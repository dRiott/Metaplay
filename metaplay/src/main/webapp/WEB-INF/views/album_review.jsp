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
					<label for="trackNumber">* Album Name</label> <span>${createAlbumWrapper.name}</span>
				</div>
				
				<div class="form-group">
					<label for="trackName">* Name</label> <span>${createAlbumWrapper.name }</span>
				</div>
				<%-- <div class="form-group">
					<label for="trackArtistName">Artist</label><span>${createTrackWrapper.artist }</span>
				</div> --%>
				<div class="form-group">
					<label for="trackArtistName">Artist</label>
					<c:if test="${createTrackWrapper.artistFromList!='** New Artist **' }">
						<c:out value="${createAlbumWrapper.artistFromList}"/>
					</c:if>
					<c:if test="${createTrackWrapper.artistFromList=='** New Artist **' }">
						<c:out value="${createAlbumWrapper.theNewArtist}"/>
					</c:if>
				</div>
				
				<div class="form-group">
					<label for="trackArtistName">Album</label>
					<c:if test="${createTrackWrapper.albumFromList!='** New Album **' }">
						<c:out value="${createAlbumWrapper.albumFromList}"/>
					</c:if>
					<c:if test="${createTrackWrapper.albumFromList=='** New Album **' }">
						<c:out value="${createAlbumWrapper.theNewAlbum}"/>
					</c:if>
				</div>
				<div class="form-group">
					<label for="trackAlbumCover">Album Cover</label> <span>${createAlbumWrapper.albumCover}</span>
				</div>
				<div class="form-group">
					<label for="trackLengthMinutes">* Length: Minutes</label> <span>${createAlbumWrapper.lengthMinutes}</span>
				</div>
				<div class="form-group">
					<label for="trackLengthSeconds">* Seconds</label> <span>${createAlbumWrapper.lengthSeconds}</span>
				</div>
				<div class="form-group">
					<label for="trackBpm">BPM</label> <span>${createAlbumWrapper.bpm}</span>
				</div>
				<div class="form-group">
					<label for="trackLyrics">Lyrics</label> <span>${createAlbumWrapper.lyrics}</span>
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
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>