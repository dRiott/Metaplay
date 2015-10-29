<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Role Manager</title>
	
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
	<script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
</head>
<body>
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>
	<div class="container" style="padding-left: 7%" id="mainContainer">

		<div class="row" id="rowHeader">
			<h1>Assign A Role To An Account</h1>
		</div>

		<spring:url value="/role/assign" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post" modelAttribute="roleWrapper">
			<div class="row" id="mainRowInsideForm">
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">
					<div class="col-md-1">
						<label for="accountFromList">Account</label>
						<form:select path="accountname" cssClass="selectpicker" id="accountFromList" >
							<form:options items="${accountOptions}" />
						</form:select>
					</div>
					<div class="col-md-1" style="padding-left: 70px;">
						<label for="roleFromList">Role</label>
						<form:select path="roleName" cssClass="selectpicker" id="roleFromList" >
							<form:options items="${roleOptions}" />
						</form:select>
					</div>
					<div class="col-md-1" style="padding-left: 70px;">
						<label for="roleFromList">Action</label>
						<form:select path="actionOption" cssClass="selectpicker" id="roleFromList" >
							<form:options items="${actionOptions}" />
						</form:select>
					</div>
				</div>
					
				<div class="form-group" style="float:clear;"></div>
					<div class="row">
						<div class="col-md-1">
							<label for="roleFromList"></label>
							<button type="submit" class="btn btn-default">Submit</button>
						</div>
				</div>
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>