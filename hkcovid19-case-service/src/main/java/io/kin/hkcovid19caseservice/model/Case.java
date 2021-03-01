package io.kin.hkcovid19caseservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "case")
public class Case {
	@Id
	private int caseNo;
	private Date reportDate;
	private String onsetDate;
	private String gender;
	private int age;
	private String admittedHospital;
	private String hospitalStatus;
	private String isHKResident;
	private String caseClassification;
	private String status;
}
