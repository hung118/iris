package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Jurisdiction entity class.
 * 
 * @author hnguyen
 *
 */
@Entity
@Table(name="JURISDICTION")
public class Jurisdiction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORI_CODE")
	private String oriCode;
	
	private String name;
	
	@Transient
	private String nameAndCode;

	public String getOriCode() {
		return oriCode;
	}

	public void setOriCode(String oriCode) {
		this.oriCode = oriCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAndCode() {
		return name + " - " + oriCode;
	}

	public void setNameAndCode(String nameAndCode) {
		this.nameAndCode = nameAndCode;
	}
}
