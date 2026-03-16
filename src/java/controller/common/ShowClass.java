/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.classes.ClassesDAO;
import model.classes.ClassesDTO;

/**
 *
 * @author PC
 */
public class ShowClass extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String AD = "admin";
    private static final String STUDENT = "student";
    private static final String TEACHER = "teacher";

    private static final String AD_URL = "admin/Classes.jsp";
    private static final String STUDENT_URL = "student/Classes.jsp";
    private static final String TEACHER_URL = "teacher/Classes.jsp";

    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        String courseID = request.getParameter("courseID");
        String url = ERROR;
        if (session != null) {
            String role = ((String) session.getAttribute("rolename"));
            ClassesDAO dao = new ClassesDAO();
            ArrayList<ClassesDTO> arr = dao.getActiveListFromCourse(courseID);
            switch (role.trim()) {
                case AD:
                    url = AD_URL;
                    break;
                case STUDENT:
                    url = STUDENT_URL;
                    break;
                case TEACHER:
                    url = TEACHER_URL;
                    break;
            }
            request.setAttribute("courseID", courseID);
            if (arr.isEmpty() || arr == null) {
                request.setAttribute("MSG", "This list is empty.");
            } else {
                request.setAttribute("ClassList", arr);
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
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
