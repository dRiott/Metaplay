<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
		<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
		<script src="<spring:url value="/resources/js/404.js"/>"></script>
		<script>	$(document).ready(addDrakes()); </script>
		<title>404F41Lur</title>
		<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
		<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	</head>
	<body>
		<div class="container" style="padding-left: 7%" id="errors">
			<c:if test="${param.logout !=null}">
				<div class="alert alert-success">
					<strong>Thank you!</strong> Logged out successfully.
				</div>
			</c:if>
		</div>

		<div class="container" style="padding-left: 7%" id="main" style="position:absolute; top: 90px; left: 250px">
			<h1 id="main-h1">Your life has taken a strange turn...</h1>
			<small id="main-small">Be careful what you ask for.</small>
		</div>

		<div id = "drakeButtonDiv">
			<button type="button" id="noMoreDrake" class="btn btn-primary btn-lg"
			style="position:absolute; top: 100px; left: 40px; background-color:white; color:black">Bye Bye Drake</button>
		</div>

		<div id="drakeStatus" style="position:absolute; top: 90px; left: 250px"></div>	

	
	<script>$(window).load(addEventListeners());</script>
	</body>
</html>