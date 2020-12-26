package io.kin.hkcovid19caseservice.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import io.kin.hkcovid19caseservice.model.CasesRelatedBuildingDB;

public interface CasesRelatedBuildingRepository {
	Collection<CasesRelatedBuildingDB> insertCase(Collection<CasesRelatedBuildingDB> c);

	List<CasesRelatedBuildingDB> getAllCaseRelatedBuilding();

	List<CasesRelatedBuildingDB> getLatestCaseRelatedBuilding();
	
	public Map<String, Long> getLatestDistrictCaseData();
}
