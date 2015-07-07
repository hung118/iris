package gov.utah.iris.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: Sep 23, 2005
 */
public class ComplaintItemTypeLinks {

    private static HashMap<String, String> complaintItemTypeLinkMap = new HashMap<String, String>();

    static {
        complaintItemTypeLinkMap.put("BANKTRANS", "/claims/BankWithdrawal.do");
        complaintItemTypeLinkMap.put("BENEFITS", "/claims/Benefit.do");
        complaintItemTypeLinkMap.put("COLLECTOR", "/claims/CollectionAgencies.do");
        complaintItemTypeLinkMap.put("CREDITREPORT", "/claims/CreditReport.do");
        complaintItemTypeLinkMap.put("DENIED", "/claims/DeniedCredit.do");
        complaintItemTypeLinkMap.put("EMAIL", "/claims/Email.do");
        complaintItemTypeLinkMap.put("EMPLOY", "/claims/DeniedEmployment.do");
        complaintItemTypeLinkMap.put("GOV", "/claims/Gov.do");
        complaintItemTypeLinkMap.put("IDLOSTSTOLEN", "/claims/Id.do");
        complaintItemTypeLinkMap.put("IRS", "/claims/IRSNotification.do");
        complaintItemTypeLinkMap.put("LOAN", "/claims/Loan.do");
        complaintItemTypeLinkMap.put("SECURITIES", "/claims/Securities.do");
        complaintItemTypeLinkMap.put("SUIT", "/claims/SuitJudgement.do");
        complaintItemTypeLinkMap.put("SSN", "/claims/Social.do");
        complaintItemTypeLinkMap.put("UNAUTHCREDIT", "/claims/UnauthCredit.do");
        complaintItemTypeLinkMap.put("UTILITIES", "/claims/Utilities.do");
        complaintItemTypeLinkMap.put("WARRANT", "/claims/ArrestWarrant.do");
        complaintItemTypeLinkMap.put("TELEPHONE", "/claims/Telephone.do");
        complaintItemTypeLinkMap.put("OTHER_IDTHEFT", "/claims/OtherIdTheft.do");
    }


    @SuppressWarnings("rawtypes")
	public static Map getComplaintItemTypeLinkMap() {
        return complaintItemTypeLinkMap;
    }

}
