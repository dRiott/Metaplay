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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>

<body>
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>
	<div class="container" style="padding-left: 8%;">
		<div class="row" id="errorWritingSpace">
			<c:if test="${loginStatus.equals('fuckedUp')}">
				<div class="form-group">
					<label for="loginFailed" style="color:red;">The login failed: <c:out value="${counter}"/> times. Account/Password combination did not exist.</label> 
				</div>
			</c:if>
		</div>    
		
		<div class="row" id="mainRow">
				<c:if test="${param.error != null}">
					<div class="alert alert-error" role="alert">
							Failed to login.
							<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null }">
							Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
							</c:if>
					</div>
				</c:if>
			<form:form name='f' action='/metaplay/account/login' method='POST'>
				<div class="row" id="mainRow">
					<h3>Login with Account and Password</h3>
					<div class="row">
						<div class="col-md-6">
							<label for="accountname">Account</label>
							<input type='text' name='username' value='' class="form-control" placeholder="Accountname (Not Necessarily An Email)" />
						</div>
					</div>
					<div class="form-group" style="float:clear;"></div>
					<div class="row">
						<div class="col-md-6">
							<label for="account-password">Password</label>
							<input name="password" type="password" id="password" class="form-control" placeholder="Password"/>
						</div>
					</div>	
					<div class="row">
						<div class="col-md-4">
							<input id="remember_me" name="remember_me" type="checkbox"  />
							<label for="remember_me" class="inline" >Remember Me</label>
						</div>
					</div>	
					<div class="form-group" style="float:clear;"></div>
					<button id="loginButton" type="submit" class="btn btn-default" >Login</button>
				</div>
			</form:form>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>	
</body>	
</html>