package model.submissions;

import java.util.Date;

public class SubmissionsDTO {
    private String submissionID;
    private String assignmentID;
    private String studentID;
    private Date submissionDate;
    private String submissionFile;
    private boolean submissionState;

    public SubmissionsDTO() {
    }

    public SubmissionsDTO(String submissionID, String assignmentID, String studentID, Date submissionDate, String submissionFile, boolean submissionState) {
        this.submissionID = submissionID;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.submissionDate = submissionDate;
        this.submissionFile = submissionFile;
        this.submissionState = submissionState;
    }

    public String getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(String submissionID) {
        this.submissionID = submissionID;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSubmissionFile() {
        return submissionFile;
    }

    public void setSubmissionFile(String submissionFile) {
        this.submissionFile = submissionFile;
    }

    public boolean isSubmissionState() {
        return submissionState;
    }

    public void setSubmissionState(boolean submissionState) {
        this.submissionState = submissionState;
    }
    
    
}
