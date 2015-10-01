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
						id="biography" rows="10" cols="30" />
				</div>


				<%-- 				<div class="form-group">
					<label for="artist-image">Artist Image</label> 
					<form:input  path="artist.artistImage" cssClass="form-control" id="artist-image"/>
				</div> --%>
				
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group">
					<label for="genreName">Genre</label>
					<form:select path="genreName" id="genreName" cssClass="selectpicker">
						<form:options items="${genreOptions}" />
					</form:select>
					<div class="form-group" id="newGenreName" style="display:none;">
						<hr/>
						<label for="newGenreName"><em>* New Genre:</em></label>
						<form:input cssClass="form-control" path="newGenreName" id="newGenreNameInput" cssErrorClass="has-error" />
					</div>
					<div class="form-group" style="float:clear;"></div>
					<div class="form-group" id="newGenreDescription" style="display:none;">
						<label for="newGenreDescription"><em>New Genre Description:</em></label>
						<form:textarea rows="3" cssClass="form-control" path="newGenreDescription" id="newGenreDescriptionInput" cssErrorClass="has-error" />
						<hr/>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4">
						<label for="location-city">City</label>
						<form:input path="locationCity" cssClass="form-control"	id="location-city" />
					</div>
					<div class="col-md-4">
						<label for="location-state">State</label>
						<div class="form-group">
						<form:select path="locationState" cssClass="selectpicker" items="${stateOptions}"
							id="location-state" />
						</div>
					</div>
				</div>
				
				<!--  Artist Member fields here -->
				<div class="row">
					<div class="col-md-4">
						<label for="member1">Member #1 Full Name</label>
						<form:input path="member1" cssClass="form-control" id="member1" />
						<label for="member1">Member #1 Stage Name</label>
						<form:input path="member1StageName" cssClass="form-control" id="member1" />
					</div>
					<div class="col-md-4">
						<label for="member2">Member #2 Full Name</label>
						<form:input path="member2" cssClass="form-control" id="member2" />
						<label for="member2">Member #2 Stage Name</label>
						<form:input path="member2StageName" cssClass="form-control" id="member2" />
					</div>
					<div class="col-md-4">
						<label for="member3">Member #3 Full Name</label>
						<form:input path="member3" cssClass="form-control" id="member3" />
						<label for="member3">Member #3 Stage Name</label>
						<form:input path="member3StageName" cssClass="form-control" id="member3" />
					</div>
					<div class="form-group" style="float:clear;"></div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-4">
						<label for="member4">Member #4 Full Name</label>
						<form:input path="member4" cssClass="form-control" id="member4" />
						<label for="member4">Member #4 Stage Name</label>
						<form:input path="member4StageName" cssClass="form-control" id="member4" />
					</div>
					<div class="col-md-4">
						<label for="member5">Member #5 Full Name</label>
						<form:input path="member5" cssClass="form-control" id="member5" />
						<label for="member5">Member #5 Stage Name</label>
						<form:input path="member5StageName" cssClass="form-control" id="member5" />
					</div>
					<div class="col-md-4">
						<label for="member6">Member #6 Full Name</label>
						<form:input path="member6" cssClass="form-control" id="member6" />
						<label for="member6">Member #6 Stage Name</label>
						<form:input path="member6StageName" cssClass="form-control" id="member6" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				
				
				<!--  Album fields here -->
				<div class="form-group">
					<label for="albumAlbumName">Album</label>
					<form:select path="albumNameFromList" cssClass="selectpicker" id="albumAlbumName" >
						<form:options items="${albumOptions}" />
					</form:select>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumAlbumName" style="display:none;">
					<hr/>
					<label for="newAlbumAlbumName" style="font-style:italic;">New Album: Name</label>
					<form:input cssClass="form-control" path="theNewAlbumName" id="newAlbumAlbumNameInput" cssErrorClass="has-error" />
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumNumTracks" style="display:none;">
					<label for="albumNumTracks" style="font-style:italic;">New Album: Number of Tracks</label>
					<form:input path="albumNumTracks" cssClass="form-control"
						id="album-numTracks" />
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumReleaseDate" style="display:none;">
					<label for="newAlbumReleaseDate" style="font-style:italic;">New Album: Release Date</label><span><small>Please use format: 04-01-2012</small></span>
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