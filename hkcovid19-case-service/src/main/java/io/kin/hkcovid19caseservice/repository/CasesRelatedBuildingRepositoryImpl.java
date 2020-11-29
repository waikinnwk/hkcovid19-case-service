package io.kin.hkcovid19caseservice.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
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
}
