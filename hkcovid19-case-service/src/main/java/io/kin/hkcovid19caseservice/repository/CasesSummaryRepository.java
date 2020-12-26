package io.kin.hkcovid19caseservice.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import io.kin.hkcovid19caseservice.model.CasesSummary;

public interface CasesSummaryRepository {
	boolean insertCasesSummary(CasesSummary c);

	Collection<CasesSummary> insertCasesSummary(Collection<CasesSummary> c);

	List<CasesSummary> getAllCasesSummary();

	CasesSummary getLatestCasesSummary();
	
	public List<CasesSummary> getLatest14CasesSummary();

	boolean isExists(CasesSummary c);
}
