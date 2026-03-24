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
public class UpdateClasses extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "admin/Classes.jsp";
    private static final String ERROR = "admin/Classes.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = ERROR;

        try {
            HttpSession session = request.getSession(false);

            if (session == null || !"admin".equals(session.getAttribute("rolename"))) {
                request.setAttribute("MSG", "Access denied.");
            } else {

                String classID = request.getParameter("classID");
                String courseID = request.getParameter("courseID");
                String className = request.getParameter("className");
                String academicTerm = request.getParameter("academicTerm");
                String teacherID = request.getParameter("teacherID");
                System.out.println(academicTerm);
                int classCapacity = Integer.parseInt(request.getParameter("classCapacity"));
                java.sql.Date startDate = java.sql.Date.valueOf(request.getParameter("classStartDate"));
                java.sql.Date endDate = java.sql.Date.valueOf(request.getParameter("classEndDate"));

                boolean classState = Boolean.parseBoolean(request.getParameter("classState"));

                if (classCapacity <= 0) {
                    request.setAttribute("MSG", "Capacity must be > 0");
                } else if (endDate.before(startDate)) {
                    request.setAttribute("MSG", "End date must be after start date");
                } else {

                    ClassesDTO dto = new ClassesDTO(classID, courseID, className, classCapacity, startDate, endDate, academicTerm, classState, teacherID);

                    ClassesDAO dao = new ClassesDAO();
                    boolean check = dao.update(dto);
                    System.out.println(dto);
                    if (check) {
                        url = SUCCESS;
                        request.setAttribute("MSG", "Class updated successfully.");
                    } else {
                        request.setAttribute("MSG", "Update failed.");
                        //request.setAttribute("CLASS", dto);
                    }
                    request.setAttribute("ClassList", dao.getActiveListFromCourse(courseID));
                }
            }
        } catch (Exception e) {
            log("Error at UpdateClassController: " + e.getMessage(), e);
            request.setAttribute("MSG", "Invalid input.");
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
