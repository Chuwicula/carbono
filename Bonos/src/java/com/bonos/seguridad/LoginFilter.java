package com.bonos.seguridad;

import com.bonos.control.UserManager;
import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginFilter implements Filter {

 /*   private static final Logger LOGGER = LoggerFactory
            .getLogger(LoginFilter.class);
*/
    public static final String LOGIN_PAGE = "/faces/login.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain) {
        //System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<FILTRO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name
        UserManager userManager = (UserManager) httpServletRequest
                .getSession().getAttribute("userManager");

        if (userManager != null) {
            System.out.println("User Manager on<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            if (userManager.isLoggedIn()) {
             //  LOGGER.debug("user is logged in");
                System.out.println("user in");
                try {
                    // user is logged in, continue request
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (IOException | ServletException ex) {
                    System.out.println("Error - LoginFilter");
                    // java.util.logging.Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
               // LOGGER.debug("");
                System.out.println("user is not logged in");
                try {
                    //String val = httpServletRequest.getContextPath() + LOGIN_PAGE;
                    String val = httpServletRequest.getContextPath();
                 //   System.out.println(val);
                    httpServletResponse.sendRedirect(val);

                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            //System.out.println("NULO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            //LOGGER.debug("userManager not found");
            // user is not logged in, redirect to login page
            httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            try {
                String val = httpServletRequest.getContextPath() + LOGIN_PAGE;
                // String val = httpServletRequest.getContextPath();
                //System.out.println(val);
                httpServletResponse.sendRedirect(val);
                //httpServletResponse.setStatus(httpServletResponse.SC_MOVED_TEMPORARILY);
                //httpServletResponse.setHeader("Location", val);
              //  System.out.println("Exito");
            } catch (IOException ex) {
                //System.out.println("Error");
                java.util.logging.Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("Redirigido");
            //System.out.println("Redirigido");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        //LOGGER.debug("LoginFilter initialized");
        //System.out.println("INICIAR*****************************-------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Override
    public void destroy() {
        // close resources
    }
}
