<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Something went wrong.</title>
    
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<!-- Comments for the Stack Track, Devs can see, Client will not -->
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

	<div class="drContainer">
		<h1 class="dH1">Security Issue</h1>
		<p>Something went wrong. The token submitted did not match the expectation. Please try again!</p>
	
		<br>
		
		<hr/>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>