package model.attend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class AttendDAO {

    private String ADD = "INSERT INTO ATTEND(slotID, userID, attendanceDate, attendanceState) "
            + "VALUES (?,?,?,?)";

    private String UPDATE = "UPDATE ATTEND SET attendanceState=? "
            + "WHERE slotID=? AND userID=?";

    private String DELETE = "DELETE FROM ATTEND "
            + "WHERE slotID=? AND userID=?";

    private String SHOW = "SELECT * FROM ATTEND";

    public boolean create(AttendDTO a) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, a.getSlotID());
                ps.setString(2, a.getUserID());
                ps.setDate(3, new Date(a.getAttendanceDate().getTime()));
                ps.setBoolean(4, a.isAttendanceState());

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public boolean update(String slotID, String userID, boolean state) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setBoolean(1, state);
                ps.setString(2, slotID);
                ps.setString(3, userID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public boolean delete(String slotID, String userID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, slotID);
                ps.setString(2, userID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public ArrayList<AttendDTO> getAll() {
        ArrayList<AttendDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SHOW);
                rs = ps.executeQuery();

                while (rs.next()) {
                    AttendDTO a = new AttendDTO(
                            rs.getString("slotID"),
                            rs.getString("userID"),
                            rs.getDate("attendanceDate"),
                            rs.getBoolean("attendanceState")
                    );
                    list.add(a);
                }
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public double getAttendancePercent(String classID, String userID) {

        String sql
                = "SELECT COUNT(a.slotID) as attended, "
                + "(SELECT COUNT(*) FROM ISSCHEDULED WHERE classID=?) as total "
                + "FROM ATTEND a "
                + "JOIN ISSCHEDULED i ON a.slotID = i.slotID "
                + "WHERE i.classID=? AND a.userID=? AND a.attendanceState=1";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, classID);
            ps.setString(2, classID);
            ps.setString(3, userID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int attended = rs.getInt("attended");
                int total = rs.getInt("total");

                if (total == 0) {
                    return 0;
                }

                return (attended * 100.0) / total;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean isAttendanceExist(String slotID, String userID) {

        String sql = "SELECT * FROM ATTEND WHERE slotID=? AND userID=?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, slotID);
            ps.setString(2, userID);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
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
            Logger.getLogger(AttendDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
