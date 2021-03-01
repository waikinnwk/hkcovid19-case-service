package io.kin.hkcovid19caseservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class CasesRelatedBuildingWithCorrdinate extends CasesRelatedBuilding{

	private Double lat;
	private Double lon;
	public CasesRelatedBuildingWithCorrdinate(Date asOfDate, String buildingName, String district,
			String lastDateOfResidenceOfTheCase, int[] relatedCase, String relatedCaseOth, int noOfCase) {
		super(asOfDate, buildingName, district, lastDateOfResidenceOfTheCase, relatedCase, relatedCaseOth, noOfCase);
	}
}
