<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${account.accountname}!</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>"
	type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>
	<div>
		<h2>Want to make Friendship?</h2>
		<h3>HonestDad.docx love friends</h3>
		<h4>Prepare to be account'd.</h4>
	</div>

	<div class="container">

		<div class="row">
			<h1>Fill In Your Desired Credentials</h1>
		</div>

		<spring:url value="/account/review" var="thisFormURL" />
		<form:form action="${thisFormURL}" method="post" modelAttribute="account">

			<div class="row">

				<div class="form-group">
					<label for="accountname">Account Name</label>
					<form:input type="text" path="accountname" cssClass="form-control"
						id="accountname" />
				</div>


			<%-- 	<div class="form-group">
					<label for="account-email">Email</label>
					<form:input path="email" cssClass="form-control" id="account-email" />
				</div> --%>


				<div class="form-group">
					<label for="account-password">Password</label>
					<form:input type="password" path="password" cssClass="form-control" id="account-password" />
				</div>

				<div class="form-group">
					<label for="account-passwordConfirm">Confirm Password</label>
					<form:input type="password" path="passwordConfirm" cssClass="form-control" id="account-passwordConfirm" />
				</div>
				
				<button type="submit" class="btn btn-default">Submit</button>
				
			</div>
		</form:form>
	</div>
</body>
</html>