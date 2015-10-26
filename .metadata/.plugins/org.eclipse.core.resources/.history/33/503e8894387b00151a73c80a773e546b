<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Location Manager</title>
	
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
	<script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
</head>

<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">

		<div class="row">
			<h1>Location</h1>
		</div>

		<spring:url value="/location/review" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post"
			modelAttribute="location">


<!--  c:out values for showing validation errors to the user on the view. -->
<%--             <tr><td>User Id:</td><td><input name="userId" type="text" /></td><td><font color="red"><c:out value="${userIdError}" /></font> </td></tr>
            <tr><td>Password:</td><td><input name="userPassword" type="password"/></td><td><font color="red"><c:out value="${userPasswordError}" /></font></td></tr>
            <tr><td>Confirm Password:</td><td><input name="userConfirmPassword" type="password"/></td><td><font color="red"><c:out value="${userPasswordError}" /></font></td></tr>
            <tr><td>Name:</td><td><input name="userName" type="text"/></td><td><font color="red"><c:out value="${userPasswordError}" /></font></td></tr>
            <tr><td></td><td><input type="submit" value="Create"/></td></tr> --%>



			<div class="row">
				<div class="row">
					<div class="col-md-4">                                                         
						<label for="location-city">City</label>
					<form:input type="text" path="city" cssClass="form-control"
						id="location-city" placeholder="Tuscaloosa"/>
	                </div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>

				<div class="form-group">
					<label for="location-state">State</label>
					<form:select path="state" items="${stateOptions}" cssClass="selectpicker"
						id="location-state" />
				</div>

				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>