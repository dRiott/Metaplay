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
	<script>
/* 	
	document.write("Good Morning, and Welcome My Friends");
	document.write("<hr/>");
	document.write("Welcome To Metaplay"); */

	
/* 	function fnSetStatus(status){alert("What does J Dilla say?");document.getElementById("myStatus").innerHTML="J Dilla be liek..."+status;}
 */	
	
	</script>	
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
	
	<div id="myStatus" style="position:fixed; left:56%; bottom:40%; background-color:#FBD475;">Hmmm...</div>	
	
	<script>
		fnSetStatus("Daayum");
	</script>
	
</body>
</html>