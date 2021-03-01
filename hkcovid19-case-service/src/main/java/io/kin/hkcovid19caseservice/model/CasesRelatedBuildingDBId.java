package io.kin.hkcovid19caseservice.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CasesRelatedBuildingDBId  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date asOfDate;
	private String buildingName;
}
