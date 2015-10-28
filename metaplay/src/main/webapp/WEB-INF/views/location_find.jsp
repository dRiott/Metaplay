<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location Manager</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
	<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>


	<div class="container">
		<h2>Current Location</h2>
		<ul class="list-group">
			<li class="list-group-item"><label>locationService.findLocation("Portland", "Oregon"): </label><br/><span>${currentLocation.city}, ${currentLocation.state}</span></li>
			<li class="list-group-item"><label>States:</label><br/> <span>${states}</span></li>
 			<li class="list-group-item"><label>Locations (Formatted):</label><br/><span>${all}</span></li>
		</ul>
		<a href="<spring:url value="/location/add"/>" class="btn btn-default">Rep Yo City (Add LocatioN)</a>
	</div>

	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>