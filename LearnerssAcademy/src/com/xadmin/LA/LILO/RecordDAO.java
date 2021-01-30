package com.xadmin.LA.LILO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class RecordDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public RecordDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertRecord(Record record) throws SQLException {
        String sql = "INSERT INTO record (classname, subjectname, teachername, studentname) VALUES (?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, record.getClassname());
        statement.setString(2, record.getSubjectname());
        statement.setString(2, record.getTeachername());
        statement.setString(2, record.getStudentname());
        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Record> listAllRecords() throws SQLException {
        List<Record> listRecord = new ArrayList<>();
         
        String sql = "SELECT * FROM record";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("record_id");
            String classname = resultSet.getString("classname");
            String subjectname = resultSet.getString("subjectname");
            String teachername = resultSet.getString("teachername");
            String studentname = resultSet.getString("studentname");
            
             
            Record record = new Record(id, classname, subjectname, teachername, studentname);
            listRecord.add(record);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listRecord;
    }
     
    public boolean deleteRecord(Record record) throws SQLException {
        String sql = "DELETE FROM record where record_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, record.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateRecord(Record record) throws SQLException {
        String sql = "UPDATE record SET classname = ?, subjectname = ?, teachername = ?, studentname= ?";
        sql += " WHERE record_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, record.getClassname());
        statement.setString(2, record.getSubjectname());
        statement.setString(3, record.getTeachername());
        statement.setString(4, record.getStudentname());
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Record getRecord(int id) throws SQLException {
    	Record record = null;
        String sql = "SELECT * FROM record WHERE record_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String classname = resultSet.getString("classname");
            String subjectname = resultSet.getString("subjectname");
            String teachername = resultSet.getString("teachername");
            String studentname = resultSet.getString("studentname");
            
             
            record = new Record(id, classname, subjectname, teachername, studentname);
        }
         
        resultSet.close();
        statement.close();
         
        return record;
    }
}
