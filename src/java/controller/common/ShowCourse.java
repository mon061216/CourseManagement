package controller.common;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.courses.CoursesDAO;
import model.courses.CoursesDTO;
import model.user.UserDTO;

public class ShowCourse extends HttpServlet {

    private static final String ADMIN = "admin/MyApp.jsp";
    private static final String STUDENT = "student/MyApp.jsp";
    private static final String TEACHER = "teacher/MyApp.jsp";

    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            CoursesDAO dao = new CoursesDAO();
            ArrayList<CoursesDTO> list = dao.getActiveList();
            if (!list.isEmpty() && list != null) {
                request.setAttribute("CourseList", list);
               // System.out.println("List size: " + (list != null ? list.size() : "null"));
            } else {
                request.setAttribute("MSG", "No course");
            }
            HttpSession session = request.getSession(false);

            UserDTO user = (session != null) ? ((UserDTO) session.getAttribute("user")) : null;
            if (user != null) {
                switch (user.getRoleID().trim()) {
                    case "AD":
                        url = ADMIN;
                        break;
                    case "GV":
                        url = TEACHER;
                        break;
                    case "SV":
                        url = STUDENT;
                        break;
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
