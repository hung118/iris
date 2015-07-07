package gov.utah.iris.repository;

import gov.utah.iris.model.Alert;
import gov.utah.iris.model.Jurisdiction;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface AlertRepository extends CrudRepository<Alert, Long> {
	
	/**
	 * Find all sorted.
	 *
	 * @return the list
	 */
	@Query(" select a from Alert a  order by name, code ")
	List<Alert> findAllSorted();
	
	@Query(" select a from Alert a  where a.id.name = :name and a.id.code = :code ")
	Alert findbyNameCode(@Param("name") String name, @Param("code") String code);
	
	@Query(" select j from Jurisdiction j, AlertJurisdiction aj where j.oriCode=aj.id.oriCode and aj.id.alertName=:name and aj.id.alertCode=:code ")
	List<Jurisdiction> findJurisdictions(@Param("name") String name, @Param("code") String code);

	@Query(" select j from Jurisdiction j order by j.name ")
	List<Jurisdiction> findAllJurisdictions();

	@Query(" select a.exitUrl from Alert a, AlertTracking at where a.id.name = at.alertName and a.id.code = at.alertCode and at.trackingNumber = :trackingNumber ")
	String findAlertUrlExit(@Param("trackingNumber") String trackingNumber);
	
	@Query("select a.email from Alert a, AlertTracking at where a.id.name = at.alertName and a.id.code = at.alertCode and at.trackingNumber = :trackingNumber ")
	String findAlertEmail(@Param("trackingNumber") String trackingNumber);
}
