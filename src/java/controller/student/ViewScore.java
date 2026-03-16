package controller.student;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.grade.GradesDAO;
import model.grade.GradesDTO;

public class ViewScore extends HttpServlet {

    private String ERROR = "admin/Students.jsp";
    private String SUCCESS = "admin/StudentScore.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String classID = request.getParameter("classID");
            String studentID = request.getParameter("studentID");

            GradesDAO dao = new GradesDAO();
            ArrayList<GradesDTO> arr = dao.getGradeOfStudentByClass(classID,studentID);
            if (arr != null) {
                url = SUCCESS;
                request.setAttribute("GRADE_LIST", arr);
                request.setAttribute("CLASS_ID", classID);
                request.setAttribute("STUDENT_ID", studentID);
            } else {
                request.setAttribute("MSG", "Empty list or wrong system.");
            }
        } catch (Exception e) {
            log("" + e);
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
