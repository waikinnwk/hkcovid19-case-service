package io.kin.hkcovid19caseservice.repository;

import java.util.Collection;
import java.util.List;

import io.kin.hkcovid19caseservice.model.Case;

public interface CaseRepository {
	boolean insertCase(Case c);
	Collection<Case> insertCase(Collection<Case> c);
	List<Case> getAllCase();
	boolean isExists(Case c);
}
