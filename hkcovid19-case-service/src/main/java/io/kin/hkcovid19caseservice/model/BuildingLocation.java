package io.kin.hkcovid19caseservice.model;


import lombok.Data;

@Data
public class BuildingLocation {
	private String district;
	private String buildingName;
	private Double lat;
	private Double lon;
}
