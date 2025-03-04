package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.user;
import com.dbcon.Dbcon;
import com.login.logindbcon;
import com.login.userlogin;

public class dao {
    
    user user=new user();
  
    public boolean login(userlogin user) throws SQLException {
    	Connection dbcon1=logindbcon.getConnection();
        String query = "SELECT * FROM userlogin WHERE user = ? AND pass = ?";
        try (PreparedStatement ps = dbcon1.prepareStatement(query)) {
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPass()); 

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Returns true if a matching user is found, otherwise false
            } catch (SQLException e) {
                System.out.println("Error executing query: " + e.getMessage());
                throw e; // Rethrow the exception after logging it
            }
        } catch (SQLException e) {
            System.out.println("Error preparing statement: " + e.getMessage());
            throw e; // Rethrow the exception after logging it
        }
    }
    
    public List<user> getAllUsers() {
        List<user> userList = new ArrayList<>();
        String query = "SELECT firstname, lastname, mobile, user, pass FROM user";
        Connection dbcon1=Dbcon.getConnection();
        try (PreparedStatement ps = dbcon1.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                user user = new user();
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));;
                user.setMobile(rs.getLong("mobile"));
                user.setUsername(rs.getString("user"));
                user.setPassword(rs.getString("pass"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problam");
        }
        return userList;
    }


 // Register user into the database
    public int register(user user) {
    	Connection dbcon1=Dbcon.getConnection();
        String query = "INSERT INTO user (firstname, lastname, mobile, user, pass) VALUES (?, ?, ?, ?, ?)";    

        try {
            PreparedStatement ps =dbcon1.prepareStatement(query);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setLong(3, user.getMobile());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            int x= ps.executeUpdate();
            ps.close();
            // Execute the query and return the result
            
            return x; // Returns the number of rows affected
            
        } catch (SQLException e) {
            // Log error details
            System.err.println("❌ Error occurred while registering user: " + e.getMessage());
            e.printStackTrace();
            return -1; // Return -1 for failure
        } 
       
    }

    public List<user> select(String username) throws SQLException {
    	user user=new user();
    	Connection dbcon1=Dbcon.getConnection();
    	List<user> userList = new ArrayList<>();
        String query = "SELECT * FROM user WHERE user = ?";
        try (PreparedStatement stmt =dbcon1.prepareStatement(query)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user.setFirstname(rs.getString(1));
                    user.setLastname(rs.getString(2));
                    user.setMobile(rs.getLong(3));
                    user.setUsername(rs.getString(4));
                    user.setPassword(rs.getString(5));
                    userList.add(user);
                    
                    
                } else {
                    return null; 
                }
            }
        }
		return userList;
    }



    // Delete user by username
    public int delete(String username) throws SQLException {
    	Connection dbcon1=Dbcon.getConnection();
        String query = "DELETE FROM user WHERE user = ?";
        PreparedStatement ps = dbcon1.prepareStatement(query);
        ps.setString(1, username);

        return ps.executeUpdate(); 
    }

    // Edit user details
    public int edit(String username, String newFirstname, String newLastname, long newMobile, String newPassword) throws SQLException {
    	Connection dbcon1=Dbcon.getConnection();
    	String query = "UPDATE user SET firstname = ?, lastname = ?, mobile = ?, pass = ? WHERE user = ?";
        PreparedStatement ps = dbcon1.prepareStatement(query);
        ps.setString(1, newFirstname);
        ps.setString(2, newLastname);
        ps.setLong(3, newMobile);
        ps.setString(4, newPassword);
        ps.setString(5, username);

        return ps.executeUpdate(); 
    }
}
