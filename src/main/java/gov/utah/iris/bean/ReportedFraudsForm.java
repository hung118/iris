package gov.utah.iris.bean;

import java.util.HashMap;
import java.util.Map;

public class ReportedFraudsForm extends BaseBean {
	
	Map<String, String> victim = new HashMap<String, String>();
	Map<String, String> banktrans = new HashMap<String, String>();
	Map<String, String> benefits = new HashMap<String, String>();
	Map<String, String> unauthcredit = new HashMap<String, String>();
	Map<String, String> creditreport = new HashMap<String, String>();
	Map<String, String> loan = new HashMap<String, String>();
	Map<String, String> collector = new HashMap<String, String>();
	Map<String, String> idloststolen = new HashMap<String, String>();
	Map<String, String> ssn = new HashMap<String, String>();
	Map<String, String> telephone = new HashMap<String, String>();
	Map<String, String> utilities = new HashMap<String, String>();
	Map<String, String> suit = new HashMap<String, String>();
	Map<String, String> email = new HashMap<String, String>();
	Map<String, String> other_idtheft = new HashMap<String, String>();
	
	// subitems
	Map<String, String> accou = new HashMap<String, String>();
	Map<String, String> trans = new HashMap<String, String>();
	Map<String, String> suspect = new HashMap<String, String>();
	Map<String, String> tranx = new HashMap<String, String>();
	Map<String, String> issue = new HashMap<String, String>();
	Map<String, String> colle = new HashMap<String, String>();
	Map<String, String> credi = new HashMap<String, String>();
	
	public Map<String, String> getVictim() {
		return victim;
	}
	public void setVictim(Map<String, String> victim) {
		this.victim = victim;
	}
	public Map<String, String> getBanktrans() {
		return banktrans;
	}
	public void setBanktrans(Map<String, String> banktrans) {
		this.banktrans = banktrans;
	}
	public Map<String, String> getSsn() {
		return ssn;
	}
	public void setSsn(Map<String, String> ssn) {
		this.ssn = ssn;
	}
	public Map<String, String> getAccou() {
		return accou;
	}
	public void setAccou(Map<String, String> accou) {
		
		this.accou = accou;
	}
	public Map<String, String> getTrans() {
		return trans;
	}
	public void setTrans(Map<String, String> trans) {
		this.trans = trans;
	}
	public Map<String, String> getSuspect() {
		return suspect;
	}
	public void setSuspect(Map<String, String> suspect) {
		this.suspect = suspect;
	}
	public Map<String, String> getBenefits() {
		return benefits;
	}
	public void setBenefits(Map<String, String> benefits) {
		this.benefits = benefits;
	}
	public Map<String, String> getUnauthcredit() {
		return unauthcredit;
	}
	public void setUnauthcredit(Map<String, String> unauthcredit) {
		this.unauthcredit = unauthcredit;
	}
	public Map<String, String> getTranx() {
		return tranx;
	}
	public void setTranx(Map<String, String> tranx) {
		this.tranx = tranx;
	}
	public Map<String, String> getCreditreport() {
		return creditreport;
	}
	public void setCreditreport(Map<String, String> creditreport) {
		this.creditreport = creditreport;
	}
	public Map<String, String> getIssue() {
		return issue;
	}
	public void setIssue(Map<String, String> issue) {
		this.issue = issue;
	}
	public Map<String, String> getLoan() {
		return loan;
	}
	public void setLoan(Map<String, String> loan) {
		this.loan = loan;
	}
	public Map<String, String> getCollector() {
		return collector;
	}
	public void setCollector(Map<String, String> collector) {
		this.collector = collector;
	}
	public Map<String, String> getColle() {
		return colle;
	}
	public void setColle(Map<String, String> colle) {
		this.colle = colle;
	}
	public Map<String, String> getCredi() {
		return credi;
	}
	public void setCredi(Map<String, String> credi) {
		this.credi = credi;
	}
	public Map<String, String> getIdloststolen() {
		return idloststolen;
	}
	public void setIdloststolen(Map<String, String> idloststolen) {
		this.idloststolen = idloststolen;
	}
	public Map<String, String> getTelephone() {
		return telephone;
	}
	public void setTelephone(Map<String, String> telephone) {
		this.telephone = telephone;
	}
	public Map<String, String> getUtilities() {
		return utilities;
	}
	public void setUtilities(Map<String, String> utilities) {
		this.utilities = utilities;
	}
	public Map<String, String> getSuit() {
		return suit;
	}
	public void setSuit(Map<String, String> suit) {
		this.suit = suit;
	}
	public Map<String, String> getEmail() {
		return email;
	}
	public void setEmail(Map<String, String> email) {
		this.email = email;
	}
	public Map<String, String> getOther_idtheft() {
		return other_idtheft;
	}
	public void setOther_idtheft(Map<String, String> other_idtheft) {
		this.other_idtheft = other_idtheft;
	}
}
