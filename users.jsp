<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <!-- Link to Bootstrap CSS -->
    <link href="css/bootstrap.css" type="text/css" rel="stylesheet">
<body>

    <div class="container mt-5">
        <header class="text-center mb-4">
            <h1>Wllcome to Masila.PVT User Management</h1>
        </header>

        <!-- User Table -->
        <section class="user-table">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Mobile</th>
                        <th>Username</th>
                        <th>password</th>
                        <th>options</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.firstname}</td>
                            <td>${user.lastname}</td>
                            <td>${user.mobile}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>
                                <a href="Controller?action=edit&username=${user.username}" class="btn btn-warning btn-sm">Edit</a>
                                <a href="Controller?action=delete&username=${user.username}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Add New User Form -->
        <section class="add-user-form mt-5">
            <h2 class="text-center">Add New User</h2>
            <form action="Controller?action=register" method="post">
                <div class="mb-3">
                    <label for="firstname" class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstname" name="firstname" required>
                </div>
                <div class="mb-3">
                    <label for="lastname" class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastname" name="lastname" required>
                </div>
                <div class="mb-3">
                    <label for="mobile" class="form-label">Mobile</label>
                    <input type="text" class="form-control" id="mobile" name="mobile" required>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Add User</button>
                <a href="Controller?action=about" class="btn btn-danger w-100 text-white">About</a>
            </form>
        </section>
    </div>
    
</body>
</html>
