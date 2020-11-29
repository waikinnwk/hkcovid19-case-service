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

	public String getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}

	public int getNoOfConfirmedCases() {
		return noOfConfirmedCases;
	}

	public void setNoOfConfirmedCases(int noOfConfirmedCases) {
		this.noOfConfirmedCases = noOfConfirmedCases;
	}

	public int getNoOfRuledOutCases() {
		return noOfRuledOutCases;
	}

	public void setNoOfRuledOutCases(int noOfRuledOutCases) {
		this.noOfRuledOutCases = noOfRuledOutCases;
	}

	public int getNoOfCasesStillHospitalisedForInvestigation() {
		return noOfCasesStillHospitalisedForInvestigation;
	}

	public void setNoOfCasesStillHospitalisedForInvestigation(int noOfCasesStillHospitalisedForInvestigation) {
		this.noOfCasesStillHospitalisedForInvestigation = noOfCasesStillHospitalisedForInvestigation;
	}

	public int getNoOfCasesFulfillingTheReportingCriteria() {
		return noOfCasesFulfillingTheReportingCriteria;
	}

	public void setNoOfCasesFulfillingTheReportingCriteria(int noOfCasesFulfillingTheReportingCriteria) {
		this.noOfCasesFulfillingTheReportingCriteria = noOfCasesFulfillingTheReportingCriteria;
	}

	public int getNoOfDeathCases() {
		return noOfDeathCases;
	}

	public void setNoOfDeathCases(int noOfDeathCases) {
		this.noOfDeathCases = noOfDeathCases;
	}

	public int getNoOfDischargeCases() {
		return noOfDischargeCases;
	}

	public void setNoOfDischargeCases(int noOfDischargeCases) {
		this.noOfDischargeCases = noOfDischargeCases;
	}

	public int getNoOfProbableCases() {
		return noOfProbableCases;
	}

	public void setNoOfProbableCases(int noOfProbableCases) {
		this.noOfProbableCases = noOfProbableCases;
	}

	public int getNoOfHospitalisedCasesInCriticalCondition() {
		return noOfHospitalisedCasesInCriticalCondition;
	}

	public void setNoOfHospitalisedCasesInCriticalCondition(int noOfHospitalisedCasesInCriticalCondition) {
		this.noOfHospitalisedCasesInCriticalCondition = noOfHospitalisedCasesInCriticalCondition;
	}

}
