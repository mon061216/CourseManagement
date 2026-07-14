package model.classes;

import java.util.Date;

public class ClassesDTO {
    private String classID;
    private String courseID;
    private String className;
    private int classCapacity;
    private Date classStartDate;
    private Date classEndDate;
    private String academicTerm;
    private boolean classState;
    private String teacherID;

    public ClassesDTO() {
    }

    public ClassesDTO(String classID, String courseID, String className, int classCapacity, Date classStartDate, Date classEndDate, String academicTerm, boolean classState, String teacherID) {
        this.classID = classID;
        this.courseID = courseID;
        this.className = className;
        this.classCapacity = classCapacity;
        this.classStartDate = classStartDate;
        this.classEndDate = classEndDate;
        this.academicTerm = academicTerm;
        this.classState = classState;
        this.teacherID = teacherID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    public Date getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
    }

    public Date getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public String getAcademicTerm() {
        return academicTerm;
    }

    public void setAcademicTerm(String academicTerm) {
        this.academicTerm = academicTerm;
    }

    public boolean isClassState() {
        return classState;
    }

    public void setClassState(boolean classState) {
        this.classState = classState;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return "ClassesDTO{" + "classID=" + classID + ", courseID=" + courseID + ", className=" + className + ", classCapacity=" + classCapacity + ", classStartDate=" + classStartDate + ", classEndDate=" + classEndDate + ", academicTerm=" + academicTerm + ", classState=" + classState + ", teacherID=" + teacherID + '}';
    }
    
}
