package model.receive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtils;

public class ReceiveDAO {

    private static final String INSERT
            = "INSERT INTO Receive(alertID, userID) VALUES (?,?)";

    private static final String DELETE
            = "DELETE FROM Receive WHERE alertID=? AND userID=?";

    private static final String GET_ALERTS_BY_USER
            = "SELECT A.* FROM ALERTS A "
            + "JOIN RECEIVE R ON A.alertID = R.alertID "
            + "WHERE R.userID = ? AND A.alertState = 1";

    public boolean create(ReceiveDTO r) {
        boolean check = false;

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setString(1, r.getAlertID());
            ps.setString(2, r.getUserID());

            check = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

//    public ArrayList<AlertsDTO> getAlertsByUser(String userID) {
//        ArrayList<AlertsDTO> list = new ArrayList<>();
//
//        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(GET_ALERTS_BY_USER)) {
//
//            ps.setString(1, userID);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                AlertsDTO alert = new AlertsDTO(
//                        rs.getString("alertID"),
//                        rs.getString("alertMessage"),
//                        rs.getTimestamp("alertDate"),
//                        rs.getString("alertType"),
//                        rs.getBoolean("alertState")
//                );
//                list.add(alert);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }

    public boolean delete(String alertID, String userID) {
        boolean check = false;

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setString(1, alertID);
            ps.setString(2, userID);

            check = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
