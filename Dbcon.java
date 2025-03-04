package com.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {
    private static final String URL = "jdbc:mysql://localhost:3306/masilamani123";
    private static final String USER = "root";
    private static final String PASS = "pass123";

    private Dbcon() {}

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database Connected Successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver Not Found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
        }
        return con;
    }

    // Method to close the connection
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection Closed Successfully!");
            } catch (SQLException e) {
                System.err.println("Failed to Close Connection: " + e.getMessage());
            }
        }
    }
}
