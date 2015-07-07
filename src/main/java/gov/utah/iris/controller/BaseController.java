package gov.utah.iris.controller;

import gov.utah.iris.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseController class.
 * 
 * @author hnguyen
 *
 */
public class BaseController {
	@Autowired
	private UserService uService;
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	/**
	 * Get alert url from db.
	 * @param trackingNumber
	 * @return
	 * @throws Exception
	 */
    protected String getAlertORI(String trackingNumber) throws Exception {
    	String ret = uService.findOri(trackingNumber);
    	if (ret == null || ret.isEmpty()) {
    		return "Not Found";
    	} else {
    		return ret;
    	}
    }
}
