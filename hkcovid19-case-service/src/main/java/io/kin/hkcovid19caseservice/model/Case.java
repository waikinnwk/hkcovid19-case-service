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

	public int getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(int caseNo) {
		this.caseNo = caseNo;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getOnsetDate() {
		return onsetDate;
	}

	public void setOnsetDate(String onsetDate) {
		this.onsetDate = onsetDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdmittedHospital() {
		return admittedHospital;
	}

	public void setAdmittedHospital(String admittedHospital) {
		this.admittedHospital = admittedHospital;
	}

	public String getHospitalStatus() {
		return hospitalStatus;
	}

	public void setHospitalStatus(String hospitalStatus) {
		this.hospitalStatus = hospitalStatus;
	}

	public String getIsHKResident() {
		return isHKResident;
	}

	public void setIsHKResident(String isHKResident) {
		this.isHKResident = isHKResident;
	}

	public String getCaseClassification() {
		return caseClassification;
	}

	public void setCaseClassification(String caseClassification) {
		this.caseClassification = caseClassification;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
