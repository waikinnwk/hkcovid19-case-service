package io.kin.hkcovid19caseservice.model;

import java.util.Date;

public class CasesRelatedBuilding {
	private Date asOfDate;
	private String district;
	private String buildingName;
	private String lastDateOfResidenceOfTheCase;
	private int[] relatedCase;
	private String relatedCaseOth;
	private int noOfCase;

	public CasesRelatedBuilding(Date asOfDate, String buildingName, String district, 
			String lastDateOfResidenceOfTheCase, int[] relatedCase, String relatedCaseOth, int noOfCase) {
		this.asOfDate = asOfDate;
		this.district = district;
		this.buildingName = buildingName;
		this.lastDateOfResidenceOfTheCase = lastDateOfResidenceOfTheCase;
		this.relatedCase = relatedCase;
		this.relatedCaseOth = relatedCaseOth;
		this.noOfCase = noOfCase;
	}

	public CasesRelatedBuildingDB toDBObj() {
		CasesRelatedBuildingDB obj = new CasesRelatedBuildingDB(this.getAsOfDate(), this.getDistrict().trim(),
				this.getBuildingName().trim(), this.lastDateOfResidenceOfTheCase, this.relatedCase, this.relatedCaseOth,
				this.noOfCase);
		return obj;
	}

	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getLastDateOfResidenceOfTheCase() {
		return lastDateOfResidenceOfTheCase;
	}

	public void setLastDateOfResidenceOfTheCase(String lastDateOfResidenceOfTheCase) {
		this.lastDateOfResidenceOfTheCase = lastDateOfResidenceOfTheCase;
	}

	public int[] getRelatedCase() {
		return relatedCase;
	}

	public void setRelatedCase(int[] relatedCase) {
		this.relatedCase = relatedCase;
	}

	public int getNoOfCase() {
		return noOfCase;
	}

	public void setNoOfCase(int noOfCase) {
		this.noOfCase = noOfCase;
	}

	public String getRelatedCaseOth() {
		return relatedCaseOth;
	}

	public void setRelatedCaseOth(String relatedCaseOth) {
		this.relatedCaseOth = relatedCaseOth;
	}
}
