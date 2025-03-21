package za.ac.tut.databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import za.ac.tut.model.User;
import za.ac.tut.model.UserReport;

public class DatabaseManager {

    private final DatabaseConnector connector = new DatabaseConnector();

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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public User getUserDetails(String username) {
        String sql = "SELECT username, gender, password, role FROM Users"
                + " WHERE username = ?";

        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String gender = rs.getString("gender");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    
                    User user = new User(username, gender, role, password);
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void deletePreviousTime(String username) {
        String sql = "DELETE FROM Time WHERE username = ?";
        
        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addClockInTime(String username) {
        boolean userExists = userExists(username);
        
        if (userExists) {
            String sql = "UPDATE Time SET clockInTime = ? WHERE username = ?";
            
            try (Connection connection = connector.connect();
                    PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                ps.setString(2, username);
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return;
        }
        
        String sql = "INSERT INTO Time (clockInTime, clockOutTime, username)"
                + " VALUES(?, NULL, ?)";

        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setString(2, username);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Timestamp getClockInTime(String username) {
        String sql = "SELECT clockInTime FROM Time WHERE username = ?";

        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp clockInTime = rs.getTimestamp("clockInTime");
                    return clockInTime;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void addClockOutTime(String username) {
        String sql = "UPDATE Time SET clockOutTime = ? WHERE clockInTime = ? AND username = ?";
        Timestamp clockInTime = getClockInTime(username);

        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(2, clockInTime);
            ps.setString(3, username);

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Timestamp getClockOutTime(String username) {
        String sql = "SELECT clockOutTime FROM Time WHERE username = ?";

        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp clockOutTime = rs.getTimestamp("clockOutTime");
                    return clockOutTime;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean userExists(String username) {
        String sql = "SELECT username from Time where username = ?";
        
        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public List<UserReport> getUserReport() {
        String sql = "SELECT u.username, u.role, u.gender, t.clockInTime, t.clockOutTime FROM "
                + "Users u LEFT JOIN Time t ON u.username = t.username";
        
        List<UserReport> userReportList = new ArrayList<>();
        
        try (Connection connection = connector.connect();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                String gender = rs.getString("gender");
                Timestamp clockInTime = rs.getTimestamp("clockInTime");
                Timestamp clockOutTime = rs.getTimestamp("clockOutTime");
                
                UserReport userReport = new UserReport(username, role, gender, clockInTime, clockOutTime);
                userReportList.add(userReport);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userReportList;
    }
}
