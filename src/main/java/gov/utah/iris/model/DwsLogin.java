package gov.utah.iris.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * DWS_LOGIN entity class.
 * 
 * @author HNGUYEN
 *
 */
@Entity
@Table(name="DWS_LOGIN")
public class DwsLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UMD_ID")
	private String UmdId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="DWS_NUMBER")
	private String dwsNumber;
	
	private String server;

	public String getUmdId() {
		return UmdId;
	}

	public void setUmdId(String umdId) {
		UmdId = umdId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDwsNumber() {
		return dwsNumber;
	}

	public void setDwsNumber(String dwsNumber) {
		this.dwsNumber = dwsNumber;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}
