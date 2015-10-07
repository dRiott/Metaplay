<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Role Manager</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container" id="mainContainer">

		<div class="row" id="rowHeader">
			<h1>Role</h1>
		</div>

		<spring:url value="/role/save" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post" modelAttribute="role">
			<div class="row" id="mainRowInsideForm">
				<div class="col-md-4">
					<label for="role-name">Name</label>
					<form:input type="text" path="name" cssClass="form-control" id="role-name" placeholder = "God"/>
				</div>

				<div class="form-group" style="float:clear;"></div>

				<div class="col-md-4">
					<label for="role-description">Description</label>
					<form:input type="text" path="description" cssClass="form-control" id="role-description" placeholder="This role controls the world. Use sparingly." />
				</div>
				
				<div class="form-group" style="float:clear;"></div>

				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>