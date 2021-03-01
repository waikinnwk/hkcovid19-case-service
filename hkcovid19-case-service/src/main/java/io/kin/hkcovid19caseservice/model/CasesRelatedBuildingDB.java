package io.kin.hkcovid19caseservice.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "casesRelatedBuilding")
public class CasesRelatedBuildingDB {

	private CasesRelatedBuildingDBId id;
	private String district;
	private String lastDateOfResidenceOfTheCase;
	private int[] relatedCase;
	private String relatedCaseOth;
	private int noOfCase;

	public CasesRelatedBuildingDB() {
		this.id = new CasesRelatedBuildingDBId();
	}

	public CasesRelatedBuildingDB(Date asOfDate, String district, String buildingName,
			String lastDateOfResidenceOfTheCase, int[] relatedCase, String relatedCaseOth, int noOfCase) {
		this.id = new CasesRelatedBuildingDBId();
		this.id.setAsOfDate(asOfDate);
		this.id.setBuildingName(buildingName);
		this.district = district;
		this.lastDateOfResidenceOfTheCase = lastDateOfResidenceOfTheCase;
		this.relatedCase = relatedCase;
		this.relatedCaseOth = relatedCaseOth;
		this.noOfCase = noOfCase;
	}

	public CasesRelatedBuilding toObj() {
		CasesRelatedBuilding obj = new CasesRelatedBuilding(this.id.getAsOfDate(), this.id.getBuildingName(),
				this.getDistrict(), this.lastDateOfResidenceOfTheCase, this.relatedCase, this.relatedCaseOth,
				this.noOfCase);
		return obj;
	}

	public Date getAsOfDate() {
		return this.id.getAsOfDate();
	}

	public void setAsOfDate(Date asOfDate) {
		this.id.setAsOfDate(asOfDate);
	}

	public String getBuildingName() {
		return this.id.getBuildingName();
	}

	public void setBuildingName(String buildingName) {
		this.id.setBuildingName(buildingName);
	}
}
