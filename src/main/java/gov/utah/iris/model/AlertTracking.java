package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Alert_Tracking entity.
 * 
 * @author hnguyen
 *
 */

@Entity
@Table(name="ALERT_TRACKING")
public class AlertTracking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TRACKING_NUMBER")
	private String trackingNumber;
	
	@Column(name = "ALERT_NAME")
	private String alertName = "";
	
	@Column(name = "ALERT_CODE")
	private String alertCode = "";

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

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
}
