package gov.utah.iris.reports;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class CommonScriptlet extends JRDefaultScriptlet {

	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException {
		
		// set suspect varibles	
		setSuspectVar();
	}
		
	@SuppressWarnings("unchecked")
	protected void setSuspectVar() throws JRScriptletException {
		
		Collection suspects = (Collection) this.getParameterValue("suspects");
		
		if (suspects == null) return;
		
		Iterator susIt = suspects.iterator();
		StringBuffer susName = new StringBuffer();
		StringBuffer susAddress = new StringBuffer();
		while (susIt.hasNext()) {
			Map suspect = (Map)susIt.next();
			
			susName.append(suspect.get("firstName") == null ? " " : (String)suspect.get("firstName"))
			.append((String)suspect.get("middleName") == null ? " " : " " + (String)suspect.get("middleName") + " ")
			.append(suspect.get("lastName") == null ? " \n" : (String)suspect.get("lastName") + "\n");
			
			susAddress.append(suspect.get("addr1Street") == null ? " " : (String)suspect.get("addr1Street"))
			.append(suspect.get("addr1City") == null ? " " : ", " + (String)suspect.get("addr1City"))
			.append(suspect.get("addr1State") == null ? " " : ", " + (String)suspect.get("addr1State"))
			.append(suspect.get("addr1Zip") == null ? " \n" : " " + (String)suspect.get("addr1Zip") + "\n");
		}
		
		this.setVariableValue("suspectName", susName.toString());
		this.setVariableValue("suspectAddress", susAddress.toString());
	}
}
