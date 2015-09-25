<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artist Manager</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>


<body class="DVBody">

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1><a>Artist</a>&nbsp&nbsp<small><small>The * indicates a required field.</small></small></h1>
		</div>

		<spring:url value="/artist/review" var="thisURL" />
		<form:form action="${thisURL}" method="POST"
			modelAttribute="createArtistWrapper" onsubmit="fixOtherValue();">

			<div class="row">

				<div class="form-group">
					<label for="artist-name">* Name</label>
					<form:input path="name" cssClass="form-control" id="artist-name" />
				</div>

				<div class="form-group">
					<label for="biography">Biography</label>
					<form:textarea path="biography" cssClass="form-control"
						id="biography" rows="3" />
				</div>


				<%-- 				<div class="form-group">
					<label for="artist-image">Artist Image</label> 
					<form:input  path="artist.artistImage" cssClass="form-control" id="artist-image"/>
				</div> --%>
				
				<div class="form-group">
					<label for="genreName">Genre</label>
					<form:select path="genreName" id="genreName" cssClass="selectpicker">
						<form:options items="${genreOptions}" />
					</form:select>
					<div class="form-group" id="newGenreName" style="display:none;">
						<hr/>
						<label for="newGenreName" style="font-style:italic;">* New Genre:</label>
						<form:input cssClass="form-control" path="genreName" id="newGenreNameInput" cssErrorClass="has-error" />
					</div>
					<div class="form-group" id="newGenreDescription" style="display:none;">
						<label for="newGenreDescription" style="font-style:italic;">Description:</label>
						<form:textarea rows="3" cssClass="form-control" path="genreDescription" id="newGenreDescriptionInput" cssErrorClass="has-error" />
						<hr/>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
						<label for="location-city">City</label>
						<form:input path="locationCity" cssClass="form-control"
							value="Denver" id="location-city" />
					</div>
					<div class="cold-md-4">
						<label for="location-state">State</label>
						<div class="form-group">
						<form:select path="locationState" cssClass="selectpicker" items="${stateOptions}"
							id="location-state" />
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="recordLabelName">Record Label</label>
					<form:select path="recordLabelName" cssClass="selectpicker" id="recordLabelName" >
						<form:options items="${recordLabelOptions}" />
					</form:select>
					<div class="form-group" id="newRecordLabelName" style="display:none;">
						<hr/>
						<label for="newRecordLabelName" style="font-style:italic;">New Record Label:</label>
						<form:input cssClass="form-control" type="text" path="recordLabelName" id="newRecordLabelNameInput" cssErrorClass="has-error" />
						<hr/>
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>

				<!--  Artist Member fields here -->
				<div class="row">
					<div class="col-md-4">
						<label for="member1">Artist Member #1</label>
						<form:input path="member1" cssClass="form-control" id="member1" />
					</div>
					<div class="col-md-4">
						<label for="member2">Artist Member #2</label>
						<form:input path="member2" cssClass="form-control" id="member2" />
					</div>
					<div class="col-md-4">
						<label for="member3">Artist Member #3</label>
						<form:input path="member3" cssClass="form-control" id="member3" />
					</div>
					<div class="form-group" style="float:clear;"></div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-4">
						<label for="member1">Artist Member #4</label>
						<form:input path="member4" cssClass="form-control" id="member4" />
					</div>
					<div class="col-md-4">
						<label for="member2">Artist Member #5</label>
						<form:input path="member5" cssClass="form-control" id="member5" />
					</div>
					<div class="col-md-4">
						<label for="member3">Artist Member #6</label>
						<form:input path="member6" cssClass="form-control" id="member6" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				
				
				<!--  Album fields here -->
				<div class="form-group">
					<label for="albumAlbumName">Album</label>
					<form:select path="albumName" cssClass="selectpicker" id="albumAlbumName" >
						<form:options items="${albumOptions}" />
					</form:select>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumAlbumName" style="display:none;">
					<hr/>
					<label for="newAlbumAlbumName" style="font-style:italic;">New Album: Name</label>
					<form:input cssClass="form-control" path="albumName" id="newAlbumAlbumNameInput" cssErrorClass="has-error" />
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumNumTracks" style="display:none;">
					<label for="albumNumTracks" style="font-style:italic;">New Album: Number of Tracks</label>
					<form:input path="albumNumTracks" cssClass="form-control"
						id="album-numTracks" />
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumReleaseDate" style="display:none;">
					<label for="newAlbumReleaseDate" style="font-style:italic;">New Album: Release Date</label>
					<form:input path="albumReleaseDate" cssClass="form-control"
						id="newAlbumReleaseDate" />
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumAlbumCover" style="display:none;">
					<label for="newAlbumAlbumCover" style="font-style:italic;"><em>New Album: Cover</em></label>
					<form:input path="albumAlbumCover" cssClass="form-control" id="newAlbumAlbumCover" cssErrorClass="has-error" />
					<hr/>
				</div>
				<div class="form-group" style="float:clear;"></div>
				
				
				
				<!--  End Album fields -->

				<button type="submit" class="btn btn-default">Submit</button>

			</div>

		</form:form>

	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
	<script src="<spring:url value="/resources/js/artistAdd.js"/>"></script>
	
</body>
</html>