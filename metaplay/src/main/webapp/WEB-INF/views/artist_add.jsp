<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1>Artist</h1>
		</div>

		<spring:url value="/artist/save" var="thisValueAttributesFormURL"/>
		<form:form action="${thisValueAttributesFormURL}" method="POST" modelAttribute="artist">
	
			<div class="row">

				<div class="form-group">
					<label for="artist-name">Name</label>
					<form:input path="name" cssClass="form-control" id="artist-name"/>
				</div>

				<div class="form-group">
					<label for="genre">Genre</label> 
					<form:select path="genre" items="${genreOptions}" cssClass="selectpicker"/>
				</div>

				<div class="form-group">
					<label for="location">Location</label> 
					<form:select path="location" items="${locationOptions}" cssClass="selectpicker"/>
				</div>

				<div class="form-group">
					<label for="recordlabel-id">Record Label ID (Required)</label> 
					<form:input path="recordlabelId" cssClass="form-control" value="2" id="recordlabel-id" />
				</div>
				
				<!--  Album fields here -->
				<div class="form-group">
					<label for="album-name">Album: Name</label>
					<form:input path="album.name" cssClass="form-control" id="album-name" />
				</div>
				<div class="form-group">
					<label for="album-numTracks">Album: Number of Tracks</label>
					<form:input path="album.numTracks" cssClass="form-control" id="album-numTracks" />
				</div>
				<div class="form-group">
					<label for="album-artistId">Album: Artist ID</label>
					<form:input path="album.artistId" cssClass="form-control" id="album-artistId" />
				</div>
				<div class="form-group">
					<label for="album-releaseDate">Album: Release Date</label>
					<form:input path="album.releaseDate" cssClass="form-control" id="album-releaseDate" />
				</div>
				<div class="form-group">
					<label for="album-albumCover">Album: Album Cover</label>
					<form:input path="album.albumCover" cssClass="form-control" id="album-albumCover" />
				</div>
				<!--  End Album fields -->
				
				
				<div class="form-group">
					<label for="biography">Biography</label>
					<form:textarea path="biography" cssClass="form-control" id="biography" rows="3"/>
				</div>
				
				<div class="form-group">
					<label for="artist-image">Artist Image</label> 
					<form:input  path="artistImage" cssClass="form-control" id="artist-image"/>
				</div>
				
				<div class="form-group">
					<label for="member">Artist Member</label> 
					<form:input  path="members[0]" cssClass="form-control" id="member"/>
				</div>
				<div class="form-group">
					<label for="member2">Artist Member 2</label> 
					<form:input  path="members[1]" cssClass="form-control" id="member2"/>
				</div>
				<div class="form-group">
					<label for="member3">Artist Member 3</label> 
					<form:input  path="members[2]" cssClass="form-control" id="member3"/>
				</div>
				
					<button type="submit" class="btn btn-default">Submit</button>
				
			</div>

		</form:form>

	</div>
</body>
</html>