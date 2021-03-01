package io.kin.hkcovid19caseservice.model;

import java.util.Date;

import lombok.Data;

@Data
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
}
