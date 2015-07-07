package gov.utah.iris.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import gov.utah.iris.model.ComplaintDataPoint;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * ComplaintDataPoint repository class. 
 * 
 * @author hnguyen
 *
 */
@Repository(value = "complainDataPointRepository")
@Transactional
public class ComplaintDataPointRepository extends BaseRepository<ComplaintDataPoint> {

	private static final String DATE_KEY_LAST_3_DAYS = "0";
	private static final String DATE_KEY_LAST_7_DAYS = "1";
	private static final String DATE_KEY_LAST_14_DAYS = "2";
	private static final String DATE_KEY_LAST_30_DAYS = "3";
	private static final String DATE_KEY_LAST_6_MONTHS = "4";
	private static final String DATE_KEY_LAST_12_MONTHS = "5";
	private static final String DATE_KEY_LAST_18_MONTHS = "6";
	private static final String DATE_KEY_LAST_24_MONTHS = "7";
	private static final String DATE_KEY_LAST_36_MONTHS = "8";
	
	/**
	 * Default constructor.
	 */
	public ComplaintDataPointRepository() {
		super(ComplaintDataPoint.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplaintDataPoint> findDataPoints(String type, String dateKey, String county, String city, String zipCode) {
		StringBuffer hql = new StringBuffer("select c from ComplaintDataPoint c left join fetch c.type " +
				"where c.utmEasting != 0 and c.utmNorthing != 0");
		if (!"-1".equals(type)) {
			hql.append(" and c.typeCode = :type");
		}
		if (city != null && city.trim().length() > 0) {
			hql.append(" and upper(c.city) = :city)");
		}
		if (zipCode != null && zipCode.trim().length() > 0) {
			hql.append(" and c.zip = :zipCode");
		}
		if (county != null && county.trim().length() > 0) {
			hql.append(" and upper(c.county) = :county");
		}
		String dateFromToday = getDateFromToday(dateKey);
		if (dateKey != null && dateKey.trim().length() > 0 && getDateFromToday(dateKey) != null) {
			hql.append(" and c.complaintDate between to_date(:dateFromToday, 'MM/DD/YYYY') and sysdate");
		}
		
		Query query = entityManager.createQuery(hql.toString());
		if (!"-1".equals(type)) {
			query.setParameter("type", type);
		}
		if (city != null && city.trim().length() > 0) {
			query.setParameter("city", city.toUpperCase());
		}
		if (zipCode != null && zipCode.trim().length() > 0) {
			query.setParameter("zipCode", zipCode);
		}
		if (county != null && county.trim().length() > 0) {
			query.setParameter("county", county.toUpperCase());
		}
		if (dateKey != null && dateKey.trim().length() > 0 && getDateFromToday(dateKey) != null) {
			query.setParameter("dateFromToday", dateFromToday);
		}
		
		return query.getResultList();
	}
	
	public Long getDashboardFraudCount(Long typeId, Calendar date1, Calendar date2) {
		String hql = "";
		if (date1 == null) {
			hql = "select count(*) from ComplaintDataPoint c where c.type.id = ?1";
		} else {
			hql = "select count(*) from ComplaintDataPoint c where c.type.id = ?1 and c.complaintDate between ?2 and ?3";
		}
		Query q = entityManager.createQuery(hql);
		try {
			q.setParameter(1, typeId);
			if (date1 != null) {
				q.setParameter(2, date1.getTime(), TemporalType.DATE);
				q.setParameter(3, date2.getTime(), TemporalType.DATE);				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return (Long)q.getSingleResult();
	}
	
	/**
	 * gets the date from today (in the past) based ont the date key
	 * @param dateKey
	 * @return
	 */
	private String getDateFromToday(String dateKey){
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		
		if (dateKey.equals(DATE_KEY_LAST_3_DAYS))
			cal.add(Calendar.DATE, -3);
		else if (dateKey.equals(DATE_KEY_LAST_7_DAYS))
			cal.add(Calendar.DATE, -7);
		else if (dateKey.equals(DATE_KEY_LAST_14_DAYS))
			cal.add(Calendar.DATE, -14);
		else if (dateKey.equals(DATE_KEY_LAST_30_DAYS))
			cal.add(Calendar.DATE, -30);
		else if (dateKey.equals(DATE_KEY_LAST_6_MONTHS))
			cal.add(Calendar.MONTH, -6);
		else if (dateKey.equals(DATE_KEY_LAST_12_MONTHS))
			cal.add(Calendar.MONTH, -12);
		else if (dateKey.equals(DATE_KEY_LAST_18_MONTHS))
			cal.add(Calendar.MONTH, -18);
		else if (dateKey.equals(DATE_KEY_LAST_24_MONTHS))
			cal.add(Calendar.MONTH, -24);
		else if (dateKey.equals(DATE_KEY_LAST_36_MONTHS))
			cal.add(Calendar.MONTH, -36);
		else return null;
		
		return sdf.format(cal.getTime());
	}
	
}
