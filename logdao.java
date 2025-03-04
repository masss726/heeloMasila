package com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class logdao {

public int register(userlogin user) throws SQLException {
    String query = "INSERT INTO userlogin (user, pass) VALUES (?, ?)";
    try (Connection conn = logindbcon.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, user.getUser());
        ps.setString(2, user.getPass());
        int rowsAffected = ps.executeUpdate();
        return  rowsAffected;
    } catch (SQLException e) {
        System.out.println("Error during registration: " + e.getMessage());
        throw e;
    }
}

}
