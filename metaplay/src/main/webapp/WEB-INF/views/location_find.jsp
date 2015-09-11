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
			<li class="list-group-item"><label>locationService.findLocation("Denver", "Colorado"): </label><br/><span>${currentLocation.city}, ${currentLocation.state}</span></li>
			<li class="list-group-item"><label>States:</label><br/> <span>${states}</span></li>
 			<li class="list-group-item"><label>Locations (Formatted):</label><br/><span>${all}</span></li>
		</ul>
		<a href="<spring:url value="/location/add"/>" class="btn btn-default">Rep Yo City (Add LocatioN)</a>
	</div>

	
</body>
</html>