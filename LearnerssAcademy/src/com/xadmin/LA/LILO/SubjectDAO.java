package com.xadmin.LA.LILO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class SubjectDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public SubjectDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertSubject(Subject subject) throws SQLException {
        String sql = "INSERT INTO subjects (subjectname) VALUES (?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, subject.getSubjectname());
        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Subject> listAllSubjects() throws SQLException {
        List<Subject> listSubject = new ArrayList<>();
         
        String sql = "SELECT * FROM subjects";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("subject_id");
            String subjectname = resultSet.getString("subjectname");
           
             
            Subject subject = new Subject(id, subjectname);
            listSubject.add(subject);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listSubject;
    }
     
    public boolean deleteSubject(Subject subject) throws SQLException {
        String sql = "DELETE FROM subjects where subject_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, subject.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateSubject(Subject subject) throws SQLException {
        String sql = "UPDATE subjects SET subjectname = ?";
        sql += " WHERE subject_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, subject.getSubjectname());
        
        statement.setInt(2, subject.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Subject getSubject(int id) throws SQLException {
        Subject subject = null;
        String sql = "SELECT * FROM subjects WHERE subject_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String subjectname = resultSet.getString("subjectname");
            
             
            subject = new Subject(id, subjectname);
        }
         
        resultSet.close();
        statement.close();
         
        return subject;
    }
}
