<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="filter_div">
	<a id="hide1" href="#hide1" class="hide"> + Filter Search</a> <a
		id="show1" href="#show1" class="show"> - Filter Search</a>
	<div class="details">
		<div class="content_fil">
			<ul>
				<li>
					<a href="<c:url value='OrderKudosServlet.do?SortType=Asc'/>">Sort by Kudos (Ascending)</a>
				</li>
				<li>
					<a href="<c:url value='OrderKudosServlet.do?SortType=Desc'/>">Sort by Kudos (Descent)</a>
				</li>
				<li>
					<a href="<c:url value='HideBlockedRtServlet.do'/>"> Hide blocked routes</a>
				</li>
				<li>
					<a href="<c:url value='ShowBlockedRtServlet.do'/>"> Show blocked routes</a>
				</li>
				<li>
					<form action="OrderKudosServlet.do" class="form_mink">
						<input type="text" style="display:none" value="Min" name="SortType">
						<label for="minkud">Min Kudos</label>
						<input name="minkud" class="minkud" type="number" min="0" value="0" step="1" >
						<input id="icon_minKud" type="submit" value="->">
					</form>
				</li>
			</ul>

		</div>
	</div>
</div>
<br>
