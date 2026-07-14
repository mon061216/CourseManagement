package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        String uri = req.getRequestURI();
        String action = req.getParameter("action");
        
        // Exclude static resources and public pages
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg") 
            || uri.endsWith("login.jsp") || uri.endsWith("Register.jsp") || uri.endsWith("README.md")
            || uri.endsWith("LoginController") || uri.endsWith("Register") || uri.endsWith("/")
            || (uri.endsWith("MainController") && ("Login".equals(action) || "Register".equals(action))) ) {
            chain.doFilter(request, response);
            return;
        }
        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        System.out.println(loggedIn);        
        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}
