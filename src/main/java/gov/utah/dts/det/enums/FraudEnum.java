package gov.utah.dts.det.enums;

/**
 * Fraud type enum class is to be used in fraud factory pattern.
 * 
 * @author hnguyen
 *
 */
public enum FraudEnum {

	// complaint items
	BANKTRANS(1, "Unauthorized Withdrawal from Your Checking or Savings Account"),
	BENEFITS(2, "Medicaid/Medicare, Insurance, or State Benefits"),
	COLLECTOR(3, "Collection Agency"),
	CREDITREPORT(4, "Unauthorized Accounts on Credit Report"),
	EMAIL(6, "Email Identity Theft"),
	IDLOSTSTOLEN(9, "Driver's License/ID Lost, Stolen or Misused"),
	LOAN(11, "Unauthorized Loan"),
	SUIT(13, "False Civil Judgment"),
	SSN(14, "Social Security Number Theft Report"),
	UNAUTHCREDIT(15, "Unauthorized Use of Your Credit Card"),
	UTILITIES(16, "Utilities Fraud"),
	TELEPHONE(18, "Telephone Service Fraud"),
	OTHER_IDTHEFT(19, "Other Identity Theft"),
	
	// complaint sub items
	accou(20, "Account Holder"),
	trans(21, "Unauthorized Transaction on Account"),
	colle(22, "Collection Agency/Call Information"),
	credi(23, "Creditor Information"),
	issue(24, "Issuer Information"),
	tranx(25, "Unauthorized Transaction"),
	suspect(26, "Suspects");
	
	private final int id;
	private final String label;
	
	private FraudEnum(int id, String label) {
		this.label = label;
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public int getId() {
		return id;
	}

	public static FraudEnum valueOf(int id) {
		for (FraudEnum fraudEnum : FraudEnum.values()) {
			if (fraudEnum.id == id) {
				return fraudEnum;
			}
		}
		throw new IllegalArgumentException(id + " is not a valid FraudEnum");
	}
	
	public static String getString(FraudEnum fraudType) {
		String ret = "";
		switch (fraudType) {
		case BANKTRANS:
			ret = "BANKTRANS";
			break;
		case BENEFITS:
			ret = "BENEFITS";
			break;
		case UNAUTHCREDIT:
			ret = "UNAUTHCREDIT";
			break;
		case CREDITREPORT:
			ret = "CREDITREPORT";
			break;
		case LOAN:
			ret = "LOAN";
			break;
		case COLLECTOR:
			ret = "COLLECTOR";
			break;
		case IDLOSTSTOLEN:
			ret = "IDLOSTSTOLEN";
			break;
		case SSN:
			ret = "SSN";
			break;
		case TELEPHONE:
			ret = "TELEPHONE";
			break;
		case UTILITIES:
			ret = "UTILITIES";
			break;
		case SUIT:
			ret = "SUIT";
			break;
		case EMAIL:
			ret = "EMAIL";
			break;
		case OTHER_IDTHEFT:
			ret = "OTHER_IDTHEFT";
			break;
		case accou:
			ret = "accou";
			break;
		case trans:
			ret = "trans";
			break;
		case colle:
			ret = "colle";
			break;
		case credi:
			ret = "credi";
			break;
		case issue:
			ret = "issue";
			break;
		case tranx:
			ret = "tranx";
			break;
		case suspect:
			ret = "suspect";
			break;
		}
		
		return ret;
	}
}
