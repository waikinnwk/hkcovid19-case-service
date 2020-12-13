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
	public boolean isExists(Case c) {
		Query query = new Query(Criteria.where("caseNo").is(c.getCaseNo()));
		return mongoTemplate.exists(query, Case.class);
	}

}
