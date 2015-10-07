<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script src="<spring:url value="/resources/js/404.js"/>"></script>
		<script>	$(document).ready(addDrakes()); </script>
		<title>404F41Lur</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
		<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	</head>
	<body>
		<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
	
		<div class="container" id="main">
			<h1 id="main-h1">Your life has taken a strange turn...</h1>
			<small id="main-small">Be careful what you ask for.</small>
		</div>

		<div id = "drakeButtonDiv">
			<button type="button" id="noMoreDrake" class="btn btn-primary btn-lg">Bye Bye Drake</button>
		</div>

		<div id="drakeStatus" style="position:relative; left:15%; bottom:50%;"></div>	

		<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
	<script>$(window).load(addEventListeners());</script>
	</body>
</html>