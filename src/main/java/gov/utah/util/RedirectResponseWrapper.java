/*
 * RedirectResponseWrapper.java
 *
 * Created on December 12, 2006, 11:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package gov.utah.util;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * The response wrapper class redirects http to https used in filter class.
 *
 * @author HNGUYEN
 */
public class RedirectResponseWrapper extends HttpServletResponseWrapper {
    
    private boolean redirect = false;
    private String serverName = null;
    
    /** Creates a new instance of RedirectResponseWrapper */
    public RedirectResponseWrapper(HttpServletResponse httpServletResponse) {
        super(httpServletResponse);
    }
    
    /** Overide default sendRedirect method. */
    public void sendRedirect(String location) throws IOException {
        
        String targetURL = null;
        if (redirect) {
            super.sendRedirect("https://" + serverName + location);
        } else {
            super.sendRedirect(location);
        }
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
