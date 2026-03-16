package model.grade;

import java.util.Date;

public class GradesDTO {
    private String gradeID;
    private String teacherID;
    private String submissionID;
    private float gradeScore;
    private Date gradeDate;

    public GradesDTO() {
    }

    public GradesDTO(String gradeID, String submissionID, String teacherID, float gradeScore, Date gradeDate) {
        this.gradeID = gradeID;
        this.teacherID = teacherID;
        this.submissionID = submissionID;
        this.gradeScore = gradeScore;
        this.gradeDate = gradeDate;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(String submissionID) {
        this.submissionID = submissionID;
    }

    public float getGradeScore() {
        return gradeScore;
    }

    public void setGradeScore(float gradeScore) {
        this.gradeScore = gradeScore;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }    
}
