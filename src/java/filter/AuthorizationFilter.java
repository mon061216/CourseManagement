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
import model.user.UserDTO;

public class AuthorizationFilter implements Filter {

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
        
        if (session != null && session.getAttribute("user") != null) {
            UserDTO user = (UserDTO) session.getAttribute("user");
            String roleID = user.getRoleID();
            
            // Basic Role Checking Logic
            if (uri.contains("/admin/") || uri.endsWith("CreateCourse") || uri.endsWith("UpdateCourse") || uri.endsWith("DeleteCourse") || uri.endsWith("CreateClasses") || uri.endsWith("UpdateClasses") || uri.endsWith("DeleteClasses")) {
                if (!"AD".equals(roleID)) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admin role required.");
                    return;
                }
            } else if (uri.contains("/teacher/") || uri.endsWith("TeacherClasses") || uri.endsWith("TeacherStudents") || uri.endsWith("CreateAssignment") || uri.endsWith("UpdateAssignment") || uri.endsWith("GradeSubmission")) {
                if (!"GV".equals(roleID) && !"AD".equals(roleID)) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Teacher role required.");
                    return;
                }
            } else if (uri.contains("/student/") || uri.endsWith("EnrolClasses") || uri.endsWith("MyClassesServlet") || uri.endsWith("StudentScheduleServlet")) {
                if (!"SV".equals(roleID) && !"AD".equals(roleID)) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Student role required.");
                    return;
                }
            }
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
