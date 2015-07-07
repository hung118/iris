package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;

/**
 * Factory class to process fraud.
 * 
 * @author hnguyen
 *
 */
public class FraudFactory {
	
	public static Fraud getInstance(FraudEnum fraudType, ReportedFraudsForm formBean, HttpSession session) throws Exception {
		Fraud fraud = null;
		
		switch (fraudType) {
		case BANKTRANS:
			fraud = new Banktrans(formBean, session);
			break;
		case BENEFITS:
			fraud = new Benefits(formBean, session);
			break;
		case UNAUTHCREDIT:
			fraud = new Unauthcredit(formBean, session);
			break;
		case CREDITREPORT:
			fraud = new Creditreport(formBean, session);
			break;
		case LOAN:
			fraud = new Loan(formBean, session);
			break;
		case COLLECTOR:
			fraud = new Collector(formBean, session);
			break;
		case IDLOSTSTOLEN:
			fraud = new Idloststolen(formBean, session);
			break;			
		case SSN:
			fraud = new Ssn(formBean, session);			
			break;
		case TELEPHONE:
			fraud = new Telephone(formBean, session);			
			break;
		case UTILITIES:
			fraud = new Utilities(formBean, session);			
			break;
		case SUIT:
			fraud = new Suit(formBean, session);			
			break;
		case EMAIL:
			fraud = new Email(formBean, session);			
			break;
		case OTHER_IDTHEFT:
			fraud = new Other_idtheft(formBean, session);			
			break;
		case suspect:
			fraud = new Suspect(formBean, session);
			break;
		case accou:
			fraud = new Accou(formBean, session);			
			break;			
		case trans:
			fraud = new Trans(formBean, session);			
			break;			
		case tranx:
			fraud = new Tranx(formBean, session);			
			break;
		case issue:
			fraud = new Issue(formBean, session);			
			break;	
		case colle:
			fraud = new Colle(formBean, session);			
			break;	
		case credi:
			fraud = new Credi(formBean, session);			
			break;	

		default:
			break;
		}
		
		return fraud;
	}
}
