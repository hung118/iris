package gov.utah.iris.controller;

import gov.utah.iris.manager.ComplaintManager;
import gov.utah.iris.util.ComplaintItemUtil;
import gov.utah.iris.util.ColumnComparator;
import gov.utah.iris.common.Constants;
import gov.utah.iris.model.AlertTracking;
import gov.utah.iris.service.AdminService;
import gov.utah.iris.service.MapDataService;
import gov.utah.iris.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Complaint controller handles all claim tasks.
 * 
 * @author hnguyen
 *
 */
@Controller
@RequestMapping("/secure")
@SessionAttributes(Constants.USER_INFO)
public class ComplaintController extends BaseController {

	@Autowired
	private UserService uService;
	
	@Autowired
	private MapDataService mService;

	@Autowired
	private AdminService aService;

	/**
	 * Select complaint.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectComplaint.shtml", method = RequestMethod.GET)
	public ModelAndView selectComplaint(@ModelAttribute(Constants.USER_INFO) Map<String, String> userInfo, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, String> ids = new HashMap<String, String>();
        ids.put(Constants.UMD_ID, userInfo.get(Constants.UMD_ID));
        ids.put("trackingNumber", request.getParameter("trackingNumber"));
        Map<String, Object> complaintDetails = ComplaintManager.selectComplaint(ids, "complaintDetails");
        String status = (String)complaintDetails.get("status");
        @SuppressWarnings("unchecked")
		Collection<Map<String, String>> complaintDetailsCollection = (Collection<Map<String, String>>)complaintDetails.get("complaintDetails");
        Collection<Map<String, String>> uniqueComplaintDetailsCollection = ComplaintItemUtil.removeDuplicateComplaintItems(complaintDetailsCollection);

        mav.addObject("complaintDetails", uniqueComplaintDetailsCollection);
		mav.addObject("status", status);
		mav.addObject("trackingNumber", request.getParameter("trackingNumber"));
		mav.setViewName(Constants.VIEW_CLAIM);
        return mav;
	}
	
	/**
	 * Delete complaint.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteComplaint.shtml", method = RequestMethod.GET)
	public String deleteComplaint(@ModelAttribute(Constants.USER_INFO) Map<String, String> userInfo, HttpServletRequest request) throws Exception {
        String trackingNumber = request.getParameter("trackingNumber");
        Map<String, String> ids = new HashMap<String, String>();
        ids.put(Constants.UMD_ID, userInfo.get(Constants.UMD_ID));
        ids.put("trackingNumber", trackingNumber);
        ComplaintManager.deleteComplaint(ids);

        // delete persisted security alert record(s) if any 
        deleteAlert(trackingNumber);
        
        return "redirect:/secure/claimsList.shtml";
	}
	
	/**
	 * Get list of complaint list.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/claimsList.shtml", method = RequestMethod.GET)
	public ModelAndView claimsList(@ModelAttribute(Constants.USER_INFO) Map<String, String> userInfo, HttpServletRequest request) throws Exception {
        String sort = request.getParameter("sort");

        Map<String, String> ids = new HashMap<String, String>();
        ids.put(Constants.UMD_ID, userInfo.get(Constants.UMD_ID));
        Map<String, Object> claims = ComplaintManager.selectComplaint(ids, "personComplaint");
        
		Collection<Map<String, String>> complaintList = (Collection<Map<String, String>>)claims.get("personComplaint");
		Collection<Map<String, String>> sortedComplaintSet = new ArrayList<Map<String, String>>();
		if (complaintList != null) {	// sort each map in claimsList
	        ColumnComparator sorter = new ColumnComparator();
	        if ( sort != null ) {
	            sorter.setColumn(sort);
	        }
	        else {
	            sorter.setColumn("trackingNumber");
	        }
			sortedComplaintSet = new TreeSet<Map<String, String>>(sorter);
	        sortedComplaintSet.addAll(complaintList);			
		}

        ModelAndView mav = new ModelAndView(Constants.LIST_CLAIMS);
        mav.addObject(Constants.CLAIMS, sortedComplaintSet);
        return mav;
	}
	
	@RequestMapping(value = "/caseStatus.shtml", method = RequestMethod.GET)
	public String caseStatus(HttpServletRequest request) throws Exception {
		String trackingNumber = request.getParameter("trackingNumber");
		Map<String, String> ids = new HashMap<String, String>();
        ids.put("trackingNumber", trackingNumber);
        Map<String, Object> fields = ComplaintManager.selectComplaint(ids, "case");
        
        // get collection of cases and put it on request scope
        @SuppressWarnings("unchecked")
		Collection<Map<String, String>> cases = (Collection<Map<String, String>>)fields.get("case");
        if ( cases != null ) {
            request.setAttribute("trackingNumber", trackingNumber);
            request.setAttribute("dateFiled", request.getParameter("dateFiled"));
            request.setAttribute("cases", cases);
        } else {
            logger.info("#-> no cases");
        }
        
		return "caseStatusList";
	}
    
    private void deleteAlert(String trackingNumber) {
    	List<AlertTracking> alertTrackings = uService.findAlertTrackings(trackingNumber);
    	if (!alertTrackings.isEmpty()) {
    		uService.deleteAlertTrackings(alertTrackings);
    	}
    }
		
}
