package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Complaint ID for Complaint class.
 * 
 * @author hnguyen
 *
 */
@Embeddable
public class ComplaintId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="UMD_ID")
	private String umdId;
	
	@Column(name="TRACKING_NUMBER")
	private String trackingNumber;
	
	@Column(name="FRAUD_TYPE_CD")
	private String fraudTypeCd;

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((umdId == null) ? 0 : umdId.hashCode());
		result = prime * result + ((trackingNumber == null) ? 0 : trackingNumber.hashCode());
		result = prime * result + ((fraudTypeCd == null) ? 0 : fraudTypeCd.hashCode());
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
		if (!(obj instanceof ComplaintId)) {
			return false;
		}
		ComplaintId other = (ComplaintId) obj;
		if (umdId == null) {
			if (other.umdId != null) {
				return false;
			}
		} else if (!umdId.equals(other.umdId)) {
			return false;
		}
		if (trackingNumber == null) {
			if (other.trackingNumber != null) {
				return false;
			}
		} else if (!trackingNumber.equals(other.trackingNumber)) {
			return false;
		}
		if (fraudTypeCd == null) {
			if (other.fraudTypeCd != null) {
				return false;
			}
		} else if (!fraudTypeCd.equals(other.fraudTypeCd)) {
			return false;
		}
		
		return true;
	}
}
