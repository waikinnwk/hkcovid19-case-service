package io.kin.hkcovid19caseservice.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

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
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import io.kin.hkcovid19caseservice.model.CasesRelatedBuildingDB;
import io.kin.hkcovid19caseservice.model.CasesRelatedBuildingDBId;

@Component
public class CasesRelatedBuildingRepositoryImpl implements CasesRelatedBuildingRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Collection<CasesRelatedBuildingDB> insertCase(Collection<CasesRelatedBuildingDB> c) {
		Collection<CasesRelatedBuildingDB> in = new ArrayList<CasesRelatedBuildingDB>();
		Query query = new Query();
		query.fields().include("_id");
		Set<CasesRelatedBuildingDBId> ids = new HashSet<CasesRelatedBuildingDBId>();
		mongoTemplate.executeQuery(query, "casesRelatedBuilding", new DocumentCallbackHandler() {
			@Override
			public void processDocument(Document document) throws MongoException, DataAccessException {
				ids.add((CasesRelatedBuildingDBId) document.get("_id"));
			}
		});
		for (CasesRelatedBuildingDB cObj : c) {
			if (!ids.contains(cObj.getId()))
				in.add(cObj);
		}
		Collection<CasesRelatedBuildingDB> r = mongoTemplate.insertAll(in);
		return r;
	}

	@Override
	public List<CasesRelatedBuildingDB> getAllCaseRelatedBuilding() {
		return mongoTemplate.find(new Query(), CasesRelatedBuildingDB.class);
	}

	@Override
	public List<CasesRelatedBuildingDB> getLatestCaseRelatedBuilding() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "_id"));
		query.limit(1);
		CasesRelatedBuildingDB maxObject = mongoTemplate.findOne(query, CasesRelatedBuildingDB.class);
		query = new Query(Criteria.where("_id.asOfDate").is(maxObject.getAsOfDate()));
		return mongoTemplate.find(query, CasesRelatedBuildingDB.class);
	}

	@Override
	public Map<String, Long> getLatestDistrictCaseData() {
		Map<String, Long> resultMap = new HashMap<String, Long>();
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.DESC, "_id"));
		query.limit(1);
		CasesRelatedBuildingDB maxObject = mongoTemplate.findOne(query, CasesRelatedBuildingDB.class);
		MatchOperation matchStage = Aggregation.match(new Criteria("_id.asOfDate").is(maxObject.getAsOfDate()));
		Aggregation agg = Aggregation.newAggregation(matchStage,
				Aggregation.project(CasesRelatedBuildingDB.class).andExpression("toUpper(district)").as("district"),
				group(Fields.fields().and("district")).count().as("count"));
		AggregationResults<Document> result = mongoTemplate.aggregate(agg, "casesRelatedBuilding", Document.class);
		result.getMappedResults().forEach(document -> resultMap.put(String.valueOf(document.get("_id")),
				Long.valueOf(String.valueOf(document.get("count")))));
		return resultMap;
	}

}
