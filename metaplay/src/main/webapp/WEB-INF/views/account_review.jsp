<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome ${createAccountWrapper.accountname}!</title>
    
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
	
		<spring:url value="/account/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="account">
		<form:errors path="*" element="div" cssClass="has-error"/>
		
			<div class="row drRow">
				<h2>Please Review Your Credentials for Accuracy</h2>
	
				<div class="form-group">
					<label>Account Name</label> <span class="dSpan">${account.accountname}</span>
				</div>
				
				<div class="form-group">
					<label>Email</label> <span class="dSpan">${account.email}</span>
				</div>
		
				<a href="<spring:url value="/account/add"/>" class="btn btn-default">Edit</a>
				<a href="<spring:url value="/account/save"/>" class="btn btn-default">Save</a>
			</div>
		</form:form>		
	</div>
</body>
</html>