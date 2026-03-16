/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.common.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.user.UserDAO;
import model.user.UserDTO;

/**
 *
 * @author PC
 */
public class UpdateUser extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "MainController?action=ShowUser";

        try {

            String userID = request.getParameter("userID");
            String roleID = request.getParameter("roleID");
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String dobStr = request.getParameter("dob");
            String mail = request.getParameter("mail");
            String phone = request.getParameter("phoneNumber");
            String address = request.getParameter("address");
            String stateStr = request.getParameter("userState");

           
            Date dob = null;
            if (dobStr != null && !dobStr.isEmpty()) {
                dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
            }

            // parse boolean
            boolean userState = Boolean.parseBoolean(stateStr);

            
            UserDAO dao = new UserDAO();
            UserDTO oldUser = dao.getObjectByID(userID);

            UserDTO user = new UserDTO(userID,roleID, username,oldUser.getPasswordHash(), fullname, dob,address,mail, phone,userState );

            boolean check = dao.update(user);
             if (check) {
                request.setAttribute("MSG", "Update user successfully!");
            } else {
                request.setAttribute("MSG", "Update failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MSG", "Error: " + e.getMessage());
        }

        request.getRequestDispatcher("MainController?action=ShowUser").forward(request, response);
    
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
