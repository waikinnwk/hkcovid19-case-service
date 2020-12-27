package io.kin.hkcovid19caseservice.model;

import java.util.Date;

public class CasesRelatedBuildingWithCorrdinate extends CasesRelatedBuilding{

	private Double lat;
	private Double lon;
	public CasesRelatedBuildingWithCorrdinate(Date asOfDate, String buildingName, String district,
			String lastDateOfResidenceOfTheCase, int[] relatedCase, String relatedCaseOth, int noOfCase) {
		super(asOfDate, buildingName, district, lastDateOfResidenceOfTheCase, relatedCase, relatedCaseOth, noOfCase);
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}

}
