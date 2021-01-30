package com.xadmin.LA.LILO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class 	TeaDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public TeaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertTea(Tea tea) throws SQLException {
        String sql = "INSERT INTO tea (classname, subjectname, teachername) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, tea.getClassname());
        statement.setString(2, tea.getSubjectname());
        statement.setString(3, tea.getTeachername());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Tea> listAllTeas() throws SQLException {
        List<Tea> listTea = new ArrayList<>();
         
        String sql = "SELECT * FROM tea";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("tea_id");
            String classname = resultSet.getString("classname");
            String subjectname = resultSet.getString("subjectname");
            String teachername = resultSet.getString("teachername");
             
            Tea tea = new Tea(id, classname, subjectname, teachername);
            listTea.add(tea);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listTea;
    }
     
    public boolean deleteTea(Tea tea) throws SQLException {
        String sql = "DELETE FROM tea where tea_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, tea.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateTea(Tea tea) throws SQLException {
        String sql = "UPDATE tea SET classname = ?, subjectname = ?, teachername = ?";
        sql += " WHERE tea_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, tea.getClassname());
        statement.setString(2, tea.getSubjectname());
        statement.setString(3, tea.getTeachername());
        statement.setInt(4, tea.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Tea getTea(int id) throws SQLException {
    	Tea tea = null;
        String sql = "SELECT * FROM tea WHERE tea_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String classname = resultSet.getString("classname");
            String subjectname = resultSet.getString("subjectname");
            String teachername = resultSet.getString("teachername");
             
            tea = new Tea(id, classname, subjectname, teachername);
        }
         
        resultSet.close();
        statement.close();
         
        return tea;
    }
}
