/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin.classes;

import java.io.IOException;
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
public class CreateClasses extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "admin/Classes.jsp";
    private static final String ERROR = "admin/CreateClasses.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String classID = request.getParameter("classID");
            String courseID = request.getParameter("courseID");
            String className = request.getParameter("className");
            int classCapacity = Integer.parseInt(request.getParameter("classCapacity"));
            java.sql.Date classStartDate = java.sql.Date.valueOf(request.getParameter("classStartDate"));
            java.sql.Date classEndDate = java.sql.Date.valueOf(request.getParameter("classEndDate"));
            String academicTerm = request.getParameter("academicTerm");
            String teacherID = request.getParameter("teacherID");
            String url = ERROR;
            
            System.out.println(courseID);
            HttpSession session = request.getSession(false);
            String role = (String) session.getAttribute("rolename");
            
            if (session == null || !"admin".equals(role.trim())) {
                request.setAttribute("MSG", "Not this role");
            } else {
                ClassesDAO dao = new ClassesDAO();
                boolean check = dao.create(new ClassesDTO(classID, courseID, className, classCapacity, classStartDate, classEndDate, academicTerm, true, teacherID));
                if (check) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            log("Error at AddCourseController: " + e.getMessage(), e);

        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
