<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BHike Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/milligram.css">
<!-- Include the AngularJS library -->
<script src="../js/angular/angular.js"></script>
<script src="../js/angular/angular-route.js"></script>
<!-- Modules -->
<script src="../js/app.js"></script>
</head>

<body class="body_log">
    <div class="div_log">
        <h1 class="login_h1">Login</h1>
        <p id="error_log" class="login_field" >${messages}</p>
        <form action="LoginServlet.do" method="POST" >
            <fieldset class="login_field">
                <input type="text" id="user" name="user" class="inputs" placeholder="User" value="${user_val}" required><br>
                <input type="password" id="passw" name="passw" placeholder="Password" required><br>
            </fieldset>
            <div class="create_us">
                <input name="sub_log" type="submit" class="butt_log" value="Login">
                <p> You don't have an account? <a href="CreateUser"> Create one </a> </p>
            </div>

        </form>
		<a class="login_home" href="mainPageServlet.do">Main Page</a>
			<br>
			<p></p>
    </div>
</body>

</html>