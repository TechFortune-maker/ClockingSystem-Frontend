
package za.ac.tut.handler.DatabaseManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean validateUser(String username, String password){
         String sql="SELECT * FROM Users WHERE username = ? AND password = ?";
         
         Connection connection = connector.connect();
         
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Error during login: "+ex.getMessage());
        }
         
         return false;
    }
}
