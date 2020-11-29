package io.kin.hkcovid19caseservice.model;

import java.io.Serializable;
import java.util.Date;

public class CasesRelatedBuildingDBId  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date asOfDate;
	private String buildingName;

	public Date getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}
