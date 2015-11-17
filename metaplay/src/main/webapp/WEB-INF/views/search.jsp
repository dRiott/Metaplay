<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Search For Your Soul</title>

    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/AJAXsearch.js"/>"></script>
</head>
<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
			<h1 class="dH1">Search Metaplay!</h1>
	 		
			<div class="form-group" style="clear:both;"></div>
	 		
	 		<!-- RANDOM BUTTON -->
	 		<div class="row">
		 		<div class="col-md-4">
			 		<input id="randomResultButton" type="button" value="Random Result" class="btn btn-default"/>
			 		<span id="randomResultDiv"></span>
				</div>
			</div>
			
			<div class="form-group" style="clear:both;"></div>
			
			
			<!-- ARTIST SEARCH BOX -->
	 		<div class="row">
	 			<div class="col-md-4">
		 	 		<input id="searchArtists" type="text" class="form-control" name="query" placeholder="Search for Artists"/>
				</div>
			</div>
	
			<div class="form-group" style="clear:both;"></div>
				
			<!-- EVERYTHING SEARCH BOX -->
	 		<div class="row">
	 			<div class="col-md-4">
		 	 		<input id="searchAll" type="text" class="form-control" name="query" placeholder="Search for Artists, Albums, Tracks, Record Labels..."/>
				</div>
			</div>
	
			<div class="form-group" style="clear:both;"></div>
			
			<!-- RESULTS TABLE -->
			<div class="row" id="searchAllSpace">
 				<div class="col-md-8">
	 				<label>Search Results</label>
 					<table class="table table-hover" id="allResultsTable"><tr></tr></table>
 				</div>
			</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>