package com.xadmin.LA.LILO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

public class ClasDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ClasDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
     
    public boolean insertClas(Clas clas) throws SQLException {
        String sql = "INSERT INTO classes (classname) VALUES (?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, clas.getClasname());
        
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public List<Clas> listAllClasses() throws SQLException {
        List<Clas> listClas = new ArrayList<>();
         
        String sql = "SELECT * FROM classes";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("class_id");
            String clasname = resultSet.getString("classname");
           
             
            Clas clas = new Clas(id, clasname);
            listClas.add(clas);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listClas;
    }
     
    public boolean deleteClas(Clas clas) throws SQLException {
        String sql = "DELETE FROM classes where class_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, clas.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateClas(Clas clas) throws SQLException {
        String sql = "UPDATE classes SET classname = ?";
        sql += " WHERE class_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, clas.getClasname());
        
        statement.setInt(2, clas.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Clas getClas(int id) throws SQLException {
    	Clas clas = null;
        String sql = "SELECT * FROM classes WHERE class_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String clasname = resultSet.getString("classname");
            
             
            clas = new Clas(id, clasname);
        }
         
        resultSet.close();
        statement.close();
         
        return clas;
    }
}
