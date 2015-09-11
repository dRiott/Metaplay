<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location Manager</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>


	<div class="container">
		<h2>Current Location</h2>
		<ul class="list-group">
			<li class="list-group-item"><label>locationService.findLocation("Denver", "Colorado"): </label><span>${currentLocation.city}, ${currentLocation.state}</span></li>
<%-- 			<li class="list-group-item"><label>Locations:</label><br /> <span>${locationz}</span></li>
 --%>			<li class="list-group-item"><label>States:</label><br /> <span>${states}</span></li>
 				<li class="list-group-item"><label>Locations (Formatted):</label><br /> <span>${all}</span></li>
		</ul>
	</div>
	
	<div class="container">

		<div class="row">
			<h1>Location</h1>
		</div>

		<spring:url value="/location/review" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post" modelAttribute="location">

			<div class="row">

				<div class="form-group">
					<label for="location-city">Location City</label>
					<form:input type="text" path="city" cssClass="form-control"
						id="location-city" />
				</div>
				
				
				<div class="form-group">
					<label for="location-state">Location State</label>
					<form:input path="state" cssClass="form-control" id="location-state" />
				</div>

				<button type="submit" class="btn btn-default">Submit</button>
			</div>

		</form:form>
	</div>
</body>
</html>