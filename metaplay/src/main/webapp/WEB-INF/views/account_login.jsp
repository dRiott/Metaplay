<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<body onload='document.f.username.focus(); alertSubmitButton();'>
	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
	
	<h3>Login with Accountname and Password</h3>
	<form name='f' action='/metaplay/login' method='POST'>
		<table>
			<tr>
				<td>Account:</td>
				<td><input type='text' name='username' value='' style="class:form-control;" ></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' style="class:form-control;"  /></td>
			</tr>
			<tr style="display:none;"><td><input name="_csrf" type="hidden" value="7160179f-cd6a-4e50-85bf-d646fba8a60e" /></td></tr>
			
		</table>
		<button id="loginButton" type="submit" class="btn btn-default" >Login</button>
	</form>
		<jsp:include page="../views/fragments/footer.jsp"></jsp:include>	
	<script src="<spring:url value="/resources/js/loginAccount.js"/>"></script>
</body>	
		
<%-- 		
		<spring:url value="/metaplay/login" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post">
		<form:errors path="*" element="div" cssClass="errors"/>

		<div class="row" id="errorWritingSpace">
				<c:if test="${loginStatus.equals('fuckedUp')}">
					<div class="form-group">
						<label for="loginFailed" style="color:red;">The login failed: <c:out value="${counter}"/> times. Account/Password combination did not exist.</label> 
					</div>
				</c:if>
			</div>       
			
			<div class="row" id="mainRow">
				<div class="row">
					<div class="col-md-6">
						<label for="accountname">Account</label>
						<form:input type="text" path="accountname" id="accountname" cssClass="form-control" cssErrorClass="has-error"/>
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-6">
						<label for="account-password">Password</label>
						<form:password path="password" id="password" cssClass="form-control" cssErrorClass="has-error" />
					</div>
				</div>	
				<div class="form-group" style="float:clear;"></div>
				<input name="_csrf" type="hidden" value="7160179f-cd6a-4e50-85bf-d646fba8a60e" />
				
				<button id="loginButton" type="submit" class="btn btn-default" >Login</button>
			</div>
	</form:form>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>	
	<script src="<spring:url value="/resources/js/loginAccount.js"/>"></script>
	
</body> --%>
</html>