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
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
 --%><script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>


<body class="DVBody">

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1><a>Artist</a></h1>
		</div>

		<spring:url value="/artist/review" var="thisURL" />
		<form:form action="${thisURL}" method="POST"
			modelAttribute="createArtistWrapper">

			<div class="row">

				<div class="form-group">
					<label for="artist-name">Name (Required!)</label>
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
					<label for="genre-name">Genre</label>
					<form:input path="genreName" cssClass="form-control"
						value="Pop/Rock" id="genre-name" />
				</div>

				<div class="form-group">
					<label for="location-city">City</label>
					<form:input path="locationCity" cssClass="form-control"
						value="Denver" id="location-city" />
				</div>

				<div class="form-group">
					<label for="location-state">State</label>
					<form:input path="locationState" cssClass="form-control"
						value="Colorado" id="location-state" />
				</div>

				<div class="form-group">
					<label for="recordLabel-name">Record Label</label>
					<form:input path="recordLabelName" cssClass="form-control"
						value="Paw Tracks" id="recordLabel-name" />
				</div>

				<!--  Artist Member fields here -->
				<div class="form-group">
					<label for="member1">Artist Member #1</label>
					<form:input path="member1" cssClass="form-control" id="member1" />
				</div>
				<div class="form-group">
					<label for="member2">Artist Member #2</label>
					<form:input path="member2" cssClass="form-control" id="member2" />
				</div>
				<div class="form-group">
					<label for="member3">Artist Member #3</label>
					<form:input path="member3" cssClass="form-control" id="member3" />
				</div>
				<div class="form-group">
					<label for="member4">Artist Member #3</label>
					<form:input path="member4" cssClass="form-control" id="member4" />
				</div>
				<!--  Artist Member fields here -->


				<!--  Album fields here -->
				<div class="form-group">
					<label for="album-name">Album: Name</label>
					<form:input path="albumName" cssClass="form-control"
						id="album-name" />
				</div>
				<div class="form-group">
					<label for="album-numTracks">Album: Number of Tracks</label>
					<form:input path="albumNumTracks" cssClass="form-control"
						id="album-numTracks" />
				</div>
				<div class="form-group">
					<label for="album-releaseDate">Album: Release Date</label>
					<form:input path="albumReleaseDate" cssClass="form-control"
						id="album-releaseDate" />
				</div>
				<div class="form-group">
					<label for="album-albumCover">Album: Album Cover</label>
					<form:input path="albumAlbumCover" cssClass="form-control"
						id="album-albumCover" />
				</div>
				<!--  End Album fields -->

				<button type="submit" class="btn btn-default">Submit</button>

			</div>

		</form:form>

	</div>
</body>
</html>