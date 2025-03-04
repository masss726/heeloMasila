package com.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.model.user;
import com.dao.dao;
import com.login.logdao;
import com.login.userlogin;

@WebServlet("/Controller")

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        dao dao = new dao();
        

        try {
            switch (action) {
                case "login":
                	handlelogin(req, res, dao);
                    break;

                case "register":
                    handleRegister(req, res, dao);
                    break;

                case "about":
                    res.sendRedirect("about.html");
                    break;

                case "users":
                    users(req, res, dao);
                    break;

                case "delete":
                    handleDelete(req, res, dao);
                    break;

                case "edit":
                    select(req, res, dao);
                    break;
                    
                case "update":
                	handleEdit(req, res, dao);
                    break;

                default:
                    res.getWriter().println("❌ Invalid action.");
            }
        }catch(Exception e) {
        	res.sendRedirect("error.html");
    }
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
    
    private void users(HttpServletRequest req, HttpServletResponse res, dao dao) throws IOException {
    	List<user> userList = dao.getAllUsers();
    	user user = new user();
    	req.setAttribute("userList", userList);
    	RequestDispatcher dispatcher = req.getRequestDispatcher("users.jsp");
        try {
			dispatcher.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void select(HttpServletRequest req, HttpServletResponse res, dao dao) throws IOException {
    	String user=req.getParameter("username");
    	try {
			List<user> userList = dao.select(user);
			req.setAttribute("userList", userList);
	    	RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
	        try {
				dispatcher.forward(req, res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		} catch (SQLException e) {
			res.getWriter().println("selection failed");			
			e.printStackTrace();
		}
    }
    
    private void handlelogin(HttpServletRequest req, HttpServletResponse res, dao dao) throws IOException {
    	userlogin log=new userlogin();
    	String user=req.getParameter("username");
    	String pass=req.getParameter("password");
    	
    	log.setUser(user);
    	log.setPass(pass);
    	boolean login=false;
    	try {
			login=dao.login(log);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(login) {
    		res.sendRedirect("loginsuccess.html");
    	}else {
    		res.sendRedirect("error.html");
    	}
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse res, dao dao) throws IOException {
        user user = new user();
        userlogin log=new userlogin();
        logdao daol= new logdao();
        try {
           
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String mobileStr = req.getParameter("mobile");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String userl=username;
            String passl=password;
            if (!mobileStr.matches("\\d+")) {
                res.getWriter().println("❌ Mobile number should contain only digits.");
                return;
            }
            long mobile = Long.parseLong(mobileStr);

            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setMobile(mobile);
            user.setUsername(username);
            user.setPassword(password);
            
            log.setUser(userl);
            log.setPass(passl);
            
            int register = dao.register(user);
            int register1=daol.register(log);
            if (register > 0) {
                res.sendRedirect("login.html");
            } else {
                res.sendRedirect("error.html");
            }
        } catch (Exception e) {
            // Handle other exceptions
            res.getWriter().println( e.getMessage());
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse res, dao dao) throws IOException {
        String username = req.getParameter("username");

        try {
            int result = dao.delete(username); 
            if (result > 0) {
                res.sendRedirect("delete.html");
            } else {
                res.getWriter().println(" Delete operation failed.");
            }
        } catch (Exception e) {
            res.getWriter().println(e.getMessage());
        }
    }

    private void handleEdit(HttpServletRequest req, HttpServletResponse res, dao dao) throws IOException {
        String username = req.getParameter("username");
        String newFirstname = req.getParameter("firstname");
        String newLastname = req.getParameter("lastname");
        long newMobile = Long.parseLong(req.getParameter("mobile"));
        String newPassword = req.getParameter("password");

        try {
            int result = dao.edit(username, newFirstname, newLastname, newMobile, newPassword); // Updated method signature
            if (result > 0) {
                res.sendRedirect("editsucc.html");
            } else {
                res.getWriter().println("Edit operation failed.");
            }
        } catch (Exception e) {
            res.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
