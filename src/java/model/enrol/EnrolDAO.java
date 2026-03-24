package model.enrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.classes.ClassesDTO;
import model.user.UserDTO;
import utils.DBUtils;

public class EnrolDAO {

    private String ADD = "INSERT INTO ENROL (userID, classID,enrolDate, enrolState, enrolNote) "
            + "VALUES (?,?,?,1,?)";
    private String UPDATE = "UPDATE ENROL SET enrolDate = ?, "
            + " enrolState = ?, enrolNote = ? WHERE userID = ? AND classID = ?";
    private String DELETE = "UPDATE ENROL SET enrolState = 0 WHERE userID = ? AND classID = ?";
    private String GET_STUDENTS_BY_CLASS
            = "SELECT u.userID, u.roleID, u.username, u.passwordHash,u.fullname, u.dateOfBirth, u.address, u.mail, u.phoneNumber, u.userState, e.enrolDate, e.enrolState, e.enrolNote "
            + "FROM USERS u "
            + "JOIN ENROL e ON u.userID = e.userID  "
            + "WHERE e.classID = ? AND e.enrolState = 1";

    public boolean create(EnrolDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, object.getUserID());
                ps.setString(2, object.getClassID());
                ps.setDate(3, new java.sql.Date(object.getEnrolDate().getTime()));
                ps.setString(4, object.getEnrolNote());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean update(EnrolDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setDate(1, new java.sql.Date(object.getEnrolDate().getTime()));
                ps.setBoolean(2, object.isEnrolState());
                ps.setString(3, object.getEnrolNote());
                ps.setString(4, object.getUserID());
                ps.setString(5, object.getClassID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean delete(EnrolDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, object.getUserID());
                ps.setString(2, object.getClassID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean checkCapacity(String classID) throws SQLException {
        boolean check = false;
        String sqlCapacity = "SELECT classCapacity FROM CLASSES WHERE classID=?";
        String sqlCount = "SELECT COUNT(*) FROM ENROL WHERE classID=? AND enrolState=1";

        try ( Connection conn = DBUtils.getConnection()) {

            int capacity = 0;
            int enrolled = 0;

            try ( PreparedStatement ps = conn.prepareStatement(sqlCapacity)) {
                ps.setString(1, classID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    capacity = rs.getInt(1);
                }
            }

            try ( PreparedStatement ps = conn.prepareStatement(sqlCount)) {
                ps.setString(1, classID);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    enrolled = rs.getInt(1);
                }
            }
            check = enrolled < capacity;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public boolean isEnrolled(String userID, String classID) throws SQLException {
        String sql = "SELECT 1 FROM ENROL WHERE userID=? AND classID=? AND enrolState=1";
        boolean check = false;
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userID);
            ps.setString(2, classID);

            ResultSet rs = ps.executeQuery();
            check = rs.next();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnrolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public List<ClassesDTO> getClassesByUser(String userID) throws SQLException {
        List<ClassesDTO> list = new ArrayList<>();

        String sql = "SELECT c.* FROM CLASSES c "
                + "JOIN ENROL e ON c.classID = e.classID "
                + "WHERE e.userID=? AND e.enrolState=1";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClassesDTO c = new ClassesDTO(rs.getString("classID"), rs.getString("courseID"), rs.getString("className"), rs.getInt("classCapacity"),
                        (java.sql.Date) rs.getDate("classStartDate"),
                        (java.sql.Date) rs.getDate("classEndDate"), rs.getString("academicTerm"), rs.getBoolean("classState"), rs.getString("teacherID"));
                list.add(c);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public ArrayList<UserDTO> getStudentsByClass(String classID) {

        ArrayList<UserDTO> list = new ArrayList<>();

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(GET_STUDENTS_BY_CLASS)) {

            ps.setString(1, classID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UserDTO user = new UserDTO(rs.getString("userID"), rs.getString("roleID"), rs.getString("username"), rs.getString("passwordHash"), rs.getString("fullname"), rs.getDate("dateOfBirth"), rs.getString("address"), rs.getString("mail"), rs.getString("phoneNumber"), rs.getBoolean("userState"));

                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
