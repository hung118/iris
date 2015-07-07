package gov.utah.iris.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Alert entity class.
 * 
 * @author HNGUYEN
 *
 */
@Entity
@Table(name="ALERT")
public class Alert implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AlertId id;
	
	private String email;
	
	@Column(name="EXIT_URL")
	private String exitUrl;
	
	private String frauds;
	
	@Transient
	private String[] fraudsArr;
	
	@ManyToMany
	@JoinTable(
			name="ALERT_JURISDICTION",
			joinColumns={@JoinColumn(name="ALERT_NAME", referencedColumnName = "NAME"), @JoinColumn(name="ALERT_CODE", referencedColumnName = "CODE")},
			inverseJoinColumns=@JoinColumn(name="ORI_CODE", referencedColumnName = "ORI_CODE")
	)
	private List<Jurisdiction> oriList;
	
	@Transient
	private String oriCode;
	
	@Transient
	private String method;
	
	public AlertId getId() {
		return id;
	}
	public void setId(AlertId id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExitUrl() {
		return exitUrl;
	}
	public void setExitUrl(String exitUrl) {
		this.exitUrl = exitUrl;
	}
	public String getFrauds() {
		return frauds;
	}
	public void setFrauds(String frauds) {
		this.frauds = frauds;
	}
	public String[] getFraudsArr() {
		return fraudsArr;
	}
	public void setFraudsArr(String[] fraudsArr) {
		this.fraudsArr = fraudsArr;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public List<Jurisdiction> getOriList() {
		return oriList;
	}
	public void setOriList(List<Jurisdiction> oriList) {
		this.oriList = oriList;
	}
	public String getOriCode() {
		return oriCode;
	}
	public void setOriCode(String oriCode) {
		this.oriCode = oriCode;
	}
}
