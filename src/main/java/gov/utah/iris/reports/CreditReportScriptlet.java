package gov.utah.iris.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class CreditReportScriptlet extends JRDefaultScriptlet {

	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException {
		
		Collection subItemList = (Collection) this.getParameterValue("subItemList");
		
		if (subItemList == null) {
			return;
		}

		Iterator it1 = subItemList.iterator();
		while (it1.hasNext()) {
			Map item = (Map)it1.next();
			
			// Unauthorized Transactions Information item
			if (item.get("issue") != null) {
				Collection issue = (Collection)item.get("issue");
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				Iterator it2 = issue.iterator();
				while (it2.hasNext()) {
					Map field = (Map)it2.next();
					
					String issuerName = (String)field.get("issuerName");
					String issuerAddress = (String)field.get("issuerAddress") + ", " +
						(String)field.get("issuerCity") + ", " + (String)field.get("issuerState") +
						", " + (String)field.get("issuerZipCode");
					sb1.append(issuerName + "\n");
					sb2.append(issuerAddress + "\n");
				}
				this.setVariableValue("issuerName", sb1.toString());
				this.setVariableValue("issuerAddress", sb2.toString());
			}
		}
	}
}
