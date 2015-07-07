package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Embedded class id for AlertJurisdiction.
 * 
 * @author hnguyen
 *
 */
@Embeddable
public class AlertJurisdictionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="ALERT_NAME")
	private String alertName;
	
	@Column(name="ALERT_CODE")
	private String alertCode;
	
	@Column(name="ORI_CODE")
	private String oriCode;

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getAlertCode() {
		return alertCode;
	}

	public void setAlertCode(String alertCode) {
		this.alertCode = alertCode;
	}

	public String getOriCode() {
		return oriCode;
	}

	public void setOriCode(String oriCode) {
		this.oriCode = oriCode;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alertName == null) ? 0 : alertName.hashCode());
		result = prime * result + ((alertCode == null) ? 0 : alertCode.hashCode());
		result = prime * result + ((oriCode == null) ? 0 : oriCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AlertJurisdictionId)) {
			return false;
		}
		AlertJurisdictionId other = (AlertJurisdictionId) obj;
		if (alertName == null) {
			if (other.alertName != null) {
				return false;
			}
		} else if (!alertName.equals(other.alertName)) {
			return false;
		}
		if (alertCode == null) {
			if (other.alertCode != null) {
				return false;
			}
		} else if (!alertCode.equals(other.alertCode)) {
			return false;
		}
		if (oriCode == null) {
			if (other.oriCode != null) {
				return false;
			}
		} else if (!oriCode.equals(other.oriCode)) {
			return false;
		}
		
		return true;
	}
}
