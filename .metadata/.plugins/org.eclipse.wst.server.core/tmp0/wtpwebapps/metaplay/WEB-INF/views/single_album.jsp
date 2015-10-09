<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Album</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">

			<div class="form-group">
				<label for="project-name">Album Name</label> <span>${album.name}</span>
			</div>

			<div class="form-group">
				<label for="project-name">Artist</label> <span>${album.artist}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Record Label</label> <span>${album.recordLabel}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Tracks</label> <span>${album.numTracks}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Length</label> <span>${album.length}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Release Date</label> <span>${album.releaseDate}</span>
			</div>
			<div class="form-group">
				<label for="project-name">Description</label> <span>${album.description}</span>
			</div>

		<a href="<spring:url value="/browse/albums"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>