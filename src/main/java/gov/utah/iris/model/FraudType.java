package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * fraud type entity class.
 * 
 * @author hnguyen
 *
 */
@Entity
@Table(name="FRAUD_TYPE")
public class FraudType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String code;
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
