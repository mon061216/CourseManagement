/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.attend.AttendDAO;
import model.enrol.EnrolDAO;
import model.user.UserDTO;

/**
 *
 * @author PC
 */
public class TeacherStudents extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String ADMIN = "admin";
    private static String TEACHER = "teacher";
    private static String ADMIN_URL = "admin/Students.jsp";
    private static String TEACHER_URL = "teacher/Students.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "login.jsp";

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {

                String role = (String) session.getAttribute("rolename");

                if (role != null) {

                    String classID = request.getParameter("classID");

                    EnrolDAO dao = new EnrolDAO();
                    System.out.println(classID);
                    ArrayList<UserDTO> list = dao.getStudentsByClass(classID);

                    AttendDAO attendDAO = new AttendDAO();

                    Map<String, Double> attendanceMap = new HashMap<>();

                    for (UserDTO u : list) {
                        double p = attendDAO.getAttendancePercent(classID, u.getUserID());
                        attendanceMap.put(u.getUserID(), p);
                    }

//                    if (list != null) {
//                        System.out.println("empty1");
//                    }
//                    if (list.isEmpty()) {
//                        System.out.println("empty");
//                    }
//                    if (attendanceMap.isEmpty()) {
//                        System.out.println("emptyAttend1");
//                    }
//                    if (attendanceMap != null) {
//                        System.out.println("emptyAttend");
//                    }
                    request.setAttribute("ATTEND_MAP", attendanceMap);
                    request.setAttribute("STUDENT_LIST", list);
                    request.setAttribute("CLASS_ID", classID);

                    if (ADMIN.equalsIgnoreCase(role.trim())) {
                        url = ADMIN_URL;
                    } else if (TEACHER.equalsIgnoreCase(role.trim())) {
                        url = TEACHER_URL;
                    }

                }
            }

        } catch (Exception e) {
           // log("Error at TeacherStudentsController: " + e.toString());
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
