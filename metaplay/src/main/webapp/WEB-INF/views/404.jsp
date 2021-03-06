<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>404F41Lur</title>
   
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/404.js"/>"></script>
</head>

<body>
	<div class="drContainer" id="main" style="top: 200px;" >
		<!-- DRAKE IMAGES GET POPULATED HERE -->
	</div>
	
	<div id = "drakeButtonDiv">
		<button type="button" id="noMoreDrake" class="btn btn-primary btn-lg"
			style="position:absolute; top: 50px; left:50px; background-color:white; color:black">Bye Bye Drake</button>
	</div>

	<div id="drakeStatus" style="position:absolute; top: 90px; left: 250px"></div>	

</body>
</html>