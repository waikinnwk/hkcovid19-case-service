package io.kin.hkcovid19caseservice.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

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
		Collection<Case> r = mongoTemplate.insertAll(c);
		return r;
	}
	@Override
	public List<Case> getAllCase() {
		return mongoTemplate.find(new Query(), Case.class);
	}

	@Override
	public boolean isExists(Case c) {
		Query query = new Query(
				Criteria.where("caseNo").is(c.getCaseNo()));
		return mongoTemplate.find(query, Case.class).size() > 0;
	}


}
