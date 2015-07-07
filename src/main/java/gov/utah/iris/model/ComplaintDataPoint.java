package gov.utah.iris.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Complaint fact entity class.
 * 
 * @author hnguyen
 *
 */
@Entity
@Table(name="COMPLAINT_FACT")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ComplaintDataPoint extends BaseModel<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPLAINT_FACT_SEQ")
	@SequenceGenerator(name="COMPLAINT_FACT_SEQ", sequenceName="COMPLAINT_FACT_SEQ", allocationSize=1)
	private Long id;
	
	@Column(name = "ADDRESS_1")
	private String address1;
	
	@Column(name = "ADDRESS_2")
	private String address2;
	
	@Column(name = "COMPLAINT_DATE")
	private Date complaintDate = new Date();
	
	@Column(name = "JURISDICTION_CODE")
	private String jurisdictionCode;
	
	private String city;
	private String zip;
	private String county;
	
	@Column(name = "TYPE")
	private String typeCode;
	
	@Column(name = "UTM_EASTING")
	private Float utmEasting;
	
	@Column(name = "UTM_NORTHING")
	private Float utmNorthing;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRAUD_TYPE_ID")
	private FraudType type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getJurisdictionCode() {
		return jurisdictionCode;
	}

	public void setJurisdictionCode(String jurisdictionCode) {
		this.jurisdictionCode = jurisdictionCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Float getUtmEasting() {
		return utmEasting;
	}

	public void setUtmEasting(Float utmEasting) {
		this.utmEasting = utmEasting;
	}

	public Float getUtmNorthing() {
		return utmNorthing;
	}

	public void setUtmNorthing(Float utmNorthing) {
		this.utmNorthing = utmNorthing;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public FraudType getType() {
		return type;
	}

	public void setType(FraudType type) {
		this.type = type;
	}

}
