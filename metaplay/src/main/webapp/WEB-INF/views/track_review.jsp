<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Manager</title>

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
	
		<spring:url value="/track/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="createTrackWrapper">
		
			<div class="row">
				<h2>Please Review the Track for Accuracy</h2>
	
				<div class="form-group">
					<label for="trackNumber">Track Number (Required!)</label> <span>${createTrackWrapper.trackNumber}</span>
				</div>
				<div class="form-group">
					<label for="trackName">Name (Required!)</label> <span>${createTrackWrapper.name }</span>
				</div>
				<div class="form-group">
					<label for="trackArtistName">Artist (Required!)</label>${createTrackWrapper.artist }</span>
				</div>
				<div class="form-group">
					<label for="trackAlbumName">Album</label> <span>${createTrackWrapper.album}</span>
				</div>
				<div class="form-group">
					<label for="trackAlbumCover">Album Cover</label> <span>${createTrackWrapper.albumCover}</span>
				</div>
				<div class="form-group">
					<label for="trackLengthSeconds">Length Seconds (Required!)</label> <span>${createTrackWrapper.lengthSeconds}</span>
				</div>
				<div class="form-group">
					<label for="trackLengthMinutes">Length Minutes (Required!)</label> <span>${createTrackWrapper.lengthSeconds}</span>
				</div>
				<div class="form-group">
					<label for="trackBpm">BPM</label> <span>${createTrackWrapper.bpm}</span>
				</div>
				<div class="form-group">
					<label for="trackLyrics">Lyrics</label> <span>${createTrackWrapper.lyrics}</span>
				</div>					
				
		<!--  START Exampe ForEach -->
				<!-- <div class="form-group">
					<label>Members</label>
					<c:forEach var="members" items="${artist.members}">
						<span>${members}</span>
					</c:forEach>
				</div> -->
					
				<a href="<spring:url value="/track/add"/>" class="btn btn-default">Edit</a>
				<a href="<spring:url value="/track/save"/>" class="btn btn-default">Save</a>
				
			</div>
		</form:form>
	</div>
</body>
</html>