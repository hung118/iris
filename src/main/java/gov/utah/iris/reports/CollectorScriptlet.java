package gov.utah.iris.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class CollectorScriptlet extends JRDefaultScriptlet {

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
			
			// Collection agency / call information item
			if (item.get("colle") != null) {
				Collection colle = (Collection)item.get("colle");
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				Iterator it2 = colle.iterator();
				while (it2.hasNext()) {
					Map field = (Map)it2.next();
					
					String collectorName = (String)field.get("collectorName");
					String collectorAddress = (String)field.get("collectorAddress") + ", " +
						(String)field.get("collectorCity") + ", " + (String)field.get("collectorState") +
						", " + (String)field.get("collectorZipCode");
					sb1.append(collectorName + "\n");
					sb2.append(collectorAddress + "\n");
				}
				this.setVariableValue("collectorName", sb1.toString());
				this.setVariableValue("collectorAddress", sb2.toString());
			}
			
			// Creditor information item
			if (item.get("credi") != null) {
				Collection credi = (Collection)item.get("credi");
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				Iterator it2 = credi.iterator();
				while (it2.hasNext()) {
					Map field = (Map)it2.next();
					String creditorName = (String)field.get("creditorName");
					String creditorContactInfo = (String)field.get("creditorContactInfo");
					
					sb1.append(creditorName + "\n");
					sb2.append(creditorContactInfo + "\n");
				}
				this.setVariableValue("creditorName", sb1.toString());
				this.setVariableValue("creditorContactInfo", sb2.toString());
			}
		}
	}
}
