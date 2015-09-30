<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
<%-- <script src="<spring:url value="/resources/js/validateAccount.js"/>"></script>
 --%>
 
<!-- <script>
	function fnAlertLogin() {
		alert("Login to Music");
		var accountName = document.getElementById("accountname").value;
		alert("Accountname: " + accountName);
		var password = document.getElementById("password").value;
		alert("Password: " + password);
		return false;
	}
	
	 onclick="return fnAlertLogin();"
</script> -->

</head>
<body onload="alertSubmitButton();">
	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<h1>Login</h1>
		</div>
		<spring:url value="/account/login" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post" modelAttribute="account">
		<form:errors path="*" element="div" cssClass="errors"/>
		
			<div class="row">
				<div class="form-group">
					<label for="accountname">Account</label>
					<form:input type="text" path="accountname" id="accountname" cssClass="form-control" cssErrorClass="has-error"/>
				</div>
				<div class="form-group">
					<label for="account-password">Password</label>
					<form:password path="password" id="password" cssClass="form-control" cssErrorClass="has-error" />
				</div>
				<button id="loginButton" type="submit" class="btn btn-default" >Login</button>
			</div>
	</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>	
</body>
</html>