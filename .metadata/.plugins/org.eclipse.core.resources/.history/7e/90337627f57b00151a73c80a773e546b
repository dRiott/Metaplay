<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Manager</title>

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		
		<spring:url value="/location/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="location">
		
		<div class="row">
			<h2>Please Review the Location for Accuracy</h2>


			<div class="form-group">
				<label for="location-city">City</label> <span>${location.city}</span>
			</div>
			
			<c:choose>
				<c:when test="${location.country.equals('United States')}">
					<div class="form-group">
						<label for="location-state">State</label> <span>${location.state}</span>
					</div>
					
					<div class="form-group">
						<label for="location-country">Country</label> <span>${location.country}</span>
					</div>
				</c:when>
				
				<c:otherwise>
					<div class="form-group">
						<label for="location-country">Country</label> <span>${location.newCountry}</span>
					</div>
				</c:otherwise>
			</c:choose>

			
		

			<a href="<spring:url value="/location/add"/>" class="btn btn-default">Edit</a>
			<button type="submit" class="btn btn-default">Save</button>
		</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>