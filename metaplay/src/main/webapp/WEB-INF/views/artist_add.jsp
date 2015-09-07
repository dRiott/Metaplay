<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
	type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1>Artist</h1>
		</div>

		<form action="<spring:url value="/artist/save"/>" method="POST">

			<div class="row">

				<div class="form-group">
					<label for="artist-name">Name</label> <input type="text"
						id="artist-name" class="form-control" name="name" />
				</div>

				<div class="form-group">
					<label for="genre-id">Genre ID</label> <input id="genre-id"
						type="text" class="form-control" name="genreId" />
				</div>

				<div class="form-group">
					<label for="genre-id">Location</label> <select id="genre-id"
						name="type" class="selectpicker">
						<option></option>
						<option value="seatle">Seattle</option>
						<option value="losangeles">Los Angeles</option>
						<option value="portland">Portland</option>
						<option value="denver">Denver</option>
						<option value="sanfrancisco">San Francisco</option>
						<option value="dallas">Dallas</option>
						<option value="chicago">Chicago</option>
						<option value="newyork">New York</option>
						<option value="other">Other</option>
					</select>
				</div>



				<div class="form-group">
					<label for="recordlabel-id">Record Label ID</label> <input id="recordlabel-id"
						type="text" class="form-control" name="recordlabelId" />
				</div>
				
				<div class="form-group">
					<label for="biography">Biography</label>
					<textarea class="form-control" name="biography" rows="4"></textarea>
				</div>
				
				<div class="form-group">
					<label for="artist-image">Artist Image</label> <input id="artist-image"
						type="text" class="form-control" name="artistImage" />
				</div>

				<button type="submit" class="btn btn-default">Submit</button>

			</div>

		</form>

	</div>
</body>
</html>