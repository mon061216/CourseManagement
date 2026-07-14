/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DBUtils {
    public static final Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection  conn = null;
        java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("utils.db");
        String hostname = rb.getString("hostname");
        String port = rb.getString("port");
        String databasename = rb.getString("databasename");
        String username = rb.getString("username");
        String pass = rb.getString("password");
        String url = String.format("jdbc:sqlserver://%s:%s;databasename=%s;", hostname,port,databasename);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url, username, pass);
        
        return conn;
    }
}
