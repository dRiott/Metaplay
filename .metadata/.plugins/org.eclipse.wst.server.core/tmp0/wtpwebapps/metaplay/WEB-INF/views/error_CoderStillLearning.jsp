<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Something went wrong.</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			


	<!-- <div style="display:hidden">
		<h3>Failed URL:</h3>
		<p>${url}</p>
		
		<br/>	
		
		<h4>Message:</h4>
		<p>${exception.message }</p>
		<pre>${exception.stackTrace}</pre>
		<br/>
		
		<ul>
			<c:forEach items="${exception.stackTrace}" var="stackTraceItem">
				<li>${stackTraceItem}</li> 
	   		</c:forEach>
		</ul>
	</div> -->

	<div class="container">
	
	
	<h1 class="dH1">Oops</h1>
	
	<p>Something went wrong. As much as we'd like to skirt blame... Let's be realistic.</p>

	<br>
	
	<h5><em>It's my fault! I'm still learning. Thanks for helping the website improve!</em></h5>
	
	<hr/>
	<small>SrslyTHoSry</small>
	<br/>
	<small>Obligatory Flying Cats</small>
	<br/>
	<img src="<spring:url value="/resources/img/cats.gif" />" id="catsGif"	alt="cats.gif" style="width:auto\9"></img>
	
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>