<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Location</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>

	<div class="container">
		<div class="row">

			<div class="form-group">
				<label for="project-name">Album Name</label> <span>${album.name}</span>
			</div>

			<div class="form-group">
				<label for="project-name">Artist</label> <span>${album.artist_id}</span>
			</div>
			
			<div class="form-group">
				<label for="project-name">Tracks</label> <span>${album.num_tracks}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Length</label> <span>${album.length_seconds}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Release Date</label> <span>${album.release_date}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Description</label> <span>${album.description}</span>
			</div>

		</div>
	</div>
</body>
</html>