package controller.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.assignments.AssignmentsDAO;
import model.assignments.AssignmentsDTO;
public class Assignment extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String url = "/login.jsp";

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {

                String role = (String) session.getAttribute("rolename");

                if (role != null && (role.equalsIgnoreCase("teacher") || role.equalsIgnoreCase("student"))) {

                    String classID = request.getParameter("classID");

                    AssignmentsDAO dao = new AssignmentsDAO();
                    List<AssignmentsDTO> list = dao.getByClassID(classID);
                    System.out.println("list size:" + (list!=null ? list.size() : null));
                    request.setAttribute("ASSIGNMENT_LIST", list);
                    request.setAttribute("classID", classID);

                    if (role.equalsIgnoreCase("teacher")) {
                        url = "teacher/Assignments.jsp";
                    } else {
                        url = "student/Assignments.jsp";
                    }
                }
            }

        } catch (Exception e) {
            log("Error at TeacherAssignments: "  + e.toString());
        }

        request.getRequestDispatcher(url).forward(request, response);
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
