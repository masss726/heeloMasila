package com.login;

import java.io.Serializable;

public class userlogin implements Serializable{
	
	private static final long serialVersionUID = -3010753497614232541L;
	
	String user;
	String pass;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public userlogin(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	public userlogin() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
