package model.scheduleSlots;

import java.sql.Date;
import java.time.LocalTime;

public class ScheduleSlotsDTO {
    private String slotID;
    private java.sql.Date sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomCode;

    public ScheduleSlotsDTO() {
    }

    public ScheduleSlotsDTO(String slotID, Date sessionDate, LocalTime startTime, LocalTime endTime, String roomCode) {
        this.slotID = slotID;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomCode = roomCode;
    }

    

    public String getSlotID() {
        return slotID;
    }

    public void setSlotID(String slotID) {
        this.slotID = slotID;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    
}
