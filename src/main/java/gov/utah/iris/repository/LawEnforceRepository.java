package gov.utah.iris.repository;

import gov.utah.iris.common.Constants;
import gov.utah.iris.model.LawEnforce;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface LawEnforceRepository extends CrudRepository<LawEnforce, Long> {
	
	@Query("select count(*) from LawEnforce l where l.umdId = :umdId and l.trackingNumber = :trackingNumber and l.fraudTypeCd = :fraudTypeCd")
	Integer getLeCount(@Param(Constants.UMD_ID) String umdId, @Param("trackingNumber") String trackingNumber, @Param("fraudTypeCd") String fraudTypeCd);
	
}
