package model.submissions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class SubmissionsDAO {

    private String ADD = "INSERT INTO SUBMISSIONS(submissionID,assignmentID,studentID, submissionDate, submissionFile, submissionState) "
            + "VALUES (?,?,?,?,?,1)";
    private String UPDATE = "UPDATE SUBMISSIONS SET submissionDate = ?, submissionFile = ?, submissionState = ? WHERE submissionID = ?";
    private String DELETE = "UPDATE SUBMISSIONS SET submissionState = 0 WHERE submissionID = ?";
    private static final String GET_BY_ASSIGNMENT
            = "SELECT * FROM SUBMISSIONS WHERE assignmentID = ? AND submissionState = 1";

    private static final String GET_BY_STUDENT
            = "SELECT * FROM SUBMISSIONS WHERE studentID = ? AND submissionState = 1";

    public boolean create(SubmissionsDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, object.getSubmissionID());
                ps.setString(2, object.getAssignmentID());
                ps.setString(3, object.getStudentID());
                ps.setDate(4, new java.sql.Date(object.getSubmissionDate().getTime()));
                ps.setString(5, object.getSubmissionFile());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SubmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean update(SubmissionsDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setDate(1, (Date) object.getSubmissionDate());
                ps.setString(2, object.getSubmissionFile());
                ps.setBoolean(3, object.isSubmissionState());
                ps.setString(4, object.getSubmissionID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SubmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean delete(SubmissionsDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, object.getSubmissionID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SubmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public ArrayList<SubmissionsDTO> getByAssignmentID(String assignmentID) {
        ArrayList<SubmissionsDTO> list = new ArrayList<>();

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_BY_ASSIGNMENT)) {

            ps.setString(1, assignmentID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SubmissionsDTO s = new SubmissionsDTO(
                        rs.getString("submissionID"),
                        rs.getString("assignmentID"),
                        rs.getString("studentID"),
                        rs.getDate("submissionDate"),
                        rs.getString("submissionFile"),
                        rs.getBoolean("submissionState")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public SubmissionsDTO getSubmissionByID(String submissionID) throws Exception {

        SubmissionsDTO sub = null;

        String sql = "SELECT * FROM SUBMISSIONS WHERE submissionID=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, submissionID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                sub = new SubmissionsDTO(
                        rs.getString("submissionID"),
                        rs.getString("assignmentID"),
                        rs.getString("studentID"),
                        rs.getTimestamp("submissionDate"),
                        rs.getString("submissionFile"),
                        rs.getBoolean("submissionState")
                );
            }
        }

        return sub;
    }
}
