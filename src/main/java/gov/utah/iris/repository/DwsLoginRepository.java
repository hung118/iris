package gov.utah.iris.repository;

import gov.utah.iris.model.DwsLogin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface DwsLoginRepository extends CrudRepository<DwsLogin, Long> {
	
	/**
	 * Find all sorted.
	 *
	 * @return the list
	 */
	@Query(" select d from DwsLogin d order by d.createdDate desc ")
	List<DwsLogin> findAllSorted();
	
}
