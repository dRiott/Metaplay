<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Album Manager</title>

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
	
		<spring:url value="/album/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="createAlbumWrapper">
		
			<div class="row">
				<h2>Please Review the Album</h2>
	
				<div class="form-group">
					<label for="albumName">* Album Name</label> <span>${createAlbumWrapper.name}</span>
				</div>

				<div class="form-group">
					<label for="albumArtistName">Artist</label>
					<c:if test="${createTrackWrapper.artistFromList!='** New Artist **' }">
						<c:out value="${createAlbumWrapper.artistFromList}"/>
					</c:if>
					<c:if test="${createTrackWrapper.artistFromList=='** New Artist **' }">
						<c:out value="${createAlbumWrapper.theNewArtist}"/>
					</c:if>
				</div>
				
				<div class="form-group">
					<label for="albumReleaseDate">Release Date</label> <span>${createAlbumWrapper.releaseDate}</span>
				</div>
				
				<div class="form-group">
					<label for="albumLengthMinutes">* Minutes</label> <span>${createAlbumWrapper.lengthMinutes}</span>
				</div>
				<div class="form-group">
					<label for="albumLengthSeconds">* Seconds</label> <span>${createAlbumWrapper.lengthSeconds}</span>
				</div>
				
				<label for="albumTracks">Tracks</label><br/>
				<c:forEach var="track" items="${createAlbumWrapper.tracks}">
					<c:if test="${track.name!=''}">
							<span>${track.trackNumber}.&nbsp</span>
							<span>${track.name}</span>
							<br/>
					</c:if>
				</c:forEach>
				
					
				<a href="<spring:url value="/album/add"/>" class="btn btn-default">Edit</a>
				<a href="<spring:url value="/album/save"/>" class="btn btn-default">Save</a>
				
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>