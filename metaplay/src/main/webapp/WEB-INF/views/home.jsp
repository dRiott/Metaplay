<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Playlist Manager</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>


	<div class="container">
		<h2>Current Playlist</h2>
		<ul class="list-group">
			<li class="list-group-item"><label>Playlist Name:</label><span>${currentPlaylist.name}</span></li>
			<li class="list-group-item"><label>Type:</label><span>${currentPlaylist.type}</span></li>
			<li class="list-group-item"><label>Description:</label><br /> <span>${currentPlaylist.description}</span></li>
		</ul>
	</div>
</body>
</html>