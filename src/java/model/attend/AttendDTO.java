package model.attend;

import java.util.Date;

public class AttendDTO {
    private String slotID;
    private String userID;
    private Date attendanceDate;
    private boolean attendanceState;

    public AttendDTO() {
    }

    public AttendDTO(String slotID, String userID, Date attendanceDate, boolean attendanceState) {
        this.slotID = slotID;
        this.userID = userID;
        this.attendanceDate = attendanceDate;
        this.attendanceState = attendanceState;
    }

    public String getSlotID() {
        return slotID;
    }

    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public boolean isAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(boolean attendanceState) {
        this.attendanceState = attendanceState;
    }
    
}
