<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/milligram.css" />
    <title>
    <c:choose>
		<c:when test="${CheckType eq 'Update'}">BHike Update User</c:when>
		<c:when test="${CheckType eq 'Delete'}">BHike Delete Account</c:when>
		<c:when test="${CheckType eq 'Create'}">BHike Create New User</c:when>
    </c:choose></title>
</head>

<body>
    <div class="div_settings">
        <h1 class="sett_h1">BHike</h1>
        <p id="error_log" class="login_field">${errormsg}</p>
        
            <c:choose>
                <c:when test="${CheckType eq 'Update'}"><p class="login_h1"> Replace the field to change and confirm password. </p></c:when>
                <c:when test="${CheckType eq 'Delete'}"><p class="login_h1"> Confirm password to proceed. </p></c:when>
                <c:when test="${CheckType eq 'Create'}"><p class="login_h1">  Create a new account of BHike. </p></c:when>
            </c:choose>
       
        <form method="post" action=
                <c:choose>
	                <c:when test="${CheckType eq 'Update'}"> "UpdateUserServlet.do" </c:when>
	                <c:when test="${CheckType eq 'Delete'}"> "DeletUserServlet.do" </c:when>
	                <c:when test="${CheckType eq 'Create'}"> "CreateUserServlet.do" </c:when>
           	 	</c:choose>>
            <fieldset class="login_field">
                <input type="text" name="usern" class="inputs" placeholder="UserName" required value="${user.getUsername()}"
                <c:if test="${not empty user.getUsername()}"> readonly </c:if>><br>
                <input type="text" name="email" class="inputs" placeholder="Email" required value="${user.getEmail()}"
                <c:if test="${CheckType eq 'Delete'}"> readonly </c:if>><br>
                <input type="password" name="passw" class="inputs" placeholder="Password" required value="${user.getPassword()}"
                <c:if test="${CheckType eq 'Delete'}"> readonly </c:if>>
                <input type="password" name="passw2" class="inputs" placeholder="Confirm Password" required><br>


            </fieldset>
            <div class="create_us">
                <input name="sub_log" type="submit" class="butt_log" value=
                <c:choose>
	                <c:when test="${CheckType eq 'Update'}"> "Update" </c:when>
	                <c:when test="${CheckType eq 'Delete'}"> "Confirm Delete" </c:when>
	                <c:when test="${CheckType eq 'Create'}"> "Create User" </c:when>
           	 	</c:choose>>
           	 	 <a class="cancel_c" href=<c:choose>
	                <c:when test="${CheckType eq 'Update'}"><c:url value='LoginServlet.do' /> </c:when>
	                <c:when test="${CheckType eq 'Delete'}"><c:url value='UpdateUserServlet.do' /> </c:when>
	                <c:when test="${CheckType eq 'Create'}"><c:url value='LoginServlet.do' /> </c:when>
           	 	</c:choose>> Cancel </a>
            </div>
             
        </form>
        <c:if test="${CheckType eq 'Update'}"> <a class="delete_account" href=<c:url value='DeletUserServlet.do' />>
                Delete Account </a> </c:if>
    </div>
</body>

</html>
