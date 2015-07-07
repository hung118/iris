package gov.utah.iris.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class UnAuthCreditScriptlet extends CommonScriptlet {

	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException {
		
		// set account holder and transaction variables in report
		setSubItemVar();

		// set suspect variables	
		setSuspectVar();		
	}
	
	private void setSubItemVar() throws JRScriptletException {
		Collection subItemList = (Collection) this.getParameterValue("subItemList");
		
		if (subItemList == null) {
			return;
		}

		Iterator it1 = subItemList.iterator();
		while (it1.hasNext()) {
			Map item = (Map)it1.next();
			
			// Unauthorized Transactions Information item
			if (item.get("tranx") != null) {
				Collection tranx = (Collection)item.get("tranx");
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				StringBuffer sb3 = new StringBuffer();
				Iterator it2 = tranx.iterator();
				while (it2.hasNext()) {
					Map field = (Map)it2.next();
					
					String tranxDate = (String)field.get("tranxDate");
					String tranxAmount = (String)field.get("tranxAmount");
					String tranxRecipientType = (String)field.get("tranxRecipientType");
					sb1.append(tranxDate + "\n");
					sb2.append("$" + tranxAmount + "\n");
					sb3.append(tranxRecipientType + "\n");
				}
				this.setVariableValue("tranxDate", sb1.toString());
				this.setVariableValue("tranxAmount", sb2.toString());
				this.setVariableValue("tranxRecipientType", sb3.toString());
			}
		}
	}	
}
