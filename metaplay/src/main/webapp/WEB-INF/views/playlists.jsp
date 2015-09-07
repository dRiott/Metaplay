<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Playlist Manager</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/header.jsp"></jsp:include>			

	<div class="container">
		
		<h2>Playlists</h2>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>Name</th><th>Type</th><th>Description</th>
				</tr>
				<c:forEach items="${playlists}" var="playlist">
					<tr>
						<td><a href="<spring:url value="/playlist/${playlist.playlistId}"/>">${playlist.name}</a></td>
						<td>${playlist.type}</td>
						<td>${playlist.description}</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
</body>
</html>