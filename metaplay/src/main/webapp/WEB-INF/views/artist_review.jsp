<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h2>Please Review the Artist for Accuracy</h2>

			<div class="form-group">
				<label for="artist-name">Name</label> <span>${artist.name}</span>
			</div>

			<div class="form-group">
				<label for="genre">Type</label> <span>${artist.genre }</span>
			</div>

			<div class="form-group">
				<label for="location">Location</label> <span>${artist.location}</span>
			</div>

			<div class="form-group">
				<label for="recordlabel-id">Record Label ID (Required)</label> <span>${artist.recordlabelId}</span>
			</div>
			
						
				<!--  Album fields here -->
				<div class="form-group">
					<label for="album-name">Album: Name</label><span>${artist.album.name }</span>
				</div>
				<div class="form-group">
					<label for="album-numTracks">Album: Number of Tracks</label><span>${artist.album.numTracks }</span>
				</div>
				<div class="form-group">
					<label for="album-artistId">Album: Artist ID</label><span>${artist.album.artistId }</span>
				</div>
				<div class="form-group">
					<label for="album-releaseDate">Album: Release Date</label><span>${artist.album.releaseDate }</span>
				</div>
				<div class="form-group">
					<label for="album-albumCover">Album: Album Cover</label><span>${artist.album.albumCover }</span>
				</div>
				<!--  End Album fields -->
				
				
				<div class="form-group">
					<label for="biography">Biography</label><span>${artist.biography }</span>
				</div>
				
				<div class="form-group">
					<label for="artist-image">Artist Image</label> 
					<form:input  path="artistImage" cssClass="form-control" id="artist-image"/>
				</div>
				
			<div class="form-group">
				<label>Members</label>
				<c:forEach var="members" items="${artist.members}">
					<span>${members}</span>
				</c:forEach>
			</div>

			<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Edit</a>
			<a href="<spring:url value="/artist/save"/>"	class="btn btn-default">Save</a>
			
		</div>
	</div>
</body>
</html>