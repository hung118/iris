package gov.utah.iris.common;

import java.util.HashMap;
import java.util.Map;

public class CaseConstants {

	private static HashMap<String, String> caseMap = new HashMap<String, String>(21);
	private static HashMap<String, String> caseWebMap = new HashMap<String, String>(16);
	private static HashMap<String, String> caseLocalMap = new HashMap<String, String>(7);
	private static HashMap<String, String> caseLink = new HashMap<String, String>(21);
	private static HashMap<String, String> fraudLink = new HashMap<String, String>(21);
	private static HashMap<String, String> fraudLink2 = new HashMap<String, String>(21);
	private static HashMap<String, Integer> fraudLink3 = new HashMap<String, Integer>(21);
	private static HashMap<String, Integer> fraudLink4 = new HashMap<String, Integer>(5);
	
	static {
		caseMap.put("p1", "Unauthorized Withdrawal from Your Checking or Savings Account");
		caseMap.put("p2", "Unauthorized checking/savings account opened in your name");
		caseMap.put("p3", "Medicaid/Medicare, Insurance, or State Benefits");
		caseMap.put("p4", "Unauthorized Use of Your Credit Card");
		caseMap.put("p5", "Unauthorized Accounts on Credit Report");
		caseMap.put("p6", "Unauthorized Loan");
		caseMap.put("p7", "Collection Agency");
		caseMap.put("p8", "Driver's License/ID Lost, Stolen or Misused");
		caseMap.put("p9", "Birth/Death Certificate");
		caseMap.put("p10", "Passport");
		caseMap.put("p11", "Military ID");
		caseMap.put("p12", "Permanent Resident Card (Green Card)");
		caseMap.put("p13", "Social Security Number Theft");
		caseMap.put("p14", "Telephone Service Fraud");
		caseMap.put("p15", "Utilities Fraud");
		caseMap.put("p16", "False Criminal History");
		caseMap.put("p17", "Warrant issued");
		caseMap.put("p18", "False Civil Judgment");
		caseMap.put("p19", "IRS notification");
		caseMap.put("p20", "Email Identity Theft");
		caseMap.put("p21", "Other Identity Theft");
		
		caseWebMap.put("BANKTRANS", "Unauthorized Withdrawal from Your Checking or Savings Account");
		caseWebMap.put("BENEFITS", "Medicaid/Medicare, Insurance, or State Benefits");
		caseWebMap.put("UNAUTHCREDIT", "Unauthorized Use of Your Credit Card");
		caseWebMap.put("CREDITREPORT", "Unauthorized Accounts on Credit Report");
		caseWebMap.put("LOAN", "Unauthorized Loan");
		caseWebMap.put("COLLECTOR", "Collection Agency");
		caseWebMap.put("IDLOSTSTOLEN", "Driver's License/ID Lost, Stolen or Misused");
		caseWebMap.put("SSN", "Social Security Number Theft");
		caseWebMap.put("TELEPHONE", "Telephone Service Fraud");
		caseWebMap.put("UTILITIES", "Utilities Fraud");
		caseWebMap.put("SUIT", "False Civil Judgment");
		caseWebMap.put("EMAIL", "Email Identity Theft");
		caseWebMap.put("OTHER_IDTHEFT", "Other Identity Theft");
		
		caseLocalMap.put("BANKTRANS_LOCAL", "Unauthorized checking/savings account opened in your name");
		caseLocalMap.put("BDC_LOCAL", "Birth/Death Certificate");
		caseLocalMap.put("PASSPORT_LOCAL", "Passport");
		caseLocalMap.put("MI_LOCAL", "Military ID");
		caseLocalMap.put("GC_LOCAL", "Permanent Resident Card (Green Card)");
		caseLocalMap.put("FCH_LOCAL", "False Criminal History");
		caseLocalMap.put("WI_LOCAL", "Warrant issued");
		caseLocalMap.put("IRS_LOCAL", "IRS notification");
		
		caseLink.put("p1", "/html/11_unauthWDCheckSavings00.html");
		caseLink.put("p2", "/html/12_unauthCheckAcct00.html");
		caseLink.put("p3", "/html/07_benefitsFraud00.html");
		caseLink.put("p4", "/html/13_unauthUseCCard00.html");
		caseLink.put("p5", "/html/15_unauthAcctCreditReport00.html");
		caseLink.put("p6", "/html/16_unauthLoan00.html");
		caseLink.put("p7", "/html/17_collectionAgency00.html");
		caseLink.put("p8", "/html/18_lostDriversLicense00.html");
		caseLink.put("p9", "/html/19_birthDeathCert00.html");
		caseLink.put("p10", "/html/20_passport00.html");
		caseLink.put("p11", "/html/21_lostMilitaryID00.html");
		caseLink.put("p12", "/html/22_greenCard00.html");
		caseLink.put("p13", "/html/23_socialSecurityCard00.html");
		caseLink.put("p14", "/html/24telephone00.html");
		caseLink.put("p15", "/html/25utilitiesFraud00.html");
		caseLink.put("p16", "/html/26_criminalHistory00.html");
		caseLink.put("p17", "/html/27_warrantIssued00.html");
		caseLink.put("p18", "/html/28falseCivilJudgement00.html");
		caseLink.put("p19", "/html/29IRSNotification00.html");
		caseLink.put("p20", "/html/30emailPhishing00.html");
		caseLink.put("p21", "/html/31other00.html");
		
		// for phase 1 (ComplaintWizardController.java)
		fraudLink.put("BANKTRANS", "/html/11_unauthWDCheckSavings00.html");
		fraudLink.put("BANKTRANS_LOCAL", "/html/12_unauthCheckAcct00.html");
		fraudLink.put("BENEFITS", "/html/07_benefitsFraud00.html");
		fraudLink.put("UNAUTHCREDIT", "/html/13_unauthUseCCard00.html");
		fraudLink.put("CREDITREPORT", "/html/15_unauthAcctCreditReport00.html");
		fraudLink.put("LOAN", "/html/16_unauthLoan00.html");
		fraudLink.put("COLLECTOR", "/html/17_collectionAgency00.html");
		fraudLink.put("IDLOSTSTOLEN", "/html/18_lostDriversLicense00.html");
		fraudLink.put("BDC_LOCAL", "/html/19_birthDeathCert00.html");
		fraudLink.put("PASSPORT_LOCAL", "/html/20_passport00.html");
		fraudLink.put("MI_LOCAL", "/html/21_lostMilitaryID00.html");
		fraudLink.put("GC_LOCAL", "/html/22_greenCard00.html");
		fraudLink.put("SSN", "/html/23_socialSecurityCard00.html");
		fraudLink.put("TELEPHONE", "/html/24telephone00.html");
		fraudLink.put("UTILITIES", "/html/25utilitiesFraud00.html");
		fraudLink.put("FCH_LOCAL", "/html/26_criminalHistory00.html");
		fraudLink.put("WI_LOCAL", "/html/27_warrantIssued00.html");
		fraudLink.put("SUIT", "/html/28falseCivilJudgement00.html");
		fraudLink.put("IRS_LOCAL", "/html/29IRSNotification00.html");
		fraudLink.put("EMAIL", "/html/30emailPhishing00.html");
		fraudLink.put("OTHER_IDTHEFT", "/html/31other00.html");

		// for phase 2 (ComplaintWizarController.java)
		fraudLink2.put("BANKTRANS", "/claims/selectClaimItem.shtml");
		fraudLink2.put("BANKTRANS_LOCAL", "/html/12_unauthCheckAcct05.html");
		fraudLink2.put("BENEFITS", "/claims/BenefitSelect.html");
		fraudLink2.put("UNAUTHCREDIT", "/claims/UnauthCreditSelect.html");
		fraudLink2.put("CREDITREPORT", "/claims/CreditReportSelect.html");
		fraudLink2.put("LOAN", "/claims/LoanSelect.html");
		fraudLink2.put("COLLECTOR", "/claims/CollectionAgenciesSelect.html");
		fraudLink2.put("IDLOSTSTOLEN", "/claims/IdSelect.html");
		fraudLink2.put("BDC_LOCAL", "/html/19_birthDeathCert01.jsp");
		fraudLink2.put("PASSPORT_LOCAL", "/html/20_passport01.jsp");
		fraudLink2.put("MI_LOCAL", "/html/21_lostMilitaryID05.jsp");
		fraudLink2.put("GC_LOCAL", "/html/22_greenCard01.jsp");
		fraudLink2.put("SSN", "/claims/selectClaimItem.shtml");
		fraudLink2.put("TELEPHONE", "/claims/TelephoneSelect.html");
		fraudLink2.put("UTILITIES", "/claims/UtilitiesSelect.html");
		fraudLink2.put("FCH_LOCAL", "/html/26_criminalHistory04.jsp");
		fraudLink2.put("WI_LOCAL", "/html/27_warrantIssued05.jsp");
		fraudLink2.put("SUIT", "/claims/SuitJudgementSelect.html");
		fraudLink2.put("IRS_LOCAL", "/html/29IRSNotification06.jsp");
		fraudLink2.put("EMAIL", "/claims/EmailSelect.html");
		fraudLink2.put("OTHER_IDTHEFT", "/claims/OtherIdTheftSelect.html");
		
		// for ComplaintItemController
		fraudLink3.put("BANKTRANS", 1);
		fraudLink3.put("BANKTRANS_LOCAL", 2);
		fraudLink3.put("BENEFITS", 3);
		fraudLink3.put("UNAUTHCREDIT", 4);
		fraudLink3.put("CREDITREPORT", 5);
		fraudLink3.put("LOAN", 6);
		fraudLink3.put("COLLECTOR", 7);
		fraudLink3.put("IDLOSTSTOLEN", 8);
		fraudLink3.put("BDC_LOCAL", 9);
		fraudLink3.put("PASSPORT_LOCAL", 10);
		fraudLink3.put("MI_LOCAL", 11);
		fraudLink3.put("GC_LOCAL", 12);
		fraudLink3.put("SSN", 13);
		fraudLink3.put("TELEPHONE", 14);
		fraudLink3.put("UTILITIES", 15);
		fraudLink3.put("FCH_LOCAL", 16);
		fraudLink3.put("WI_LOCAL", 17);
		fraudLink3.put("SUIT", 18);
		fraudLink3.put("IRS_LOCAL", 19);
		fraudLink3.put("EMAIL", 20);
		fraudLink3.put("OTHER_IDTHEFT", 21);
		
		// for ComplaintSubItemController
		fraudLink4.put("accou", 1);
		fraudLink4.put("trans", 2);
		fraudLink4.put("suspect", 3);
	}
	
	public static final String LOCAL_CASES = "2,9,10,11,12,16,17,19";

	public static Map<String, String> getCaseMap() {
		return caseMap;
	}

	public static Map<String, String> getCaseWebMap() {
		return caseWebMap;
	}

	public static Map<String, String> getCaseLocalMap() {
		return caseLocalMap;
	}
	
	public static Map<String, String> getCaseLink() {
		return caseLink;
	}
	
	public static Map<String, String> getFraudLink() {
		return fraudLink;
	}
	
	public static Map<String, String> getFraudLink2() {
		return fraudLink2;
	}
	
	public static Map<String, Integer> getFraudLink3() {
		return fraudLink3;
	}
	
	public static Map<String, Integer> getFraudLink4() {
		return fraudLink4;
	}

}
