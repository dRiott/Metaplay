<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Something went wrong.</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container" style="padding-left: 7%">
	<h1>An error has occurred.</h1>
	<p>There was an issue with our database. Either what you want to create already exists, or violates the database's integrity.</p>
	
	<br/>
	
	<!-- <div style="display:hidden">
		<h3>Failed URL:</h3>
		<p>${url}</p>
		
		<br/>	
		
		<h4>Message:</h4>
		<p>${exception.message }</p>
		
		<ul>
			<c:forEach items="${exception.stackTrace}" var="stackTraceItem">
				<li>${stackTraceItem}</li> 
	   		</c:forEach>
		</ul>
	</div> -->
	
	<br>
	<hr/>
	<small>SrslyTHoSry</small>

	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>