
package za.ac.tut.databases;

import java.sql.*;
import za.ac.tut.model.User;

public class DatabaseManager {
    
    DatabaseConnector connector = new DatabaseConnector();
    
    public void registerUser(String username, String gender, String password, String role) {
        String sql = "INSERT INTO Users (username, gender, password, role) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, gender);
            ps.setString(3, password);
            ps.setString(4, role);
            
            ps.executeUpdate();
            System.out.println("A new user has been added into the database");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public User getUserDetails(String username) {
        String sql = "SELECT username, gender, password, role FROM Users"
                + " WHERE username = ?";
        
        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String gender = rs.getString("gender");
                String password = rs.getString("password");
                String role = rs.getString("role");
                
                User user = new User(username, gender, role, password);
                return user;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
