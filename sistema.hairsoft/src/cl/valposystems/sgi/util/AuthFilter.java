package cl.valposystems.sgi.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Servlets;
 
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
    
    public AuthFilter() {}
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String loginURL   = request.getContextPath() + "/login.xhtml";
        String errorUrl   = request.getContextPath() + "/error.xhtml";
        
        String indexUrl   = request.getContextPath() + "/index.xhtml";
        String clienteURL = request.getContextPath() + "/cliente.xhtml";
        String itoURL     = request.getContextPath() + "/ito.xhtml";
        String managerURL = request.getContextPath() + "/manager.xhtml";

        HttpSession session = request.getSession(false);

        try {
        	boolean raiz = request.getRequestURI().equals(request.getContextPath() + "/");
	        boolean loggedIn = (session != null) && (session.getAttribute("usuario") != null);
	        
	        boolean loginRequest   = request.getRequestURI().equals(loginURL);
	        boolean errorRequest   = request.getRequestURI().equals(errorUrl);
	        
	        
	        
	        boolean resourceRequest = Servlets.isFacesResourceRequest(request);

	        if (loggedIn || loginRequest || errorRequest || resourceRequest || raiz) {
	        	if ((loginRequest && loggedIn) || (raiz && loggedIn)) { 
	        		if (session != null) {
	        			//Validar el ROL y redirigir a las vista correspondientes
	        			Servlets.facesRedirect(request, response, indexUrl);
	        		}
	        	} else {
	        		if (!resourceRequest) { // Prevent browser from caching restricted resources. See also http://stackoverflow.com/q/4194207/157882
	                    Servlets.setNoCacheHeaders(response);
	                }
	        		chain.doFilter(request, response);
	        	}
	        } else {
	        	if (session!= null) session.invalidate();
	            Servlets.facesRedirect(request, response, loginURL);
	        }
        
	    } catch (Exception t) {
	    	if (session!= null) session.invalidate();
	    	Servlets.facesRedirect(request, response, loginURL);  // Anonymous user. Redirect to login page
	    } 
    
    } //doFilter
 
    @Override
    public void destroy() {
         
    }
}