<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Project Manager</title>
    
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="delayedReveal">

	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		
		<spring:url value="/location/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="location">
			<div class="row">
				<h2>Please Review the Location for Accuracy</h2>

				<div class="form-group">
					<label>City</label> <span class="dSpan">${location.city}</span>
				</div>

				<c:choose>
					<c:when test="${location.country.equals('United States')}">
						<div class="form-group">
							<label>State</label> <span class="dSpan">${location.state}</span>
						</div>

						<div class="form-group">
							<label>Country</label> <span class="dSpan">${location.country}</span>
						</div>
					</c:when>

					<c:otherwise>
						<div class="form-group">
							<label>Country</label> <span class="dSpan">${location.newCountry}</span>
						</div>
					</c:otherwise>
				</c:choose>

				<a href="<spring:url value="/location/add"/>" class="btn btn-default">Edit</a>
				<button type="submit" class="btn btn-default">Save</button>
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>