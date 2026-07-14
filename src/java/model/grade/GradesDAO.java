package model.grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class GradesDAO {

    private static final String ADD = "INSERT INTO GRADES(gradeID,submissionID,teacherID, gradeScore, gradeDate) "
            + "VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE GRADES SET submissionID = ?, teacherID = ?, gradeScore = ?, gradeDate = ? WHERE gradeID = ?";
    // private String DELETE = "UPDATE GRADES SET submissionState = 0 WHERE submissionID = ?";
    private static final String GET_GRADE_OF_STUDENT_BY_CLASS = "select g.*"
            + "from CLASSES c "
            + "inner join ENROL e on c.classID = e.classID "
            + "inner join USERS u on e.userID = u.userID "
            + "inner join ASSIGNMENTS a on c.classID = a.classID "
            + "inner join SUBMISSIONS s on a.assignmentID = s.assignmentID "
            + "inner join GRADES g on g.submissionID = s.submissionID "
            + "where u.userID = ? and c.classID = ?";

    public boolean create(GradesDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, object.getGradeID());
                ps.setString(2, object.getSubmissionID());
                ps.setString(3, object.getTeacherID());
                ps.setFloat(4, object.getGradeScore());
                ps.setDate(5, new java.sql.Date(object.getGradeDate().getTime()));
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GradesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GradesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean update(GradesDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setString(1, object.getSubmissionID());
                ps.setString(2, object.getTeacherID());
                ps.setFloat(3, object.getGradeScore());
                ps.setDate(4, new java.sql.Date(object.getGradeDate().getTime()));
                ps.setString(5, object.getGradeID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GradesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GradesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean delete(GradesDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<GradesDTO> getGradesByStudent(String studentID) throws SQLException {

        ArrayList<GradesDTO> list = new ArrayList<>();

        String sql
                = "SELECT g.* FROM GRADES g "
                + "JOIN SUBMISSIONS s ON g.submissionID = s.submissionID "
                + "WHERE s.studentID=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GradesDTO g = new GradesDTO(
                        rs.getString("gradeID"),
                        rs.getString("submissionID"),
                        rs.getString("teacherID"),
                        rs.getFloat("gradeScore"),
                        rs.getDate("gradeDate")
                );
                list.add(g);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GradesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ArrayList<GradesDTO> getGradeOfStudentByClass(String classID, String studentID) throws ClassNotFoundException, SQLException {
        ArrayList<GradesDTO> grade = new ArrayList<>();
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_GRADE_OF_STUDENT_BY_CLASS)) {
            ps.setString(1, studentID);
            ps.setString(2, classID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String gradeID = rs.getString("gradeID");
                String teacherID = rs.getString("teacherID");
                String submissionID = rs.getString("submissionID");
                float gradeScore = rs.getFloat("gradeScore");
                java.sql.Date gradeDate = rs.getDate("gradeDate");
                grade.add(new GradesDTO(gradeID, submissionID, teacherID, gradeScore, gradeDate));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GradesDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
        return grade;
    }

}
