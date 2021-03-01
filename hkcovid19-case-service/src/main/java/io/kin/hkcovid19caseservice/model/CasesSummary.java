package io.kin.hkcovid19caseservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "casesSummary")
public class CasesSummary {
	@Id
	private String asOfDate;
	private int noOfConfirmedCases;
	private int noOfRuledOutCases;
	private int noOfCasesStillHospitalisedForInvestigation;
	private int noOfCasesFulfillingTheReportingCriteria;
	private int noOfDeathCases;
	private int noOfDischargeCases;
	private int noOfProbableCases;
	private int noOfHospitalisedCasesInCriticalCondition;
}
