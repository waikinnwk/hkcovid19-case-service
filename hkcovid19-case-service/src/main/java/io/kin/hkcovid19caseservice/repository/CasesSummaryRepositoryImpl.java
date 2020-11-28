package io.kin.hkcovid19caseservice.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;

import io.kin.hkcovid19caseservice.model.CasesSummary;

@Component
public class CasesSummaryRepositoryImpl implements CasesSummaryRepository {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public boolean insertCasesSummary(CasesSummary c) {
		mongoTemplate.insert(c);
		return false;
	}

	@Override
	public Collection<CasesSummary> insertCasesSummary(Collection<CasesSummary> c) {
		Collection<CasesSummary> in = new ArrayList<CasesSummary>();
		Query query = new Query();
		query.fields().include("_id");
		Set<String> asOfDates = new HashSet<String>();
		mongoTemplate.executeQuery(query, "casesSummary", new DocumentCallbackHandler() {
			@Override
			public void processDocument(Document document) throws MongoException, DataAccessException {
				asOfDates.add((String) document.get("_id"));
			}
		});
		for(CasesSummary casesSummaryObj : c) {
			if(!asOfDates.contains(casesSummaryObj.getAsOfDate()))
				in.add(casesSummaryObj);
		}	
		Collection<CasesSummary> r = mongoTemplate.insertAll(in);
		return r;
	}

	@Override
	public List<CasesSummary> getAllCasesSummary() {
		return mongoTemplate.find(new Query(), CasesSummary.class);
	}

	@Override
	public boolean isExists(CasesSummary c) {
		Query query = new Query(Criteria.where("asOfDate").is(c.getAsOfDate()));
		return mongoTemplate.exists(query, CasesSummary.class);
	}

}
