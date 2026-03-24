/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            String url = "login.jsp";
            System.out.println(action);
            if (action != null) {
                switch (action.trim()) {
                    case "Login":
                        url = "LoginController";
                        break;

                    case "Logout":
                        url = "LogoutController";
                        break;

                    //Course
                    case "UpdateCourse":
                        url = "UpdateCourse";
                        break;
                    case "DeleteCourse":
                        url = "DeleteCourse";
                        break;
                    case "ShowCourse":
                        url = "ShowCourse";
                        break;
                    case "CreateCourse":
                        url = "CreateCourse";
                        break;
                    // Class
                    case "ShowClass":
                        url = "ShowClass";
                        break;
                    case "UpdateClasses":
                        url = "UpdateClasses";
                        break;
                    case "DeleteClasses":
                        url = "DeleteClasses";
                        break;
                    case "CreateClasses":
                        url = "CreateClasses";
                        break;
                    //User
                    case "ShowUser":
                        url = "ShowUser";
                        break;
                    case "UpdateUser":
                        url = "UpdateUser";
                        break;
                    case "DeleteUser":
                        url = "DeleteUser";
                        break;
                    case "Register":
                        url = "Register";
                        break;
                    case "EnrolClass":
                        url = "EnrolClasses";
                        break;
                    case "MyClasses":
                        url = "MyClassesServlet";
                        break;
                    case "ViewStudents":
                        url = "TeacherStudents";
                        break;
                    case "Assignment":
                        url = "Assignment";
                        break;
                    case "SaveAssignment":
                        url = "CreateAssignment";
                        break;
                    case "BackToMyClasses":
                        url = "TeacherClasses";
                        break;
                    case "UpdateAssignment":
                        url = "UpdateAssignment";
                        break;
                    case "ViewSubmissions":
                        url = "Submissions";
                        break;
                    case "GradeSubmission":
                        url = "GradeSubmission";
                        break;
                    case "ViewScore":
                        url = "ViewScore";
                        break;
                    case "ViewSubmission":
                        url = "ViewSubmission";
                        break;
                    case "ViewSubmissionOfAStudent":
                        url = "ViewSubmissionOfAStudent";
                        break;
                    case "ViewSchedule":
                        url = "viewSchedule";
                        break;
                    case "TakeAttendance":
                        url = "TakeAttendance";
                        break;
                    case "SaveAttendance":
                        url = "SaveAttendance";
                        break;
                    case "editUser":
                        url = "ShowUser";
                        break;
                    case "Profile":
                        url = "Profile";
                        break;
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            log(e + "");
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
