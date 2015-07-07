package gov.utah.iris.repository;

import org.springframework.data.repository.CrudRepository;

import gov.utah.iris.model.AlertJurisdiction;


/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface AlertJurisdictionRepository extends CrudRepository<AlertJurisdiction, Long> {
	
}
