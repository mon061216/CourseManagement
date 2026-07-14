/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PC
 */
public class IDUtils {

    public static String generateID(String prefix) throws Exception {

        String newID = prefix + "001";

        String sql = "SELECT MAX(userID) FROM USERS WHERE userID LIKE ?";

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, prefix + "%");
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getString(1) != null) {

                String lastID = rs.getString(1); // HS007
                int number = Integer.parseInt(lastID.substring(2)); // 7
                number++;

                newID = String.format("%s%03d", prefix, number);
            }
        }

        return newID;
    }

    private static final String GET_LAST_ASSIGNMENTID
            = "SELECT TOP 1 assignmentID FROM ASSIGNMENTS ORDER BY assignmentID DESC";
    private static final String GET_LAST_SUBMISSIONSID
            = "SELECT TOP 1 submissionID FROM SUBMISSIONS ORDER BY submissionID DESC";
    private static final String GET_LAST_GRADEID
            = "SELECT TOP 1 gradeID FROM GRADES ORDER BY gradeID DESC";

    public static String generateAssignmentID() {

        String newID = "AS001";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_LAST_ASSIGNMENTID);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastID = rs.getString("assignmentID"); // AS007
                int number = Integer.parseInt(lastID.substring(2));
                number++;
                newID = String.format("AS%03d", number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
    }

    public static String generateSubmissionID() {

        String newID = "SUB001";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_LAST_SUBMISSIONSID);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastID = rs.getString("submissionID");
                int num = Integer.parseInt(lastID.substring(3));
                num++;
                newID = String.format("SUB%03d", num);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
    }

    public static String generateGradeID() {

        String newID = "GR001";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_LAST_GRADEID);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastID = rs.getString("gradeID");
                int num = Integer.parseInt(lastID.substring(2));
                num++;
                newID = String.format("GR%03d", num);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newID;
    }
}
