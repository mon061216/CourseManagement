/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.user.UserDAO;
import model.user.UserDTO;
import utils.IDUtils;

/**
 *
 * @author PC
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "Register.jsp";
    private static final String SUCCESS_ADMIN = "admin/User.jsp";
    private static final String SUCCESS_STUDENT = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;

        try {

            String roleID = request.getParameter("roleID");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String dob_raw = request.getParameter("dateOfBirth");
            String address = request.getParameter("address");
            String mail = request.getParameter("mail");
            String phone = request.getParameter("phoneNumber");

            java.sql.Date dob = null;
            if (dob_raw != null && !dob_raw.isEmpty()) {
                dob = java.sql.Date.valueOf(dob_raw);
            }
            UserDAO dao = new UserDAO();
            System.out.println(username);
            // Check username exists
            if (dao.isUsernameExist(username)) {
                request.setAttribute("MSG", "Username already exists!");
            } else {
                String userID = IDUtils.generateID("ST");
                if (roleID == null || roleID.isEmpty()) {
                    roleID = "SV";
                }
                UserDTO newUser = new UserDTO(userID, roleID, username, password, fullName, dob, address, mail, phone, true);
                boolean check = dao.create(newUser);

                if (check) {

                    HttpSession session = request.getSession(false);

                    // Nếu admin tạo user
                    if (session != null && session.getAttribute("user") != null) {
                        UserDTO loginUser = (UserDTO) session.getAttribute("user");

                        if ("AD".equals(loginUser.getRoleID())) {
                            url = SUCCESS_ADMIN;
                            request.setAttribute("MSG", "User created successfully!");
                        }
                    } else {
                        // Student tự đăng ký
                        url = SUCCESS_STUDENT;
                        request.setAttribute("MSG_LOGIN", "Register success! Please login.");
                    }

                } else {
                    request.setAttribute("MSG", "Create failed!");
                }
            }

        } catch (Exception e) {
            log(e.getMessage());
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
