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

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
	
		<spring:url value="/track/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="createTrackWrapper">
		
			<div class="row">
				<h2>Please Review the Track for Accuracy</h2>
	
				<div class="form-group">
					<label for="trackNumber">* Track Number</label> <span class="dSpan">${createTrackWrapper.trackNumber}</span>
				</div>
				<div class="form-group">
					<label for="trackName">* Name</label> <span class="dSpan">${createTrackWrapper.name }</span>
				</div>
				<%-- <div class="form-group">
					<label for="trackArtistName">Artist</label><span class="dSpan">${createTrackWrapper.artist }</span>
				</div> --%>
				<div class="form-group">
					<label for="trackArtistName">Artist</label>
					<c:if test="${createTrackWrapper.artistFromList!='** New Artist **' }">
						<c:out value="${createTrackWrapper.artistFromList}"/>
					</c:if>
					<c:if test="${createTrackWrapper.artistFromList=='** New Artist **' }">
						<c:out value="${createTrackWrapper.theNewArtist}"/>
					</c:if>
				</div>
				
				<div class="form-group">
					<label for="trackArtistName">Album</label>
					<c:if test="${createTrackWrapper.albumFromList!='** New Album **' }">
						<c:out value="${createTrackWrapper.albumFromList}"/>
					</c:if>
					<c:if test="${createTrackWrapper.albumFromList=='** New Album **' }">
						<c:out value="${createTrackWrapper.theNewAlbum}"/>
					</c:if>
				</div>
				<div class="form-group">
					<label for="trackAlbumCover">Album Cover</label> <span class="dSpan">${createTrackWrapper.albumCover}</span>
				</div>
				<div class="form-group">
					<label for="trackLengthMinutes">* Length: Minutes</label> <span class="dSpan">${createTrackWrapper.lengthMinutes}</span>
				</div>
				<div class="form-group">
					<label for="trackLengthSeconds">* Seconds</label> <span class="dSpan">${createTrackWrapper.lengthSeconds}</span>
				</div>
				<div class="form-group">
					<label for="trackBpm">BPM</label> <span class="dSpan">${createTrackWrapper.bpm}</span>
				</div>
				<div class="form-group">
					<label for="trackLyrics">Lyrics</label> <span class="dSpan">${createTrackWrapper.lyrics}</span>
				</div>					
				
		<!--  START Exampe ForEach -->
				<!-- <div class="form-group">
					<label>Members</label>
					<c:forEach var="members" items="${artist.members}">
						<span class="dSpan">${members}</span>
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