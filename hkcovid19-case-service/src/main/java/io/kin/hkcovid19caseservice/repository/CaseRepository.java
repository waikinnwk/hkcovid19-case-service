package io.kin.hkcovid19caseservice.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import io.kin.hkcovid19caseservice.model.Case;

public interface CaseRepository {
	boolean insertCase(Case c);

	Collection<Case> insertCase(Collection<Case> c);

	List<Case> getAllCase();

	Map<String, Long> getCaseBySymptomatic();

	Map<String, Long> getCaseByAge();
	
	Map<String, Map<String, Long>> getCaseByMonth();

	boolean isExists(Case c);
}
