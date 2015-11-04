<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Track Manager</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
	
	<script src="<spring:url value="/resources/js/trackAdd.js"/>"></script>
</head>

<body class="DVBody">

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1 class="dH1"><a>Track</a><span style="padding-left: 10px"></span><small><small>The * indicates a required field.</small></small></h1>
		
			<sec:authorize access="!hasAuthority('God')">
				<h3 class="dH1">Looks like you don't have priviledges to submit new info. <a href="<spring:url value="/account/requestRole"/>">Make a request!</a></h3>
			</sec:authorize>
		</div>

		<spring:url value="/track/save" var="thisURL" />
		<form:form action="${thisURL}" method="POST" modelAttribute="createTrackWrapper" onsubmit="return validate(this);">

			<div class="row">
				<div class="row">
					<div class="col-md-1">
						<label for="trackNumber"><small>Number</small></label>
						<form:input path="trackNumber" cssClass="form-control" type="number" min="0" id="trackNumber" placeholder="1"/>
					</div>
				
					<div class="col-md-4">
						<label for="trackName"><small>Name</small></label>
						<form:input path="name" cssClass="form-control" id="trackName" placeholder="In the Flowers" />
					</div>
					
					<div class="col-md-1">
						<label for="trackLengthMinutes"><small>Minutes</small></label>
						<form:input path="lengthMinutes" type="number" min="0" id="trackLengthMinutes"  cssClass="form-control" placeholder="5"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds"><small>Seconds</small></label>
						<form:input path="lengthSeconds" type="number" min="0" id="trackLengthSeconds" cssClass="form-control" placeholder="42"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="bpm" cssClass="form-control" type="number" min="0" id="trackBpm" placeholder="120"/>
					</div>
				</div>
			
				<div class="form-group" style="float:clear;"></div>
			
			<!-- START ARTIST -->	
				<div class="row">
					<div class="col-md-2">
						<label for="trackArtistName">Artist</label>
						<div class="form-group">
							<form:select path="artistFromList" cssClass="selectpicker" id="trackArtistName" >
								<form:options items="${artistOptions}" />
							</form:select>
						</div>
					</div>
					
					<div id="newTrackArtistName" style="display:none;">
						<div class="col-md-4">
							<label for="newTrackArtistName" style="font-style:italic;">New Artist:</label>
							<form:input cssClass="form-control" type="text" path="theNewArtist" id="newTrackArtistNameInput" cssErrorClass="has-error" />
						</div>
						
						<div class="col-md-2">
							<label for="newTrackArtistButton" style="font-style:italic;">Or...</label>
							<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Go To Add Artist Page</a>
						</div>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				
			<!-- END ARTIST -->	
					<div class="row">
					<div class="col-md-2">
						<label for="trackAlbumName">Album</label>
						<div class="form-group">
							<form:select path="albumFromList" cssClass="selectpicker" id="trackAlbumName" >
								<form:options items="${albumOptions}" />
							</form:select>
						</div>
					</div>
					
					<div id="newTrackAlbumName" style="display:none;">
						<div class="col-md-4">
							<label for="newTrackAlbumName" style="font-style:italic;">New Album:</label>
							<form:input path="theNewAlbum" id="newTrackAlbumNameInput" cssClass="form-control" cssErrorClass="has-error" />
						</div>
						
						<div class="col-md-2">
							<label for="newAlbumArtistButton" style="font-style:italic;">Or...</label>
							<a href="<spring:url value="/album/add"/>" class="btn btn-default" style="font-size: 12.5px;">Go To Add Album Page</a>
						</div>
					</div>
				</div>
				<!-- END ALBUM -->	
				
				<div class="form-group" style="float:clear;"></div>

				<div class="row">
					<div class="col-md-4">
						<label for="audioFile">Audio File</label><span class="dSpan"></span><small>mp3, wav, m4p, flac</small>
						<form:input type="file" path="mp3" id="audioFileInput" cssClass="btn btn-default btn-file" />
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">
					<div class="col-md-8">
						<label for="trackLyrics">Lyrics</label>
						<form:textarea path="lyrics" rows="6" cols="10" style="text-align:center" cssClass="form-control" id="trackLyrics" />
					</div>
				</div>
			
				<div class="form-group" style="float:clear;"></div>
			
				<button type="submit" class="btn btn-default">Submit</button>

			</div>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>