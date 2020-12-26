package io.kin.hkcovid19caseservice.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;

import io.kin.hkcovid19caseservice.model.Case;

@Component
public class CaseRepositoryImpl implements CaseRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public boolean insertCase(Case c) {
		mongoTemplate.insert(c);
		return false;
	}

	@Override
	public Collection<Case> insertCase(Collection<Case> c) {
		Collection<Case> in = new ArrayList<Case>();
		Query query = new Query();
		query.fields().include("_id");
		Set<Integer> caseNos = new HashSet<Integer>();
		mongoTemplate.executeQuery(query, "case", new DocumentCallbackHandler() {
			@Override
			public void processDocument(Document document) throws MongoException, DataAccessException {
				caseNos.add((Integer) document.get("_id"));
			}
		});
		for (Case caseObj : c) {
			if (!caseNos.contains(caseObj.getCaseNo()))
				in.add(caseObj);
		}
		Collection<Case> r = mongoTemplate.insertAll(in);
		return r;
	}

	@Override
	public List<Case> getAllCase() {
		return mongoTemplate.find(new Query(), Case.class);
	}

	@Override
	public Map<String, Long> getCaseBySymptomatic() {
		Map<String, Long> resultMap = new HashMap<String, Long>();
		Query query = new Query();
		Long all = mongoTemplate.count(query, Case.class);
		query = new Query(Criteria.where("onsetDate").regex("Asymptomatic.*", "i"));
		Long as = mongoTemplate.count(query, Case.class);
		Long s = all - as;
		resultMap.put("total", all);
		resultMap.put("asymptomatic_case", as);
		resultMap.put("symptomatic_case", s);
		return resultMap;
	}

	@Override
	public Map<String, Long> getCaseByAge() {
		Map<String, Long> resultMap = new HashMap<String, Long>();
		TypedAggregation<Case> agg = Aggregation.newAggregation(Case.class,
				Aggregation.bucket("age").withBoundaries(0, 18, 31, 41, 51, 65).withDefaultBucket("over65")
						.andOutput("_id").count().as("count"));
		AggregationResults<Document> result = mongoTemplate.aggregate(agg, Document.class);
		result.getMappedResults().forEach(document -> resultMap.put(String.valueOf(document.get("_id")),
				Long.valueOf(String.valueOf(document.get("count")))));
		return resultMap;
	}

	@Override
	public Map<String, Map<String, Long>> getCaseByMonth() {
		Map<String, Map<String, Long>> resultMap = new HashMap<String, Map<String, Long>>();
		Cond condOperation = ConditionalOperators.when(Criteria.where("caseClassification").is("Imported case"))
				.then("Y").otherwise("N");
		Aggregation agg = Aggregation.newAggregation(
				Aggregation.project(Case.class).andExpression("year(reportDate)").as("year")
						.andExpression("month(reportDate)").as("month").and(condOperation).as("importedcase"),
				group(Fields.fields().and("year").and("month").and("importedcase")).count().as("count"));
		AggregationResults<Document> result = mongoTemplate.aggregate(agg, "case", Document.class);
		result.getMappedResults().forEach(document -> {
			StringBuilder sb = new StringBuilder();
			sb.append(String.valueOf(((Document) document.get("_id")).get("year")));
			sb.append("-");
			sb.append(String.valueOf(((Document) document.get("_id")).get("month")));
			String monthKey = sb.toString();
			Map<String, Long> monthMap = null;
			if (resultMap.containsKey(monthKey))
				monthMap = resultMap.get(monthKey);
			else
				monthMap = new HashMap<String, Long>();
			if ("Y".equals(((Document) document.get("_id")).getString("importedcase")))
				monthMap.put("importedcase", Long.valueOf(String.valueOf(document.get("count"))));
			else
				monthMap.put("localcase", Long.valueOf(String.valueOf(document.get("count"))));

			resultMap.put(monthKey, monthMap);
		});
		return resultMap;
	}

	@Override
	public boolean isExists(Case c) {
		Query query = new Query(Criteria.where("caseNo").is(c.getCaseNo()));
		return mongoTemplate.exists(query, Case.class);
	}

}
