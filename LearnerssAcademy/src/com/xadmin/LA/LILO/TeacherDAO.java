package com.xadmin.LA.LILO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class TeacherDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public TeacherDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (teachername) VALUES (?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, teacher.getTeachername());
        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Teacher> listAllTeachers() throws SQLException {
        List<Teacher> listTeacher = new ArrayList<>();
         
        String sql = "SELECT * FROM teachers";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("teacher_id");
            String teachername = resultSet.getString("teachername");
           
             
            Teacher teacher = new Teacher(id, teachername);
            listTeacher.add(teacher);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listTeacher;
    }
     
    public boolean deleteTeacher(Teacher teacher) throws SQLException {
        String sql = "DELETE FROM teachers where teacher_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, teacher.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateTeacher(Teacher teacher) throws SQLException {
        String sql = "UPDATE teachers SET teachername = ?";
        sql += " WHERE teacher_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, teacher.getTeachername());
        
        statement.setInt(2, teacher.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Teacher getTeacher(int id) throws SQLException {
    	Teacher teacher = null;
        String sql = "SELECT * FROM teachers WHERE teacher_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String teachername = resultSet.getString("teachername");
            
             
            teacher = new Teacher(id, teachername);
        }
         
        resultSet.close();
        statement.close();
         
        return teacher;
    }
}
