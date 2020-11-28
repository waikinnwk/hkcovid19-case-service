package io.kin.hkcovid19caseservice.model;

public class InsertResult {
	private String result;
	private String errorMsg;
	
	private static final String SUCCESS = "S";
	private static final String DUPLICATE = "D";
	private static final String ERROR = "E";
	public void successResult() {
		this.result = SUCCESS;
	}
	
	public void duplicateResult() {
		this.result = DUPLICATE;
	}
	
	public void errorResult(String errorMsg) {
		this.result = ERROR;
		this.errorMsg = errorMsg;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
