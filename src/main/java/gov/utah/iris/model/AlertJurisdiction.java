package gov.utah.iris.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Alert_jurisdiction entity class.
 * 
 * @author hnguyen
 *
 */
@Entity
@Table(name="ALERT_JURISDICTION")
public class AlertJurisdiction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AlertJurisdictionId id;

	public AlertJurisdictionId getId() {
		return id;
	}

	public void setId(AlertJurisdictionId id) {
		this.id = id;
	}
	
}
