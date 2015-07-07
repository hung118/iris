package gov.utah.iris.repository;

import java.util.List;

import gov.utah.iris.model.AlertTracking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface AlertTrackingRepository extends CrudRepository<AlertTracking, Long> {
	
	@Query("select aj.id.oriCode from AlertTracking at, AlertJurisdiction aj where at.alertName = aj.id.alertName and at.alertCode = aj.id.alertCode and at.trackingNumber = :trackingNumber ")
	String findOriCode(@Param("trackingNumber") String trackingNumber);
	
	@Query("select at from AlertTracking at where at.trackingNumber = :trackingNumber ")
	List<AlertTracking> findAlertTrackings(@Param("trackingNumber") String trackingNumber);
	
}
