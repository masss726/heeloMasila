package com.model;

import java.io.Serializable;

public class user implements Serializable {
    private static final long serialVersionUID = 7042746747108078112L;

    private String firstname;
    private String lastname;
    private long mobile;
    private String username;
    private String password;

    // Default Constructor
    public user() {
        // No-argument constructor
    }

    // Parameterized Constructor
    public user(String firstname, String lastname, int mobile, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Optional: Override `toString` for debugging or logging
    @Override
    public String toString() {
        return "User [firstname=" + firstname + ", lastname=" + lastname + ", mobile=" + mobile 
                + ", username=" + username + "]";
    }
}
