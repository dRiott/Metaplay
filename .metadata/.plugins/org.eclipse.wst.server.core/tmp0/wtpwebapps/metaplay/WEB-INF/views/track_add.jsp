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
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>


<body class="DVBody">

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		<div class="row">
			<h1><a>Track</a><span style="padding-left: 10px"></span><small><small>The * indicates a required field.</small></small></h1>
		</div>

		<spring:url value="/track/save" var="thisURL" />
		<form:form action="${thisURL}" method="POST" modelAttribute="createTrackWrapper" onsubmit="return validate(this);">

			<div class="row">
				<div class="row">
					<div class="col-md-1">
						<label for="trackNumber"><small>* Number</small></label>
						<form:input path="trackNumber" cssClass="form-control" id="trackNumber" />
					</div>
				
					<div class="col-md-4">
						<label for="trackName"><small>* Name</small></label>
						<form:input path="name" cssClass="form-control" id="trackName" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				
		<!-- AUDIO FILE -->	
			<!-- accept="image/jpeg, image/png, image/gif, image/jpg"  -->
				 <div class="row">     
					<div class="col-md-6">
						<label for="audioFile">Audio File</label><span style="padding-left: 10px;"></span><small>mp3, wav, m4p, flac</small>
							<form:input type="file" path="mp3" id="audioFileInput" cssClass="btn btn-default btn-file" />
					</div>
				</div>
		<!-- END AUDIO FILE -->	
			
				<div class="form-group" style="float:clear;"></div>
			
			<!-- START ARTIST -->	
				<div class="row">
					<div class="col-md-1">
						<label for="trackArtistName">Artist</label>
						<form:select path="artistFromList" cssClass="selectpicker" id="trackArtistName" >
							<form:options items="${artistOptions}" />
						</form:select>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="form-group" id="newTrackArtistName" style="display:none;">
					<hr/>
					<label for="newTrackArtistName" style="font-style:italic;">New Artist:</label>
					<form:input cssClass="form-control" type="text" path="theNewArtist" id="newTrackArtistNameInput" cssErrorClass="has-error" />
					<div class="form-group" style="float:clear;"></div>
					<label for="newTrackArtistButton" style="font-style:italic;">Or...</label>
					<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Go To Add Artist Page</a>
					<hr/>
				</div>
			<!-- END ARTIST -->	
				
			<!-- START ALBUM -->	
				<div class="form-group">
					<label for="trackAlbumName">Album</label>
					<form:select path="albumFromList" cssClass="selectpicker" id="trackAlbumName" >
						<form:options items="${albumOptions}" />
					</form:select>
				</div>
				
				<div class="form-group" id="newTrackAlbumName" style="display:none;">
					<hr/>
					<label for="newTrackAlbumName" style="font-style:italic;">New Album:</label>
					<form:input path="theNewAlbum" id="newTrackAlbumNameInput" cssClass="form-control" cssErrorClass="has-error" />
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newTrackAlbumCover" style="display:none;">
					<label for="newTrackAlbumCover"><em>New Album Cover:</em></label>
					<form:input path="albumCover" id="newTrackAlbumCover" cssClass="form-control" cssErrorClass="has-error" />
					<div class="form-group" style="float:clear;"></div>
					<label for="newAlbumArtistButton" style="font-style:italic;">Or...</label>
					<a href="<spring:url value="/album/add"/>" class="btn btn-default">Go To Add Album Page</a>
					<hr/>
				</div>
				<div class="form-group" style="float:clear;"></div>
			<!-- END ALBUM -->	

				<div class="row">
					<div class="col-md-1">
						<label for="trackLengthMinutes"><small>* Minutes</small></label>
						<form:input path="lengthMinutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds"><small>* Seconds</small></label>
						<form:input path="lengthSeconds" id="trackLengthSeconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="bpm" cssClass="form-control" id="trackBpm" />
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="form-group">
						<label for="trackLyrics">Lyrics</label>
						<form:textarea path="lyrics" rows="10" cols="30" style="text-align:center" cssClass="form-control" id="trackLyrics" />
				</div>
			
				<button type="submit" class="btn btn-default">Submit</button>

			</div>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
	<script src="<spring:url value="/resources/js/populateTrackAdd.js"/>"></script>
	<script src="<spring:url value="/resources/js/trackAdd.js"/>"></script>
	<script src="<spring:url value="/resources/js/validateTrack.js"/>"></script>
	
</body>
</html>