package gov.utah.iris.repository;

import gov.utah.iris.model.FraudType;

import org.springframework.data.repository.CrudRepository;


/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface FraudTypeRepository extends CrudRepository<FraudType, Long> {
	
}
