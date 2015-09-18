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
	<link rel="stylesheet" href="<spring:url value="/resources/css/welcome.css"/>" type="text/css" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- 	<script>function fnSetStatus(status){alert(status);document.getElementById("myStatus").innerHTML="Dem Boiz be liek..."+status;}</script>
 -->	
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
		<div class=text id=mainTest>
			<h1>W3lcome to the Fr0nt Page</h1>
			<h2>Naw meen?</h2>
			<h3>Naw meen?</h3>
			<h4>Naw meen?</h4>
			<h5>Naw meen?</h5>
			<h6>Naw meen?</h6>
		</div>
	</div>
	
<!-- 	<div id="myStatus" style="position:fixed; left:250px; bottom:250px; background-color:yellow;">status</div>
 -->	
	
	<script>
		/* fnSetStatus("Twerk it girl"); */
	</script>
	
</body>
</html>