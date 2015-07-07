package gov.utah.iris.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRScriptletException;

public class BanktransScriptlet extends CommonScriptlet {

	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException {
		
		// set account holder and transaction variables in report
		setSubItemVar();

		// set suspect variables	
		setSuspectVar();
	}
	
	@SuppressWarnings("unchecked")
	private void setSubItemVar() throws JRScriptletException {
		
		Collection subItemList = (Collection) this.getParameterValue("subItemList");
		
		if (subItemList == null) return;
		
		Iterator it1 = subItemList.iterator();
		while (it1.hasNext()) {
			Map item = (Map)it1.next();
			
			// Account holder information item
			if (item.get("accou") != null) {
				Collection accou = (Collection)item.get("accou");
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				Iterator it2 = accou.iterator();
				while (it2.hasNext()) {
					Map field = (Map)it2.next();
					
					String fullName = (String)field.get("accountHolderFirstName") + " " + 
					(String)field.get("accountHolderMiddleName") + " " +
					(String)field.get("accountHolderLastName");
					String type = (String)field.get("accountHolderType");
					
					sb1.append(fullName + "\n");
					sb2.append(type + "\n");
				}
				this.setVariableValue("accountHolderName", sb1.toString());
				this.setVariableValue("accountHolderType", sb2.toString());
			}
			
			// Transaction information item
			if (item.get("trans") != null) {
				Collection trans = (Collection)item.get("trans");
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				StringBuffer sb3 = new StringBuffer();
				Iterator it2 = trans.iterator();
				while (it2.hasNext()) {
					Map field = (Map)it2.next();
					String transDate = (String)field.get("transDate");
					String transAmount = (String)field.get("transAmount");
					String transType = (String)field.get("transAccountType");
					
					sb1.append(transDate + "\n");
					sb2.append("$" + transAmount + "\n");
					sb3.append(transType + "\n");
				}
				this.setVariableValue("transDate", sb1.toString());
				this.setVariableValue("transAmount", sb2.toString());
				this.setVariableValue("transType", sb3.toString());
			}
		}
	}	
}
