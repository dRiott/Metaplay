<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Manager</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
 --%><script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>


<body class="DVBody">

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1><a>Track</a></h1><span>The * indicates a required field.</span><br/>
		</div>

		<spring:url value="/track/review" var="thisURL" />
		<form:form action="${thisURL}" method="POST" modelAttribute="createTrackWrapper">

			<div class="row">
				<div class="form-group">
					<label for="trackNumber">* Track Number</label>
					<form:input path="trackNumber" cssClass="form-control" id="trackNumber" />
				</div>
				<div class="form-group">
					<label for="trackName">* Name</label>
					<form:input path="name" cssClass="form-control" id="trackName" />
				</div>
				<div class="form-group">
					<label for="trackArtistName">Artist</label>
					<form:input path="artist" cssClass="form-control" id="trackArtistName" />
				</div>
				<div class="form-group">
					<label for="trackAlbumName">Album</label>
					<form:input path="album" cssClass="form-control" id="trackAlbumName" />
				</div>
				<div class="form-group">
					<label for="trackAlbumCover">Album Cover</label>
					<form:input path="albumCover" cssClass="form-control" id="trackAlbumCover" />
				</div>
				<div class="form-group">
					<label for="trackLengthMinutes">* Length: Minutes</label>
					<form:input path="lengthMinutes" id="trackLengthMinutes" /> <span>&nbsp;&nbsp;&nbsp;</span>
					<label for="trackLengthSeconds">* Seconds</label>
					<form:input path="lengthSeconds" id="trackLengthSeconds" />
				</div>
				<div class="form-group">
					<label for="trackBpm">BPM</label>
					<form:input path="bpm" cssClass="form-control" id="trackBpm" />
				</div>
				<div>
					<label for="trackLyrics">Lyrics</label>
					<form:textarea path="lyrics" rows="10" cols="30" style="text-align:center" cssClass="form-control" id="trackLyrics" />
				</div>
			
				<button type="submit" class="btn btn-default">Submit</button>
				<br/><hr/>
				<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

			</div>
		</form:form>
	</div>
	<script src="<spring:url value="/resources/js/populateTrackAdd.js"/>"></script>
</body>
</html>