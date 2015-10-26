<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search For Your Soul</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
	<script src="<spring:url value="/resources/js/AJAXsearch.js"/>"></script>
	<script src="<spring:url value="/resources/js/waitingAnimation.js"/>"></script>
</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container" style="padding-left: 11%; padding-top: 5%;">
		<form:form name="testPage">
	 		
	 		<!-- RANDOM BUTTON -->
	 		<div class="row">
		 		<div class="col-md-4">
			 		<input id="randomResultButton" type="button" value="Random Result" class="btn btn-default"/>
			 		<span style="padding-left: 10px;" id="randomResultDiv"></span>
				</div>
			</div>
			
			<div class="form-group" style="float:clear;"></div>
			
			
			<!-- ARTIST SEARCH BOX -->
	 		<div class="row">
	 			<div class="col-md-4">
		 	 		<input id="searchArtists" type="text" class="form-control" name="query" placeholder="Search for Artists"/>
				</div>
			</div>
	
			<div class="form-group" style="float:clear;"></div>
				
			<!-- EVERYTHING SEARCH BOX -->
	 		<div class="row">
	 			<div class="col-md-4">
		 	 		<input id="searchAll" type="text" class="form-control" name="query" placeholder="Search for Artists, Albums, Tracks, Record Labels..."/>
				</div>
			</div>
	
			<div class="form-group" style="float:clear;"></div>
			
			<!-- RESULTS TABLE -->
			<div class="row" id="searchAllSpace">
 				<div class="col-md-8">
	 				<label>Search Results</label>
 					<table class="table table-hover" id="allResultsTable"><tr></tr></table>
 				</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>