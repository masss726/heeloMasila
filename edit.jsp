<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit User</title>
    <link href="css/bootstrap.css" rel="stylesheet">
</head>
<body>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Edit User</h1>

        <form action="Controller?action=update" method="post">
           <c:forEach var="user" items="${userList}">
            <input type="hidden" name="username" value="${param.username}" />

            <div class="mb-3">
                <label for="firstname" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstname" name="firstname" value="${user.firstname}" required>
            </div>

            <div class="mb-3">
                <label for="lastname" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastname" name="lastname" value="${user.lastname}" required>
            </div>

            <div class="mb-3">
                <label for="mobile" class="form-label">Mobile</label>
                <input type="text" class="form-control" id="mobile" name="mobile" value="${user.mobile}" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" value="${user.password}" required>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Update User</button>
                <a href="http://localhost:8080/MasilaPVT/Controller?action=users" class="btn btn-secondary">Cancel</a>
            </div>
            </c:forEach>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1bO1jAO89hQojwbpJ1U9z8+HLVd4wS2c9z5g+EDUyco9dD45zvAzRUyLS92X4yM" crossorigin="anonymous"></script>
</body>
</html>
