/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.courses;

/**
 *
 * @author PC
 */
public class CoursesDTO {
    private String courseID;
    private String courseCode;
    private String courseTitle;
    private String department;
    private String materials;
    private String description;
    private boolean courseState;

    public CoursesDTO() {
    }

    public CoursesDTO(String courseID, String courseCode, String courseTitle, String department, String materials, String description, boolean courseState) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.department = department;
        this.materials = materials;
        this.description = description;
        this.courseState = courseState;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCourseState() {
        return courseState;
    }

    public void setCourseState(boolean courseState) {
        this.courseState = courseState;
    }
    
}
