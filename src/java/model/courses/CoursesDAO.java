/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class CoursesDAO {
    private String ADD = "INSERT INTO COURSES(courseID, courseCode,courseTitle, department, courseMaterials, courseDescription, courseState) "
            + "VALUES (?,?,?,?,?,?,1)";
    private String UPDATE = "UPDATE COURSES SET courseCode = ?,"
            + "courseTitle = ?, department = ?, courseMaterials = ?,"
            + " courseDescription = ? , courseState = ? WHERE courseID = ?";
    private String DELETE = "UPDATE COURSES SET courseState = 0 WHERE courseID = ?";
    private String SHOW = "SELECT * FROM COURSES";

    public boolean create(CoursesDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, object.getCourseID());
                ps.setString(2, object.getCourseCode());
                ps.setString(3, object.getCourseTitle());
                ps.setString(4, object.getDepartment());
                ps.setString(5, object.getMaterials());
                ps.setString(6, object.getDescription());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CoursesDAO.class.getName());
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CoursesDAO.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean update(CoursesDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setString(7, object.getCourseID());
                ps.setString(1, object.getCourseCode());
                ps.setString(2, object.getCourseTitle());
                ps.setString(3, object.getDepartment());
                ps.setString(4, object.getMaterials());
                ps.setString(5, object.getDescription());
                ps.setBoolean(6, object.isCourseState());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean delete(String courseID) {
        return deleteClass(checkCourseExistByID(courseID));
    }

    private boolean deleteClass(CoursesDTO object) {
        boolean check = false;
        if (object == null) {
            return false;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(DELETE);
                ps.setString(1, object.getCourseID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public ArrayList<CoursesDTO> getAll() {
        return getListCourse();
    }

    public ArrayList<CoursesDTO> getActiveList() {
        ArrayList<CoursesDTO> arr = getListCourse();
        ArrayList<CoursesDTO> newArr = new ArrayList<>();

        for (CoursesDTO var : arr) {
            if (var.isCourseState()) {
                newArr.add(var);
            }
        }
        return newArr;
    }

    public ArrayList<CoursesDTO> getInactiveList() {
        ArrayList<CoursesDTO> arr = getListCourse();
        ArrayList<CoursesDTO> newArr = new ArrayList<>();

        for (CoursesDTO var : arr) {
            if (!var.isCourseState()) {
                newArr.add(var);
            }
        }
        return newArr;
    }

    private ArrayList<CoursesDTO> getListCourse() {
        ArrayList<CoursesDTO> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SHOW);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String courseID = rs.getString("courseID");
                    String courseCode = rs.getString("courseCode");
                    String courseTitle = rs.getString("courseTitle");
                    String department = rs.getString("department");
                    String courseMaterials = rs.getString("courseMaterials");
                    String courseDescription = rs.getString("courseDescription");
                    boolean state = rs.getBoolean("courseState");
                    CoursesDTO course = new CoursesDTO(courseID, courseCode, courseTitle, department, courseMaterials, courseDescription, state);
                    arr.add(course);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CoursesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    private CoursesDTO checkCourseExistByID(String id) {
        CoursesDTO course = null;
        ArrayList<CoursesDTO> arr = getActiveList();
        for (CoursesDTO var : arr) {
            if (var.getCourseID().equalsIgnoreCase(id)) {
                course = var;
            }
        }
        return course;
    }
}
