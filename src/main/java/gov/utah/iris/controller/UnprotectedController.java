/**
 * 
 */
package gov.utah.iris.controller;

import gov.utah.iris.manager.ComplaintManager;
import gov.utah.iris.service.MapDataService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class which has URLs with *.public extension that are not protected by siteminder with url pattern /public/*.
 * 
 * @author hnguyen
 *
 */
@Controller
@RequestMapping("/public")
public class UnprotectedController extends BaseController {
	
	@Autowired
	private MapDataService mService; 

	@RequestMapping(value = "/validateVictim.shtml", method = RequestMethod.GET)
	public String validateVictim(HttpServletRequest request) throws Exception {
		String trackingNumber = request.getParameter("trackingNumber"); 
		if (trackingNumber == null) {
			return "validateVictimForm";
		}

		Map<String, String> ids = new HashMap<String, String>();
		ids.put("trackingNumber", trackingNumber);
		Map<String, Object> fields = ComplaintManager.selectComplaint(ids, "validateVictim");
		
		// get collection of cases and put it on request scope
		@SuppressWarnings("unchecked")
		Collection<Map<String, String>> validateVictims = (Collection<Map<String, String>>)fields.get("validateVictim");
		if (validateVictims != null) {
			request.setAttribute("trackingNumber", trackingNumber);
			request.setAttribute("cases", validateVictims);
			
			// set victim last name on request
			Iterator<Map<String, String>> it = validateVictims.iterator();
			Map<String, String> map = it.next();
			request.setAttribute("victimLastName", map.get("victimLastName"));
		} else {
			logger.info("*** No victims ***");
		}
		
		return "validateVictimResults";
	}
	
	@RequestMapping(value = "/map.shtml", method = RequestMethod.GET)
	public String showMap(HttpServletRequest request) throws Exception {
		request.setAttribute("fraudList", mService.findAllFraudTypes());
		return "irisMapLayout";
	}
}
