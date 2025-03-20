
package za.ac.tut.databases;

import java.sql.*;

public class DatabaseConnector {
    final String URL = "jdbc:derby://localhost:1527/ClockingSystemDatabase";
    final String USERNAME = "app";
    final String PASSWORD = "123";
    
    public Connection connect() {
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return connection;
    }
}
