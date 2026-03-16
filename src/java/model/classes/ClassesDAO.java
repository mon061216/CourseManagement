package model.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class ClassesDAO {

    private String ADD = "INSERT INTO CLASSES (classID, courseID, className, classCapacity, classStartDate, classEndDate, academicTerm, classState,teacherID) "
            + "VALUES (?,?,?,?,?,?,?,1, ?)";
    private String UPDATE = "UPDATE CLASSES SET className = ?, classCapacity = ?, classStartDate = ?, classEndDate = ?, academicTerm = ?, classState = ? WHERE classID = ? AND courseID = ? AND teacherID = ?";
    private String DELETE = "UPDATE CLASSES SET classState = 0 WHERE classID = ? AND courseID = ? AND teacherID = ?";
    private String SHOW = "SELECT * FROM CLASSES";
    private String GET_BY_COURSE_ID = "SELECT * FROM CLASSES WHERE courseID = ?";

    public boolean create(ClassesDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(ADD);
                ps.setString(1, object.getClassID());
                ps.setString(2, object.getCourseID());
                ps.setString(3, object.getClassName());
                ps.setInt(4, object.getClassCapacity());
                ps.setDate(5, new java.sql.Date(object.getClassStartDate().getTime()));
                ps.setDate(6, new java.sql.Date(object.getClassEndDate().getTime()));
                ps.setString(7, object.getAcademicTerm());
                ps.setString(8, object.getTeacherID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean update(ClassesDTO object) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE);
                ps.setString(7, object.getClassID());
                ps.setString(8, object.getCourseID());
                ps.setString(1, object.getClassName());
                ps.setInt(2, object.getClassCapacity());
                ps.setDate(3, new java.sql.Date(object.getClassStartDate().getTime()));
                ps.setDate(4, new java.sql.Date(object.getClassEndDate().getTime()));
                ps.setString(5, object.getAcademicTerm());
                ps.setBoolean(6, object.isClassState());
                ps.setString(9, object.getTeacherID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean delete(String classID, String courseID, String teacherID) {
        return deleteClass(getObjectClassDTO(classID, courseID, teacherID));
    }

    private boolean deleteClass(ClassesDTO object) {
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
                ps.setString(1, object.getClassID());
                ps.setString(2, object.getCourseID());
                ps.setString(3, object.getTeacherID());
                check = ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public ArrayList<ClassesDTO> getAll() {
        return getListClass();
    }

    public ArrayList<ClassesDTO> getActiveListFromCourse(String courseID) {
        ArrayList<ClassesDTO> arr = new ArrayList<>();

        for (ClassesDTO var : getByCourseID(courseID.trim())) {
            if (var.isClassState()) {
                arr.add(var);
            }
        }
        return arr;
    }

    private ArrayList<ClassesDTO> getByCourseID(String courseID) {
        ArrayList<ClassesDTO> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_BY_COURSE_ID);
                ps.setString(1, courseID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String classID = rs.getString("classID");
                    String className = rs.getString("className");
                    int classCapacity = rs.getInt("classCapacity");
                    java.sql.Date classStartDate = rs.getDate("classStartDate");
                    java.sql.Date classEndDate = rs.getDate("classEndDate");
                    String academicTerm = rs.getString("academicTerm");
                    boolean classState = rs.getBoolean("classState");
                    String teacherID = rs.getString("teacherID");

                    arr.add(new ClassesDTO(classID, courseID, className, classCapacity, classStartDate, classEndDate, academicTerm, classState, teacherID));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    public ArrayList<ClassesDTO> getActiveList() {
        ArrayList<ClassesDTO> arr = getListClass();
        ArrayList<ClassesDTO> newArr = new ArrayList<>();

        for (ClassesDTO var : arr) {
            if (var.isClassState()) {
                newArr.add(var);
            }
        }
        return newArr;
    }

    public ArrayList<ClassesDTO> getInactiveList() {
        ArrayList<ClassesDTO> arr = getListClass();
        ArrayList<ClassesDTO> newArr = new ArrayList<>();

        for (ClassesDTO var : arr) {
            if (!var.isClassState()) {
                newArr.add(var);
            }
        }
        return newArr;
    }

    private ArrayList<ClassesDTO> getListClass() {
        ArrayList<ClassesDTO> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(SHOW);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String classID = rs.getString("classID");
                    String courseID = rs.getString("courseID");
                    String className = rs.getString("className");
                    int classCapacity = rs.getInt("classCapacity");
                    java.sql.Date classStartDate = rs.getDate("classStartDate");
                    java.sql.Date classEndDate = rs.getDate("classEndDate");
                    String academicTerm = rs.getString("academicTerm");
                    boolean classState = rs.getBoolean("classState");
                    String teacherID = rs.getString("teacherID");

                    arr.add(new ClassesDTO(classID, courseID, className, classCapacity, classStartDate, classEndDate, academicTerm, classState, teacherID));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }

    private ClassesDTO getObjectClassDTO(String classID, String courseID, String teacherID) {
        ArrayList<ClassesDTO> arr = getActiveList();
        ClassesDTO cl = null;
        for (ClassesDTO var : arr) {
            if (var.getClassID().equalsIgnoreCase(classID) && var.getCourseID().equalsIgnoreCase(courseID) && var.getTeacherID().equalsIgnoreCase(teacherID)) {
                cl = var;
            }
        }
        return cl;
    }

    private String GET_BY_TEACHER_ID
            = "SELECT * FROM CLASSES WHERE teacherID = ?";

    public List<ClassesDTO> getByTeacherID(String teacherID) {

        List<ClassesDTO> list = new ArrayList<>();

        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(GET_BY_TEACHER_ID)) {

            ps.setString(1, teacherID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClassesDTO c = new ClassesDTO(rs.getString("classID"), rs.getString("courseID"), rs.getString("className"), rs.getInt("classCapacity"),(java.sql.Date) rs.getDate("classStartDate") ,(java.sql.Date) rs.getDate("classEndDate"), rs.getString("academicTerm"),rs.getBoolean("classState") , rs.getString("teacherID"));
                
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
