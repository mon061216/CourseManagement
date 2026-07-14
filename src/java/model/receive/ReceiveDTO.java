package model.receive;
public class ReceiveDTO {
    private String alertID;
    private String userID;

    public ReceiveDTO() {
    }

    public ReceiveDTO(String alertID, String userID) {
        this.alertID = alertID;
        this.userID = userID;
    }

    public String getAlertID() {
        return alertID;
    }

    public void setAlertID(String alertID) {
        this.alertID = alertID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
}
