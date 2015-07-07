package gov.utah.iris.bean;

import gov.utah.dts.det.enums.FraudEnum;

public class BaseBean {

	private String method;
	private FraudEnum[] frauds;
	private String trackingNumber;
	private Integer fraudTypeSeq;
	private String umdId;
	private String defaultAddress;
	private String defaultZipCode;
	private String alertORI;
	
	private Integer index;
	private String fraudTypeCd;
	private String formName;
	private String type;
	private String suspectFormId;
	private String actionType;
	private String wsAction;
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public FraudEnum[] getFrauds() {
		return frauds;
	}

	public void setFrauds(FraudEnum[] frauds) {
		this.frauds = frauds;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Integer getFraudTypeSeq() {
		return fraudTypeSeq;
	}

	public void setFraudTypeSeq(Integer fraudTypeSeq) {
		this.fraudTypeSeq = fraudTypeSeq;
	}

	public String getUmdId() {
		return umdId;
	}

	public void setUmdId(String umdId) {
		this.umdId = umdId;
	}

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public String getDefaultZipCode() {
		return defaultZipCode;
	}

	public void setDefaultZipCode(String defaultZipCode) {
		this.defaultZipCode = defaultZipCode;
	}

	public String getAlertORI() {
		return alertORI;
	}

	public void setAlertORI(String alertORI) {
		this.alertORI = alertORI;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getFraudTypeCd() {
		return fraudTypeCd;
	}

	public void setFraudTypeCd(String fraudTypeCd) {
		this.fraudTypeCd = fraudTypeCd;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuspectFormId() {
		return suspectFormId;
	}

	public void setSuspectFormId(String suspectFormId) {
		this.suspectFormId = suspectFormId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getWsAction() {
		return wsAction;
	}

	public void setWsAction(String wsAction) {
		this.wsAction = wsAction;
	}
}
