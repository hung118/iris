package gov.utah.iris.controller;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.factory.Accou;
import gov.utah.iris.factory.Colle;
import gov.utah.iris.factory.Credi;
import gov.utah.iris.factory.Fraud;
import gov.utah.iris.factory.FraudFactory;
import gov.utah.iris.factory.Issue;
import gov.utah.iris.factory.Suspect;
import gov.utah.iris.factory.Trans;
import gov.utah.iris.factory.Tranx;
import gov.utah.iris.manager.ComplaintItemManager;
import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.iris.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Complain item controller handles all complaint item/sub item tasks.
 * 
 * @author hnguyen
 *
 */
@Controller
@RequestMapping("/secure")
@SessionAttributes(Constants.USER_INFO)
public class ComplaintItemController extends BaseController {

	@Autowired
	private UserService uService;
	
	/**
	 * Select to view complaint item fraud (BANKTRANS, SSN, ...) retrieved from webservice.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectComplaintItem.shtml", method = RequestMethod.GET)
	public ModelAndView selectComplaintItem(@ModelAttribute(Constants.USER_INFO) Map<String, String> userInfo, HttpServletRequest request) throws Exception {
		// clear session cache object
		HttpCacheManager cache = new HttpCacheManager(request.getSession());
		cache.clear();
		
		FraudEnum fraudEnum = FraudEnum.valueOf(Constants.getFraudType().get(request.getParameter("fraudTypeCd")));
		ReportedFraudsForm formBean = new ReportedFraudsForm();
		formBean.setVictim(userInfo);
		formBean.setUmdId(userInfo.get(Constants.UMD_ID));
		String trackingNumber = request.getParameter("trackingNumber");
		formBean.setTrackingNumber(trackingNumber);
		formBean.setFraudTypeCd(request.getParameter("fraudTypeCd"));
		if (request.getParameter("fraudTypeSeq") != null) {
			formBean.setFraudTypeSeq(new Integer(request.getParameter("fraudTypeSeq")));
		}
		formBean.setDefaultAddress(userInfo.get("addr1Street"));
		formBean.setDefaultZipCode(userInfo.get("addr1Zip"));
		formBean.setAlertORI(getAlertORI(trackingNumber));
		
		Fraud fraud = FraudFactory.getInstance(fraudEnum, formBean, request.getSession());
		fraud.selectFraud();
		formBean.setFormName(fraud.getFormName());
		
		switch(fraudEnum) {
		case BANKTRANS:
			formBean.setBanktrans(fraud.getFormData());
			break;
		case BENEFITS:
			formBean.setBenefits(fraud.getFormData());
			break;
		case UNAUTHCREDIT:
			formBean.setUnauthcredit(fraud.getFormData());
			break;
		case CREDITREPORT:
			formBean.setCreditreport(fraud.getFormData());
			break;
		case LOAN:
			formBean.setLoan(fraud.getFormData());
			break;
		case COLLECTOR:
			formBean.setCollector(fraud.getFormData());
			break;
		case IDLOSTSTOLEN:
			formBean.setIdloststolen(fraud.getFormData());
			break;
		case SSN:
			formBean.setSsn(fraud.getFormData());
			break;
		case TELEPHONE:
			formBean.setTelephone(fraud.getFormData());
			break;
		case UTILITIES:
			formBean.setUtilities(fraud.getFormData());
			break;
		case SUIT:
			formBean.setSuit(fraud.getFormData());
			break;
		case EMAIL:
			formBean.setEmail(fraud.getFormData());
			break;
		case OTHER_IDTHEFT:
			formBean.setOther_idtheft(fraud.getFormData());
			break;
		case accou:	// subitems, will be handled via selectComplaintSubItem method
		case trans:
		case colle:
		case credi:
		case issue:
		case tranx:
		case suspect:
		}
		
		String forward = "";
		if ("n".equals(request.getParameter("status"))) {
			formBean.setWsAction("update");
			forward = formBean.getFormName();
		} else {
			forward = formBean.getFormName() + "_ro"; 	// readonly
		}
		ModelAndView mav = new ModelAndView(forward);		
		mav.addObject("formBean", formBean);
		mav.addObject("status", request.getParameter("status"));
		mav.addObject("trackingNumber", trackingNumber);
		return mav;
	}

	/**
	 * Ajax select to view complaint sub item (accou, trans, suspect, ...) retrieved from session cache.
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectComplaintSubItem.shtml", method = RequestMethod.GET)
	public String selectComplaintSubItem(Model model, HttpServletRequest request) throws Exception {
		ReportedFraudsForm formBean = new ReportedFraudsForm();
		HttpCacheManager cache = new HttpCacheManager(request.getSession());
		String type = request.getParameter("type");
		String fraudTypeCd = request.getParameter("fraudTypeCd");
		String trackingNumber = request.getParameter("trackingNumber");
		String fraudTypeSeq = request.getParameter("fraudTypeSeq");
		int index = -1;
        if (request.getParameter("index") != null) {
        	index = Integer.parseInt(request.getParameter("index"));
        }
		
		FraudEnum fraudEnum = FraudEnum.valueOf(Constants.getFraudType().get(type));		
		switch(fraudEnum) {
		case BANKTRANS:	// complaint items, already handled via selectComplaintItem method.
		case BENEFITS:
		case UNAUTHCREDIT:
		case CREDITREPORT:
		case LOAN:
		case COLLECTOR:
		case IDLOSTSTOLEN:
		case SSN:
		case TELEPHONE:
		case UTILITIES:
		case SUIT:
		case EMAIL:
		case OTHER_IDTHEFT:
		case accou:	
			if (index != -1) {	// get it from session cache for update
				ArrayList<Map<String, String>> accous = (ArrayList<Map<String, String>>)cache.get(type);
				formBean.setAccou(accous.get(index));	
				formBean.setIndex(new Integer(index));
			} else {	// insert, init form
				Accou accou = new Accou();
				accou.initFormData();
				formBean.setAccou(accou.getFormData());
			}
			break;
		case trans:
			if (index != -1) {
				ArrayList<Map<String, String>> transes = (ArrayList<Map<String, String>>)cache.get(type);
				formBean.setTrans(transes.get(index));	
				formBean.setIndex(new Integer(index));
			} else {
				Trans trans = new Trans();
				trans.initFormData();
				formBean.setTrans(trans.getFormData());
			}		
			break;
		case tranx:
			if (index != -1) {
				ArrayList<Map<String, String>> tranxes = (ArrayList<Map<String, String>>)cache.get(type);
				formBean.setTranx(tranxes.get(index));	
				formBean.setIndex(new Integer(index));
			} else {
				Tranx tranx = new Tranx();
				tranx.initFormData();
				formBean.setTranx(tranx.getFormData());
			}
			break;
		case issue:
			if (index != -1) {
				ArrayList<Map<String, String>> issues = (ArrayList<Map<String, String>>)cache.get(type);
				formBean.setIssue(issues.get(index));	
				formBean.setIndex(new Integer(index));
			} else {
				Issue issue = new Issue();
				issue.initFormData();
				formBean.setIssue(issue.getFormData());
			}
			break;
		case colle:
			if (index != -1) {
				ArrayList<Map<String, String>> colles = (ArrayList<Map<String, String>>)cache.get(type);
				formBean.setColle(colles.get(index));	
				formBean.setIndex(new Integer(index));
			} else {
				Colle colle = new Colle();
				colle.initFormData();
				formBean.setColle(colle.getFormData());
			}
			break;
		case credi:
			if (index != -1) {
				ArrayList<Map<String, String>> credis = (ArrayList<Map<String, String>>)cache.get(type);
				formBean.setCredi(credis.get(index));	
				formBean.setIndex(new Integer(index));
			} else {
				Credi credi = new Credi();
				credi.initFormData();
				formBean.setCredi(credi.getFormData());
			}
			break;
		case suspect:
			if (index != -1) {
				ArrayList<Map<String, String>> suspects = (ArrayList<Map<String, String>>)cache.get(fraudTypeCd.toLowerCase() + "Suspect");
				formBean.setSuspect(suspects.get(index));	
				formBean.setIndex(new Integer(index));
			} else {
				Suspect suspect = new Suspect();
				suspect.initFormData();
				formBean.setSuspect(suspect.getFormData());
			}
			
			formBean.setSuspectFormId(fraudTypeCd.toLowerCase() + "SuspectForm");
			break;					
		}
		
		formBean.setTrackingNumber(trackingNumber);
		formBean.setFraudTypeCd(fraudTypeCd);
	
		try {
			Integer.parseInt(fraudTypeSeq);
			formBean.setFraudTypeSeq(new Integer(fraudTypeSeq));
		} catch (NumberFormatException nfe) {
			// ignore and continue.
		}

		if ("update".equals(request.getParameter("wsAction"))) {
			formBean.setWsAction("update");
		} else {
			formBean.setWsAction("insert");
		}
		formBean.setType(type);	
		
		model.addAttribute("claimSubItemForm", formBean);
		return type;
	}
	
	/**
	 * Delete complaint item from webservice.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteComplaintItem.shtml", method = RequestMethod.GET)
	public String deleteComplaintItem(@ModelAttribute(Constants.USER_INFO) Map<String, String> userInfo, HttpServletRequest request) throws Exception {
		String trackingNumber = request.getParameter("trackingNumber");
		Map<String, String> ids = new HashMap<String, String>();
        ids.put(Constants.UMD_ID, userInfo.get(Constants.UMD_ID));
        ids.put("trackingNumber", trackingNumber);
        ids.put("fraudTypeCd", request.getParameter("fraudTypeCd"));
        ids.put("fraudTypeSeq", request.getParameter("fraudTypeSeq"));
        ids.put("dataElementSeq", request.getParameter("dataElementSeq"));
		
        ComplaintItemManager.deleteComplaintItem(ids);
        
		return "redirect:/secure/selectComplaint.shtml?trackingNumber=" + trackingNumber;
	}
	
	/**
	 * Ajax delete sub item from session cache.
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteSubItem.json", method = RequestMethod.GET)
	public Model deleteSubItem(Model model, HttpServletRequest request) {
		String errors = "";
		try {
			// delete from webservice
	        String trackingNumber = request.getParameter("trackingNumber");
	        String fraudTypeSeq = request.getParameter("fraudTypeSeq");
	        String dataElementSeq = request.getParameter("dataElementSeq");
	        String fraudTypeCd = request.getParameter("fraudTypeCd");
			String type = request.getParameter("type");

	        String wsAction = request.getParameter("wsAction");
	        if ("update".equals(wsAction)) {
		        Map<String, String> ids = new HashMap<String, String>(); 
		        if ("suspect".equals(type)) {
		        	String personIndex = request.getParameter("personIndex");
		        	ids.put("personIndex", personIndex);
		        	ComplaintItemManager.deleteSuspect(ids);
		        } else {
					@SuppressWarnings("unchecked")
					String umdId = ((Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO)).get(Constants.UMD_ID);
					ids.put("umdId", umdId);
					ids.put("trackingNumber", trackingNumber);
					ids.put("fraudTypeCd", fraudTypeCd);
					ids.put("fraudTypeSeq", fraudTypeSeq);
					ids.put("dataElementSeq", dataElementSeq);
					ComplaintItemManager. deleteComplaintItem(ids);
		        }	        	        	
	        }
	        
	        // delete from session cache object.
			if ("suspect".equals(type)) {
				type = fraudTypeCd.toLowerCase() + "Suspect";
			}
	        int index = Integer.parseInt(request.getParameter("index"));
	        HttpCacheManager cache = new HttpCacheManager(request.getSession());
	        cache.remove(type, index);
	        
	        // get list of sub items from session for ajax display
	        Collection<Map<String, String>> subItemList = getSubItemList(type, trackingNumber, fraudTypeSeq, wsAction, request.getSession());
	        
	        model.addAttribute("subItemList", subItemList);
		} catch (Exception e) {
			errors = e.getMessage();
			model.addAttribute("errors", errors);
		}
		
		return model;
	}
	
	/**
	 * Ajax add claim subitem (accou, trans, suspect, ...) to session cache object and/or to webservice.
	 * @param claimSubItemForm
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addSubItem.json", method = RequestMethod.POST)
	public Model addSubItem(@ModelAttribute("claimSubItemForm") ReportedFraudsForm claimSubItemForm, Model model, HttpServletRequest request) throws Exception {
		String fraudTypeCd = claimSubItemForm.getFraudTypeCd();
		String type = claimSubItemForm.getType();
		
		if ("update".equals(claimSubItemForm.getWsAction())) {
			@SuppressWarnings("unchecked")
			Map<String, String> userInfo = (Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO);
			claimSubItemForm.setDefaultAddress(userInfo.get("addr1Street"));
			claimSubItemForm.setDefaultZipCode(userInfo.get("addr1Zip"));
			claimSubItemForm.setAlertORI(getAlertORI(claimSubItemForm.getTrackingNumber()));
			claimSubItemForm.setUmdId(userInfo.get(Constants.UMD_ID));
		}
		
		FraudEnum fraudEnum = FraudEnum.valueOf(Constants.getFraudType().get(type));
		HttpCacheManager cache = new HttpCacheManager(request.getSession());
		switch(fraudEnum) {
		case BANKTRANS:
		case BENEFITS:
		case UNAUTHCREDIT:
		case CREDITREPORT:
		case LOAN:
		case COLLECTOR:
		case IDLOSTSTOLEN:
		case SSN:
		case TELEPHONE:
		case UTILITIES:
		case SUIT:
		case EMAIL:
		case OTHER_IDTHEFT:
		case accou:	
			if ("update".equals(claimSubItemForm.getWsAction())) {	// create fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.accou, claimSubItemForm, request.getSession());
				fraud.createFraud();
			} else {	// just add it to cache
				cache.add("accou", claimSubItemForm.getAccou());
			}
			break;
		case trans:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// create fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.trans, claimSubItemForm, request.getSession());
				fraud.createFraud();
			} else {	// just add it to cache
				cache.add("trans", claimSubItemForm.getTrans());
			}
			break;
		case tranx:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// create fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.tranx, claimSubItemForm, request.getSession());
				fraud.createFraud();
			} else {	// just add it to cache
				cache.add("tranx", claimSubItemForm.getTranx());
			}
			break;
		case issue:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// create fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.issue, claimSubItemForm, request.getSession());
				fraud.createFraud();
			} else {	// just add it to cache
				cache.add("issue", claimSubItemForm.getIssue());
			}
			break;
		case colle:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// create fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.colle, claimSubItemForm, request.getSession());
				fraud.createFraud();
			} else {	// just add it to cache
				cache.add("colle", claimSubItemForm.getColle());
			}
			break;
		case credi:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// create fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.credi, claimSubItemForm, request.getSession());
				fraud.createFraud();
			} else {	// just add it to cache
				cache.add("credi", claimSubItemForm.getCredi());
			}
			break;
		case suspect:
			String personIndex = "";
			if ("update".equals(claimSubItemForm.getWsAction())) {
				Fraud fraud = FraudFactory.getInstance(FraudEnum.suspect, claimSubItemForm, request.getSession());
				personIndex = fraud.createFraud();
			}
			
			claimSubItemForm.getSuspect().put("personIndex", personIndex);
			type = fraudTypeCd.toLowerCase() + "Suspect";
			cache.add(type, claimSubItemForm.getSuspect());
			
			break;					
		}
		
		// refresh session cache with data from ws for latest dataElementSeq value after creating sub item.
		if ("update".equals(claimSubItemForm.getWsAction())) {	
			fraudEnum = FraudEnum.valueOf(Constants.getFraudType().get(fraudTypeCd));
			Fraud fraud = FraudFactory.getInstance(fraudEnum, claimSubItemForm, request.getSession());
			fraud.selectFraud();
		}
		
        // get list of sub items from session for ajax display
		Collection<Map<String, String>> subItemList = getSubItemList(type, claimSubItemForm.getTrackingNumber(), String.valueOf(claimSubItemForm.getFraudTypeSeq()), claimSubItemForm.getWsAction(), request.getSession());
        model.addAttribute("subItemList", subItemList);
		
		return model;		
	}
	
	/**
	 * Ajax update claim subitem (accou, trans, suspect, ...) to session cache object and/or webservice.
	 * @param claimSubItemForm
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateSubItem.json", method = RequestMethod.POST)
	public Model updateSubItem(@ModelAttribute("claimSubItemForm") ReportedFraudsForm claimSubItemForm, Model model, HttpServletRequest request) throws Exception {
		String fraudTypeCd = claimSubItemForm.getFraudTypeCd();
		String type = claimSubItemForm.getType();
		
		if ("update".equals(claimSubItemForm.getWsAction())) {
			@SuppressWarnings("unchecked")
			Map<String, String> userInfo = (Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO);
			claimSubItemForm.setDefaultAddress(userInfo.get("addr1Street"));
			claimSubItemForm.setDefaultZipCode(userInfo.get("addr1Zip"));
			claimSubItemForm.setAlertORI(getAlertORI(claimSubItemForm.getTrackingNumber()));
			claimSubItemForm.setUmdId(userInfo.get(Constants.UMD_ID));
		}
        
		FraudEnum fraudEnum = FraudEnum.valueOf(Constants.getFraudType().get(type));
		HttpCacheManager cache = new HttpCacheManager(request.getSession());
		switch(fraudEnum) {
		case BANKTRANS:
		case BENEFITS:
		case UNAUTHCREDIT:
		case CREDITREPORT:
		case LOAN:
		case COLLECTOR:
		case IDLOSTSTOLEN:
		case SSN:
		case TELEPHONE:
		case UTILITIES:
		case SUIT:
		case EMAIL:
		case OTHER_IDTHEFT:
		case accou:	
			if ("update".equals(claimSubItemForm.getWsAction())) {	// update fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.accou, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			cache.update(type, claimSubItemForm.getAccou(), claimSubItemForm.getIndex().intValue());
			break;
		case trans:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// update fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.trans, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			cache.update(type, claimSubItemForm.getTrans(), claimSubItemForm.getIndex().intValue());
			break;
		case tranx:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// update fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.tranx, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			cache.update(type, claimSubItemForm.getTranx(), claimSubItemForm.getIndex().intValue());
			break;
		case issue:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// update fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.issue, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			cache.update(type, claimSubItemForm.getIssue(), claimSubItemForm.getIndex().intValue());
			break;
		case colle:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// update fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.colle, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			cache.update(type, claimSubItemForm.getColle(), claimSubItemForm.getIndex().intValue());
			break;
		case credi:
			if ("update".equals(claimSubItemForm.getWsAction())) {	// update fraud in ws
				Fraud fraud = FraudFactory.getInstance(FraudEnum.credi, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			cache.update(type, claimSubItemForm.getCredi(), claimSubItemForm.getIndex().intValue());
			break;
		case suspect:
			if ("update".equals(claimSubItemForm.getWsAction())) {
				Fraud fraud = FraudFactory.getInstance(FraudEnum.suspect, claimSubItemForm, request.getSession());
				fraud.updateFraud();
			}
			type = fraudTypeCd.toLowerCase() + "Suspect";
			cache.update(type, claimSubItemForm.getSuspect(), claimSubItemForm.getIndex().intValue());
			break;					
		}
		
        // get list of sub items from session for ajax display
		Collection<Map<String, String>> subItemList = getSubItemList(type, claimSubItemForm.getTrackingNumber(), String.valueOf(claimSubItemForm.getFraudTypeSeq()), claimSubItemForm.getWsAction(), request.getSession());
        model.addAttribute("subItemList", subItemList);
		
		return model;		
	}
	
	/**
	 * Get list of sub items from session for ajax display
	 * 
	 * @return
	 */
	private Collection<Map<String, String>> getSubItemList(String type, String trackingNumber, String fraudTypeSeq, String wsAction, HttpSession session) {
		HttpCacheManager cache = new HttpCacheManager(session);
        Collection<Map<String, String>> subItemList = cache.get(type);
        Iterator<Map<String, String>> it = subItemList.iterator();
        int i = 0;
        while (it.hasNext()) {
        	Map<String, String> form = (Map<String, String>)it.next();
	        form.put("index", String.valueOf(i));
	        form.put("trackingNumber", trackingNumber);
	        form.put("fraudTypeSeq", fraudTypeSeq);
	        form.put("wsAction", wsAction);
	        
	        i = i + 1;
        }
        
        return subItemList;
	}
}

