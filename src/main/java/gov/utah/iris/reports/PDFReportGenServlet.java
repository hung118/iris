package gov.utah.iris.reports;

import gov.utah.iris.common.CaseConstants;
import gov.utah.iris.common.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 * Servlet generates report in PDF format.
 * Syntax:  1) PDFReport?report=<name>
 * where name is a Jasper report name (fraudTypeCd) without the extension (.jrxml) which is stored 
 * in /reports root of this application.
 * Example: PDFReport?report=BANKTRANS
 *
 * @author HNGUYEN
 * Date: August 9, 2007
 */
public class PDFReportGenServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// image name which should be in images directory at the root of application
    static final String IMAGE_NAME = "pdfbanner.jpg";
    static final String AFFIDAVIT_IMAGE_NAME = "affidavitBanner.gif";
       
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception {

        RequestDispatcher rd;
        try {
        	createReport(request, response);
        } catch (Exception e) { // show error page
            e.printStackTrace();
            rd = this.getServletContext().getRequestDispatcher("/errorPage.jsp?error=" + e.getMessage());
            rd.forward(request, response);
        }
    }
    
    /**
     * Creates pdf report using Jasper report.
     * @param request
     * @param response
     * @throws Exception
     */
	private void createReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ServletOutputStream sos = null;
        
        try {
        	String fraudTypeCd = request.getParameter("report");
        	HttpSession session = request.getSession();
        	String umdId = (String)session.getAttribute(Constants.USER_ID);
        	String trackingNumber = request.getParameter("trackingNumber");
        	if (trackingNumber == null) {
        		trackingNumber = (String)session.getAttribute("trackingNumber");
        	}
        	
            // get Jasper report xml (*.jrxml) full path file name (c:/tomcat/webapps/JasperSample/reports/sample.jrxml)
            ServletContext context = this.getServletContext();
            String pathName = null;
            if ("yes".equals(request.getParameter("affidavit"))) {	// Affidavit report
            	pathName = context.getRealPath("/reports/Affidavit");
            } else {	// form report
            	pathName = context.getRealPath("/reports/" + fraudTypeCd);
            }
                        
            // --- 1) Compile .jrxml report report design to produce .jasper file. Check its existence before compiling.
            String reportFileName = pathName + ".jasper";
            File reportFile = new File(reportFileName);
            if (!reportFile.exists()) {
            	JasperCompileManager.compileReportToFile(pathName + ".jrxml");
            }
                        
            // --- 2) Fill the compiled report design with data and produce the .jrprint object.
            Map<String, Object> parameters = new HashMap<String, Object>();
            
            if ("yes".equals(request.getParameter("affidavit"))) {	// Affidavit report
            	parameters.put("imagePathName", context.getRealPath("/img/" + AFFIDAVIT_IMAGE_NAME));
            } else {
            	parameters.put("imagePathName", context.getRealPath("/img/" + IMAGE_NAME));
            }
            
            Map<String, String> caseWebMap = CaseConstants.getCaseWebMap();
            parameters.put("reportTitle", (String)caseWebMap.get(fraudTypeCd));                        	
            
            parameters.put("incidentNumber", trackingNumber);
           
            // get data from web service or user info if report is Affidavit
            MapCollectionData data = null;
            if ("yes".equals(request.getParameter("affidavit"))) {	// Affidavit report
            	@SuppressWarnings("unchecked")
				Map<String, String> user = (Map<String, String>)session.getAttribute(Constants.USER_INFO);
            	data = new MapCollectionData(user);
            } else {		// form data from web service
                data = new MapCollectionData(umdId, trackingNumber, fraudTypeCd);
                
                // get sub item list
                if (data.isSubItem()) {
                	parameters.put("subItemList", data.getSubItemList());
                }
                
                // get suspects
                if (data.isSuspect()) {
                	parameters.put("suspects", data.getSuspectList());
                }
            }
            
            JasperPrint reportPrint = JasperFillManager.fillReport(reportFileName, 
            		parameters, new JRMapCollectionDataSource(data.getMapCollection()));
            
            // --- 3) Export a PDF version of the report by converting the .jrprint object.
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint);
            
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
