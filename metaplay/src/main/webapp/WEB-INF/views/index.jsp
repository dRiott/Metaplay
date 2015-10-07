<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>You're Here! Omg.. You're here?</title>

 	<link rel="stylesheet" href="<spring:url value="/resources/css/reset.css"/>" type="text/css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/index.css"/>" type="text/css" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="<spring:url value="/resources/js/index.js"/>"></script>
</head>
<body>
	<div id="container">
		<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
		<div class="wrapper" id="mainTest" style="position:relative">
			<h1 id="main-h1" style="position:relative; font-size:4em"></h1>
 			<button type="button" id="permaRaveButton" class="btn btn-primary btn-lg"
 				style="background: url(<spring:url value="/resources/img/raveGuy.jpg" />); 
 				position:relative; top: 10%; left: 5%; width: 10em; height: 7em; font-size: 30px;">PermaRave</button>
 			<button type="button" id="noMoreParty" class="btn btn-primary btn-lg"
 				style="position:relative; top: 20%; left: 10%; 
 				width: 10em; height: 2.5em;">(Too Old For This)</button>
		</div>
	</div>
	
	<!-- Dilla Thought Bubble. Delete? -->
	<!-- <div class="oval-thought-border" id="myStatus" style="position:fixed; left:45%; bottom:40%;"></div>	 -->
	
	<script>
		$(window).load(addEventHandlers());	
		$(window).load(fnSetStatus());
	</script>
	
</body>
</html>