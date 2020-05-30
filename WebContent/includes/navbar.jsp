<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="nav_div">
	<ul>
		<li><a href="<c:url value='ListRoutesServlet.do'/>">Home</a></li>
		<li><a href="<c:url value='CreateRouteServlet.do'/>">Add
				Route</a></li>
		<li class="search_li">
			<form class="form_nav" method="get" action="SearchServlet.do">
				<input type="search" name="search" class="search_input"
					placeholder="Search"> <input id="icon_search" type="submit"
					value="->">
			</form>
		</li>
		<li id="home_right_bar"><a
			href="<c:url value='UpdateUserServlet.do'/>">User Settings</a></li>
		<li id="home_right_bar"><a class="logout_h"
			href="<c:url value='LogoutServlet.do'/>">Logout</a></li>
		<li><img class="img_nav" alt="BHike" src="${pageContext.request.contextPath}/img/BHike_blue.png"></li>
	</ul>

</div>
<br>
<br>
<br>