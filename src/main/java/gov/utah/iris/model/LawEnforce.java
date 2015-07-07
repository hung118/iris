package gov.utah.iris.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Law enforce entity.
 * 
 * @author hnguyen
 *
 */
@Entity
@Table(name="LAWENFORCE")
public class LawEnforce implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LEID_SEQ")
	@SequenceGenerator(name="LEID_SEQ", sequenceName="LEID_SEQ", allocationSize=1)
	private Long leid;

	@Column(name = "UMD_ID")
	private String umdId;
	
	@Column(name = "TRACKING_NUMBER")
	private String trackingNumber;
	
	@Column(name = "FRAUD_TYPE_CD")
	private String fraudTypeCd;
	
	private String leName;
	private String leCity;
	private String leState;
	private String leEmail;
	private String lePhone;
	private String leCaseNumber;
	private Date leDateSubmitted;
	private String leMisc;
	
	public Long getLeid() {
		return leid;
	}
	public void setLeid(Long leid) {
		this.leid = leid;
	}
	public String getUmdId() {
		return umdId;
	}
	public void setUmdId(String umdId) {
		this.umdId = umdId;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getFraudTypeCd() {
		return fraudTypeCd;
	}
	public void setFraudTypeCd(String fraudTypeCd) {
		this.fraudTypeCd = fraudTypeCd;
	}
	public String getLeName() {
		return leName;
	}
	public void setLeName(String leName) {
		this.leName = leName;
	}
	public String getLeCity() {
		return leCity;
	}
	public void setLeCity(String leCity) {
		this.leCity = leCity;
	}
	public String getLeState() {
		return leState;
	}
	public void setLeState(String leState) {
		this.leState = leState;
	}
	public String getLeEmail() {
		return leEmail;
	}
	public void setLeEmail(String leEmail) {
		this.leEmail = leEmail;
	}
	public String getLePhone() {
		return lePhone;
	}
	public void setLePhone(String lePhone) {
		this.lePhone = lePhone;
	}
	public String getLeCaseNumber() {
		return leCaseNumber;
	}
	public void setLeCaseNumber(String leCaseNumber) {
		this.leCaseNumber = leCaseNumber;
	}
	public Date getLeDateSubmitted() {
		return leDateSubmitted;
	}
	public void setLeDateSubmitted(Date leDateSubmitted) {
		this.leDateSubmitted = leDateSubmitted;
	}
	public String getLeMisc() {
		return leMisc;
	}
	public void setLeMisc(String leMisc) {
		this.leMisc = leMisc;
	}
	
}
