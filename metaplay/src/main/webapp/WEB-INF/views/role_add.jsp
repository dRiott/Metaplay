<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Role Manager</title>
    
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
  	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer" id="mainContainer">

		<div class="row drRow" id="rowHeader">
			<h1 class="dH1">Add A Role</h1>
		</div>
	
		<div class="form-group" style="clear:both;"></div>

        <div class="row drRow">  
			<spring:url value="/role/save" var="thisFormURL" />
			<form:form action="${thisFormURL}" method="post" modelAttribute="role">
				<div class="row" id="mainRowInsideForm">
					<div class="col-md-4">
						<label for="role-name">Name</label>
						<form:input type="text" path="name" cssClass="form-control" id="role-name" placeholder = "God"/>
					</div>
				</div>
		
				<div class="form-group" style="clear:both;"></div>
	
	            <div class="row">  
					<div class="col-md-4">
						<label for="role-description">Description</label>
						<form:input type="text" path="description" cssClass="form-control" id="role-description" placeholder="This role controls the world. Use sparingly." />
					</div>
				</div>
				<div class="form-group" style="clear:both;"></div>
					
				<button type="submit" class="btn btn-default">Submit</button>
			</form:form>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>