package model.assignments;

import java.time.LocalDateTime;

public class AssignmentsDTO {
    private String assignmentID;
    private String classID;
    private String assignmentTitle;
    private String assignmentDescription;
    private String assignmentNote;
    private LocalDateTime dueDate;

    public AssignmentsDTO() {
    }

    public AssignmentsDTO(String assignmentID, String classID, String assignmentTitle, String assignmentDescription, String assignmentNote, LocalDateTime dueDate) {
        this.assignmentID = assignmentID;
        this.classID = classID;
        this.assignmentTitle = assignmentTitle;
        this.assignmentDescription = assignmentDescription;
        this.assignmentNote = assignmentNote;
        this.dueDate = dueDate;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }


    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getAssignmentNote() {
        return assignmentNote;
    }

    public void setAssignmentNote(String assignmentNote) {
        this.assignmentNote = assignmentNote;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
}
