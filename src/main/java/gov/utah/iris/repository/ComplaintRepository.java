package gov.utah.iris.repository;

import java.util.List;

import gov.utah.iris.common.Constants;
import gov.utah.iris.model.Complaint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Simple repository interface for {@link Fee} instances. 
 * 
 * @author hnguyen
 *
 */
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {

	@Query("select count(*) from Complaint c where c.id.umdId = :umdId and c.id.trackingNumber = :trackingNumber and c.id.fraudTypeCd = :fraudTypeCd")
	Integer getComplaintCount(@Param(Constants.UMD_ID) String umdId, @Param("trackingNumber") String trackingNumber, @Param("fraudTypeCd") String fraudTypeCd);
	
	@Query("select c from Complaint c where c.id.umdId = :umdId and c.id.trackingNumber = :trackingNumber order by c.createdDate")
	List<Complaint> findComplaints(@Param(Constants.UMD_ID) String umdId, @Param("trackingNumber") String trackingNumber);
	
	@Query("delete from Complaint c where c.id.umdId = :umdId and c.id.trackingNumber = :trackingNumber and c.id.fraudTypeCd = :fraudTypeCd")
	void deleteById(@Param(Constants.UMD_ID) String umdId, @Param("trackingNumber") String trackingNumber, @Param("fraudTypeCd") String fraudTypeCd);
	
}
