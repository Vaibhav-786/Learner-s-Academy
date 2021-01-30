package com.xadmin.LA.LILO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class SubDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public SubDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertSub(Sub sub) throws SQLException {
        String sql = "INSERT INTO subs (subname, classname) VALUES (?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sub.getSubname());
        statement.setString(2, sub.getClassname());
        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Sub> listAllSubs() throws SQLException {
        List<Sub> listSub = new ArrayList<>();
         
        String sql = "SELECT * FROM subs";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("sub_id");
            String subname = resultSet.getString("subname");
            String classname = resultSet.getString("classname");
            
             
            Sub sub = new Sub(id, subname, classname);
            listSub.add(sub);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listSub;
    }
     
    public boolean deleteSub(Sub sub) throws SQLException {
        String sql = "DELETE FROM subs where sub_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, sub.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateSub(Sub sub) throws SQLException {
        String sql = "UPDATE subs SET subname = ?, classname = ?";
        sql += " WHERE sub_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, sub.getSubname());
        statement.setString(2, sub.getClassname());
        
        statement.setInt(3, sub.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Sub getSub(int id) throws SQLException {
    	Sub sub = null;
        String sql = "SELECT * FROM subs WHERE sub_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String subname = resultSet.getString("subname");
            String classname = resultSet.getString("classname");
            
             
            sub = new Sub(id, subname, classname);
        }
         
        resultSet.close();
        statement.close();
         
        return sub;
    }
}