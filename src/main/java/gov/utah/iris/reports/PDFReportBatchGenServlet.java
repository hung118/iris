package gov.utah.iris.reports;

import gov.utah.iris.common.CaseConstants;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.PersistantManagerImpl;
import gov.utah.iris.util.ComplaintItemUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 * Servlet generates multiple reports (*.jxml) in single PDF. Called from Complaint Report
 * button.
 * Syntax:  PDFReportBatch
 * 
 * @author HNGUYEN
 * Date: August 9, 2007
 */

public class PDFReportBatchGenServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// image name which should be in images directory at the root of application
    static final String IMAGE_NAME = "pdfbanner.jpg";
           
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception {

        RequestDispatcher rd;
        try {
        	HttpSession session = request.getSession();
        	String trackingNumber = request.getParameter("trackingNumber");
        	@SuppressWarnings("unchecked")
			String umdId = ((Map<String, String>)session.getAttribute(Constants.USER_INFO)).get(Constants.UMD_ID);
        	
        	// get complaint details from WS
        	Collection<Map<String, String>> complaintDetails = getComplaintDetails(trackingNumber, umdId);
        	
        	// create report
        	createReport(request, response, complaintDetails, trackingNumber, umdId);

        } catch (Exception e) { // show error page
            e.printStackTrace();
            rd = this.getServletContext().getRequestDispatcher("/errorPage.jsp");
            rd.forward(request, response);
        }
    }
    
    /**
     * Creates pdf report using Jasper report.
     * @param request
     * @param response
     * @param complaintDetails
     * @param trackingNumber
     * @param umdId
     * @throws Exception
     */
    private void createReport(HttpServletRequest request, HttpServletResponse response, 
    		Collection<Map<String, String>> complaintDetails, String trackingNumber, String umdId) throws Exception {
        
        ServletOutputStream sos = null;
        
        try {
        	List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
        	
        	Iterator<Map<String, String>> it = complaintDetails.iterator();
        	while (it.hasNext()) {
        		Map<String, String> details = (Map<String, String>)it.next();
        		
        		String fraudTypeCd = (String)details.get("fraudTypeCd");
                // get Jasper report xml (*.jrxml) full path file name (c:/tomcat/webapps/JasperSample/reports/sample.jrxml)
                ServletContext context = this.getServletContext();
               	String pathName = context.getRealPath("/reports/" + fraudTypeCd);
        		
                // 1) Compile .jrxml report design to produce .jasper file
                JasperReport reportJasper = JasperCompileManager.compileReport(pathName + ".jrxml");
        		
                // 2) Fill the compiled report design with data and produce the .jrprint file.
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("imagePathName", context.getRealPath("/img/" + IMAGE_NAME));
                Map<String, String> caseWebMap = CaseConstants.getCaseWebMap();
                parameters.put("reportTitle", (String)caseWebMap.get(fraudTypeCd));
                parameters.put("incidentNumber", trackingNumber);
                
                // get and fill user information
                @SuppressWarnings("unchecked")
				Map<String, String> user = (Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO);
                parameters.put("victimName", (String)user.get("firstName") + " " + (String)user.get("lastName"));
                parameters.put("victimStreetAddr", (String)user.get("addr1Street"));
                parameters.put("victimCityStateZip", (String)user.get("addr1City") + ", " + (String)user.get("addr1State") + " " + (String)user.get("addr1Zip"));
                parameters.put("victimEmail", (String)user.get("emailAddress"));
                parameters.put("victimPhone", (String)user.get("dayPhone"));
                
               
                // get data from web service and produce .jrprint file 
                MapCollectionData data = null;
                data = new MapCollectionData(umdId, trackingNumber, fraudTypeCd);
                // get sub item
                if (data.isSubItem()) {
                	parameters.put("subItemList", data.getSubItemList());
                }
                // get suspects
                if (data.isSuspect()) {
                	parameters.put("suspects", data.getSuspectList());
                }
                
                // add to jasperPrintList array for batch PDF export
                JasperPrint reportPrint = JasperFillManager.fillReport(reportJasper, 
                		parameters, new JRMapCollectionDataSource(data.getMapCollection()));
                
                jasperPrintList.add(reportPrint);
        	}
            
            // 3) Batch PDF export using array list of .jrprint objects
            JRPdfExporter exporter = new JRPdfExporter();
            
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
			exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            
            exporter.exportReport();
            byte[] reportBytes = baos.toByteArray();
            
            // display PDF report on client browser
            response.setContentType("application/pdf");
            response.setContentLength(reportBytes.length);
            sos = response.getOutputStream();
            
            sos.write(reportBytes, 0, reportBytes.length);
            sos.flush();   
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception(e.getMessage());
        } finally {
            if (sos != null) {
                sos.close();
            }
        }
    }
    
	private Collection<Map<String, String>> getComplaintDetails(String trackingNumber, String umdId) throws Exception {
    	
        if ( trackingNumber != null && !trackingNumber.equals("") ) {
            // lookup tracking number
            PersistantManagerImpl manager = new PersistantManagerImpl();
            Map<String, String> ids = new HashMap<String, String>();
            ids.put(Constants.UMD_ID, umdId);
            ids.put("trackingNumber", trackingNumber);
            Map<String, Object> complaintDetailsWS = manager.select(ids,"complaintDetails");

            @SuppressWarnings("unchecked")
			Collection<Map<String, String>> complaintDetailsCollection = (Collection<Map<String, String>>)complaintDetailsWS.get("complaintDetails");
            return ComplaintItemUtil.removeDuplicateComplaintItems(complaintDetailsCollection);
        }
        else {
        	throw new Exception("Tracking Number is null!");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
