package model.assignments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class AssignmentsDAO {

    private static final String ADD
            = "INSERT INTO ASSIGNMENTS (assignmentID, classID, assignmentTitle, assignmentDescription, assignmentNote, dueDate, assignmentState) "
            + "VALUES (?,?,?,?,?,?,1)";

    private static final String UPDATE
            = "UPDATE ASSIGNMENTS "
            + "SET assignmentTitle=?, assignmentDescription=?, assignmentNote=?, dueDate=? "
            + "WHERE assignmentID=? AND assignmentState=1";

//    private static final String DELETE
//            = "UPDATE ASSIGNMENTS SET assignmentState=0 WHERE assignmentID=?";

    private static final String SHOW
            = "SELECT * FROM ASSIGNMENTS WHERE assignmentState=1";
    private static final String GET_BY_CLASS_ID
            = "SELECT * FROM ASSIGNMENTS "
            + "WHERE classID = ?";

    public boolean create(AssignmentsDTO a) {
        boolean check = false;

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(ADD)) {

            ps.setString(1, a.getAssignmentID());
            ps.setString(2, a.getClassID());
            ps.setString(3, a.getAssignmentTitle());
            ps.setString(4, a.getAssignmentDescription());
            ps.setString(5, a.getAssignmentNote());
            ps.setTimestamp(6, Timestamp.valueOf(a.getDueDate()));

            check = ps.executeUpdate() > 0;

        } catch (Exception e) {
            Logger.getLogger(AssignmentsDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }

        return check;
    }

    public boolean update(AssignmentsDTO a) {
        boolean check = false;

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(UPDATE)) {

            ps.setString(1, a.getAssignmentTitle());
            ps.setString(2, a.getAssignmentDescription());
            ps.setString(3, a.getAssignmentNote());
            ps.setTimestamp(4, Timestamp.valueOf(a.getDueDate()));
            ps.setString(5, a.getAssignmentID());

            check = ps.executeUpdate() > 0;

        } catch (Exception e) {
            Logger.getLogger(AssignmentsDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }

        return check;
    }

//    public boolean delete(String assignmentID) {
//        boolean check = false;
//
//        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(DELETE)) {
//
//            ps.setString(1, assignmentID);
//
//            check = ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            Logger.getLogger(AssignmentsDAO.class.getName())
//                    .log(Level.SEVERE, null, e);
//        }
//
//        return check;
//    }

    public ArrayList<AssignmentsDTO> getAll() {
        ArrayList<AssignmentsDTO> list = new ArrayList<>();

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(SHOW);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                LocalDateTime dueDate
                        = rs.getTimestamp("dueDate").toLocalDateTime();

                AssignmentsDTO a = new AssignmentsDTO(
                        rs.getString("assignmentID"),
                        rs.getString("classID"),
                        rs.getString("assignmentTitle"),
                        rs.getString("assignmentDescription"),
                        rs.getString("assignmentNote"),
                        dueDate
                );

                list.add(a);
            }

        } catch (Exception e) {
            Logger.getLogger(AssignmentsDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }

        return list;
    }

    public ArrayList<AssignmentsDTO> getByClassID(String classID) {

        ArrayList<AssignmentsDTO> list = new ArrayList<>();

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_BY_CLASS_ID)) {

            ps.setString(1, classID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                LocalDateTime dueDate
                        = rs.getTimestamp("dueDate").toLocalDateTime();

                AssignmentsDTO a = new AssignmentsDTO(
                        rs.getString("assignmentID"),
                        rs.getString("classID"),
                        rs.getString("assignmentTitle"),
                        rs.getString("assignmentDescription"),
                        rs.getString("assignmentNote"),
                        dueDate
                );

                list.add(a);
            }

        } catch (Exception e) {
            Logger.getLogger(AssignmentsDAO.class.getName())
                    .log(Level.SEVERE, null, e);
        }

        return list;
    }
}
