package model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class UserDAO {

    private String LOGIN = "SELECT userID,ro.roleID,fullName,dateOfBirth,address,mail,phoneNumber FROM USERS us "
            + "INNER JOIN ROLES ro ON us.roleID = ro.roleID "
            + "WHERE username = ? AND passwordHash = ? AND us.userState = 1";
    private String ADD = "INSERT INTO USERS(userID, roleID, username, passwordHash, fullName,dateOfBirth,address, mail,phoneNumber,userState) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?,1)";

    private String UPDATE = "UPDATE USERS SET username = ?, passwordHash = ?, fullName = ?, dateOfBirth = ?,address = ?, mail = ?,phoneNumber = ?,userState = ? WHERE userID = ?";
    private String DELETE = "UPDATE USERS SET userState = 0 WHERE userID = ?";
    private String CHECK_USERNAME = "SELECT username FROM USERS WHERE username = ?";
    private String GET_ROLE_NAME = "SELECT roleName FROM ROLES WHERE roleID = ?";
    private String GET_ALL_USERS = "SELECT userID, roleID, username, fullName, dateOfBirth, address, mail, phoneNumber, userState FROM USERS";
    private String GET_OBJECT_BY_ID = "SELECT * FROM USERS WHERE userID = ?";

    public UserDTO login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(LOGIN);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    Date dob = rs.getDate("dateOfBirth");
                    String address = rs.getString("address");
                    String mail = rs.getString("mail");
                    String phone = rs.getString("phoneNumber");
                    user = new UserDTO(userID, roleID, username, password, fullName, dob, address, mail, phone, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return user;
    }

    public boolean create(UserDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        UserDTO user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, object.getUserID());
                ps.setString(2, object.getRoleID());
                ps.setString(3, object.getUsername());
                ps.setString(4, object.getPasswordHash());
                ps.setString(5, object.getFullname());
//                ps.setDate(6, (java.sql.Date) object.getDob());
                if (object.getDob() != null) {
                    ps.setDate(6, new java.sql.Date(object.getDob().getTime()));
                } else {
                    ps.setNull(6, java.sql.Types.DATE);
                }
                ps.setString(7, object.getAddress());
                ps.setString(8, object.getMail());
                ps.setString(9, object.getPhoneNumber());
                check = ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean update(UserDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        UserDTO user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setString(1, object.getUsername());
                ps.setString(2, object.getPasswordHash());
                ps.setString(3, object.getFullname());
                if (object.getDob() != null) {
                    ps.setDate(4, new java.sql.Date(object.getDob().getTime()));
                } else {
                    ps.setNull(4, java.sql.Types.DATE);
                }
                ps.setString(5, object.getAddress());
                ps.setString(6, object.getMail());
                ps.setString(7, object.getPhoneNumber());
                ps.setBoolean(8, object.isUserState());
                ps.setString(9, object.getUserID());
                check = ps.executeUpdate() > 0;

            }
        } catch (Exception e) {

        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean delete(UserDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, object.getUserID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {

        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public String getRoleName(String roleID) {
        String roleName = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ROLE_NAME);
                ps.setString(1, roleID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    roleName = rs.getString("roleName");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roleName;
    }

    public boolean isUsernameExist(String username) throws Exception {
        boolean exists = false;

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(CHECK_USERNAME)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }
        }

        return exists;
    }

    public ArrayList<UserDTO> getAllUsers() throws Exception {

        ArrayList<UserDTO> list = new ArrayList<>();

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_ALL_USERS);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                UserDTO user = new UserDTO(
                        rs.getString("userID"),
                        rs.getString("roleID"),
                        rs.getString("username"),
                        null, // hide password
                        rs.getString("fullName"),
                        rs.getDate("dateOfBirth"),
                        rs.getString("address"),
                        rs.getString("mail"),
                        rs.getString("phoneNumber"),
                        rs.getBoolean("userState")
                );

                list.add(user);
            }
        }

        return list;
    }

    public UserDTO getObjectByID(String userID) throws ClassNotFoundException {
        UserDTO user = null;
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(GET_OBJECT_BY_ID)) {

            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String roleID = rs.getString("roleID");
                String username = rs.getString("username");
                String password = rs.getString("passwordHash");
                String fullName = rs.getString("fullName");
                Date dob = rs.getDate("dateOfBirth");
                String address = rs.getString("address");
                String mail = rs.getString("mail");
                String phone = rs.getString("phoneNumber");
                boolean state = rs.getBoolean("userState");
                user = new UserDTO(userID, roleID, username, password, fullName, dob, address, mail, phone, state);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
