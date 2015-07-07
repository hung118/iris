package gov.utah.iris.dwr.model;

import gov.utah.iris.model.FraudType;

/**
 * 
 * @author CCUPP & HNGUYEN
 *
 */
public class ComplaintDataPoint {
	
	private Long id;
	
	private String address1;
	
	private String address2;
	
	private String complaintDate;
	
	private String jurisdictionCode;
	
	private String city;
	
	private String zip;
	
	private String county;
	
	private FraudType type;
	
	private Float utmEasting;
	
	private Float utmNorthing;
	
	private String typeCode;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	/**
	 * @return the jurisdictionCode
	 */
	public String getJurisdictionCode() {
		return jurisdictionCode;
	}

	/**
	 * @param jurisdictionCode the jurisdictionCode to set
	 */
	public void setJurisdictionCode(String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the type
	 */
	public FraudType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(FraudType type) {
		this.type = type;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	/**
	 * @return the utmEasting
	 */
	public Float getUtmEasting() {
		return utmEasting;
	}

	/**
	 * @param utmEasting the utmEasting to set
	 */
	public void setUtmEasting(Float utmEasting) {
		this.utmEasting = utmEasting;
	}

	/**
	 * @return the utmNorthing
	 */
	public Float getUtmNorthing() {
		return utmNorthing;
	}

	/**
	 * @param utmNorthing the utmNorthing to set
	 */
	public void setUtmNorthing(Float utmNorthing) {
		this.utmNorthing = utmNorthing;
	}

	/**
	 * @return the complaintDate
	 */
	public String getComplaintDate() {
		return complaintDate;
	}

	/**
	 * @param complaintDate the complaintDate to set
	 */
	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}
	
	

}
