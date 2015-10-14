<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search For Your Soul</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/AJAXsearch.js"/>"></script>
</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container" style="padding-left: 7%">
		<form:form name="testPage">
	 		<div class="row">
		 		<div class="col-md-4">
			 		<div id="hello">I am </div><br/>
			 		<input id="button" type="button" value= "Add name Text return" class="btn btn-default"/>
			 		<br/><br/>
			 		<div id="hello2">Hello my name is </div><br/>
			 		<input id="button2" type="button" value="Add name JSON return" class="btn btn-default"/>
				</div>
			</div>
	 		<div class="row">
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" style="float:clear;"></div>

	 			<div class="col-md-4">
		 	 		<input id="searchInput" type="text" class="form-control" name="query" placeholder="Search for Artists, Albums, Tracks..."/>
				</div>
			</div>
	
			<div class="form-group" style="float:clear;"></div>
				
			<div class="row">
				<div class="col-md-4">
		 			<div id="searchSpace">
		 				<label>Search Results</label>
		 				<table id="resultsTable">
		 					<tr>
		 						<th>Name</th><th>Other Field</th><th>Third Field</th>
		 					</tr>
		 				</table>
		 			</div>
	 			</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>