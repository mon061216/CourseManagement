package model.enrol;

import java.util.Date;

public class EnrolDTO {
    private String userID;
    private String classID;
    private Date enrolDate;
    private boolean enrolState;
    private String enrolNote;

    public EnrolDTO() {
    }

    public EnrolDTO(String userID, String classID, Date enrolDate, boolean enrolState, String enrolNote) {
        this.userID = userID;
        this.classID = classID;
        this.enrolDate = enrolDate;
        this.enrolState = enrolState;
        this.enrolNote = enrolNote;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public boolean isEnrolState() {
        return enrolState;
    }

    public void setEnrolState(boolean enrolState) {
        this.enrolState = enrolState;
    }

    public String getEnrolNote() {
        return enrolNote;
    }

    public void setEnrolNote(String enrolNote) {
        this.enrolNote = enrolNote;
    }
    
}
