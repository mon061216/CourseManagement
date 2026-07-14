package model.isScheduled;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class IsScheduledDAO {

    private String ADD =
        "INSERT INTO IsScheduled(classID, slotID) VALUES (?,?)";

    private String DELETE =
        "DELETE FROM IsScheduled WHERE classID=? AND slotID=?";

    private String SHOW =
        "SELECT * FROM IsScheduled";

    private String GET_BY_SCHEDULE =
        "SELECT slotID FROM IsScheduled WHERE classID=?";

    private String GET_BY_SLOT =
        "SELECT classID FROM IsScheduled WHERE slotID=?";

    public boolean create(IsScheduledDTO is) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, is.getClassID());
                ps.setString(2, is.getSlotID());

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public boolean delete(String classID, String slotID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, classID);
                ps.setString(2, slotID);

                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, null);
        }
        return check;
    }

    public ArrayList<IsScheduledDTO> getAll() {
        ArrayList<IsScheduledDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SHOW);
                rs = ps.executeQuery();

                while (rs.next()) {
                    IsScheduledDTO is = new IsScheduledDTO(
                            rs.getString("classID"),
                            rs.getString("slotID")
                    );
                    list.add(is);
                }
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public ArrayList<String> getSlotsBySchedule(String classID) {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_BY_SCHEDULE);
                ps.setString(1, classID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("slotID"));
                }
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    public ArrayList<String> getSchedulesBySlot(String slotID) {
        ArrayList<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_BY_SLOT);
                ps.setString(1, slotID);
                rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(rs.getString("classID"));
                }
            }
        } catch (Exception e) {
        } finally {
            closeResources(conn, ps, rs);
        }
        return list;
    }

    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(IsScheduledDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
