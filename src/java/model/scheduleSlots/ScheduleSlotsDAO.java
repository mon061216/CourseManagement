package model.scheduleSlots;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class ScheduleSlotsDAO {

    private static final String ADD
            = "INSERT INTO SCHEDULESLOTS(slotID, sessionDate, startTime, endTime, roomCode) "
            + "VALUES (?,?,?,?,?)";

    private static final String UPDATE
            = "UPDATE SCHEDULESLOTS SET sessionDate=?, startTime=?, endTime=?, roomCode=? "
            + "WHERE slotID=?";

    private static final String DELETE
            = "DELETE FROM SCHEDULESLOTS WHERE slotID=?";

    private static final String GET_OBJECT_BY_ID
            = "SELECT * FROM SCHEDULESLOTS WHERE slotID=?";

    public boolean create(ScheduleSlotsDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);

                ps.setString(1, object.getSlotID());
                ps.setDate(2, object.getSessionDate());
                ps.setTime(3, Time.valueOf(object.getStartTime()));
                ps.setTime(4, Time.valueOf(object.getEndTime()));
                ps.setString(5, object.getRoomCode());

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public boolean update(ScheduleSlotsDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);

                ps.setDate(1, object.getSessionDate());
                ps.setTime(2, Time.valueOf(object.getStartTime()));
                ps.setTime(3, Time.valueOf(object.getEndTime()));
                ps.setString(4, object.getRoomCode());
                ps.setString(5, object.getSlotID());

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public boolean delete(String slotID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, slotID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public ScheduleSlotsDTO getObjectByID(String slotID) {
        ScheduleSlotsDTO sc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_OBJECT_BY_ID);
                ps.setString(1, slotID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    LocalTime start = rs.getTime("startTime").toLocalTime();
                    LocalTime end = rs.getTime("endTime").toLocalTime();

                    sc = new ScheduleSlotsDTO(
                            rs.getString("slotID"),
                            rs.getDate("sessionDate"),
                            start,
                            end,
                            rs.getString("roomCode")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ps, rs);
        }
        return sc;
    }

    public ArrayList<ScheduleSlotsDTO> getScheduleByClass(String classID) {

        ArrayList<ScheduleSlotsDTO> list = new ArrayList<>();

        String sql
                = "SELECT s.* FROM SCHEDULESLOTS s "
                + "JOIN ISSCHEDULED i ON s.slotID = i.slotID "
                + "WHERE i.classID=? "
                + "ORDER BY s.sessionDate, s.startTime";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, classID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ScheduleSlotsDTO s = new ScheduleSlotsDTO(
                        rs.getString("slotID"),
                        rs.getDate("sessionDate"),
                        rs.getTime("startTime").toLocalTime(),
                        rs.getTime("endTime").toLocalTime(),
                        rs.getString("roomCode")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleSlotsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
