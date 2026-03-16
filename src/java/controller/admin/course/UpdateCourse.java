/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.course;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.courses.CoursesDAO;
import model.courses.CoursesDTO;

/**
 *
 * @author PC
 */
public class UpdateCourse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "admin/MyApp.jsp";
    private static final String ERROR = "admin/MyApp.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String courseID = request.getParameter("courseID");
            String courseCode = request.getParameter("courseCode");
            String courseTitle = request.getParameter("courseTitle");
            String department = request.getParameter("department");
            String courseMaterials = request.getParameter("courseMaterials");
            String courseDescription = request.getParameter("courseDescription");
            boolean courseState = Boolean.valueOf(request.getParameter("courseState"));
            String url = ERROR;

            HttpSession session = request.getSession(false);

            if (session == null || !"admin".equals(session.getAttribute("rolename"))) {
                request.setAttribute("MSG", "Not this role");
            } else {
                CoursesDAO dao = new CoursesDAO();
                CoursesDTO course = new CoursesDTO(courseID, courseCode, courseTitle, department, courseMaterials, courseDescription, courseState);
                boolean check = dao.update(course);
                System.out.println(check);
                if (check) {
                    url = SUCCESS;
//                    request.setAttribute("CourseList", dao.get);
                } else {
                    url = ERROR;
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("MSG", "System error.");
            log("Error at UpdateCourseController: " + e.getMessage(), e);
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
