package gov.utah.iris.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Complaint entity class.
 * 
 * @author hnguyen
 *
 */
@Entity
@Table(name="COMPLAINT")
public class Complaint implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComplaintId id;
	
	@Column(name = "FRAUD_DESC")
	private String fraudDesc;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "FRAUD_LINK")
	private String fraudLink;
	
	/*@Column(name = "HOLDER")	// these fields are not in production complaint table?
	private String holder;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "MIDDLENAME")
	private String middleName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "SSN")
	private String ssn;
	
	@Column(name = "INCIDENTDATE")
	private Date incidentDate;
	
	@Column(name = "INCIDENTDESC")
	private String inidentDesc; */

	public ComplaintId getId() {
		return id;
	}

	public void setId(ComplaintId id) {
		this.id = id;
	}

	public String getFraudDesc() {
		return fraudDesc;
	}

	public void setFraudDesc(String fraudDesc) {
		this.fraudDesc = fraudDesc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFraudLink() {
		return fraudLink;
	}

	public void setFraudLink(String fraudLink) {
		this.fraudLink = fraudLink;
	}
}
