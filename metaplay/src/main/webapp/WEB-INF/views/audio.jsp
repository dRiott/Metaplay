<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cool! Mongo!</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container" style="padding-left:7%;">
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>
		<div>
			<h2>Yo</h2>
			<table>
				<tr><th>Audio</th></tr>
				<tr><td> <audio controls><source src="/metaplay/audio/retrieve?id=100&filename=Sixth Grade (that which ain't)" type="audio/mpeg" /></audio> </td></tr>
			</table>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>