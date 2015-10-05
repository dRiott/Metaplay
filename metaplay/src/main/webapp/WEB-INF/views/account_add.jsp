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


</head>
<body>
	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
	<div class="container"  id="1">
		<h2>Want to make Friendship?</h2>
		<h3>HonestDad.docx love friends: <span style="padding-left: 10px"></span><small>Prepare to be account'd.</small></h3>
	</div>

	<div class="container">

		<div class="row">
			<h2>Fill In Your Desired Credentials</h2>
		</div>

		<spring:url value="/account/review" var="thisFormURL" />                                 
		<form:form action="${thisFormURL}" method="post" modelAttribute="account" onsubmit="return validatePassword();">  
		<form:errors path="*" element="div" cssClass="errors"/>
            
            <div class="row" id="errorWritingSpace"></div>                                                                                    
			
			<div class="row" id="mainRow">                                                                    
				<div class="row">
					<div class="col-md-4">                                                         
						<label for="accountname">Account Name</label>                                
						<form:input type="text" path="accountname" id="accountname" cssClass="form-control" cssErrorClass="has-error"/>          
	                </div>
				</div>
				<div class="form-group" style="float:clear;"></div>
           	           
				<div class="form-group">                                                         
					<label for="account-passwordConfirm">Email Address</label>
					<form:input type="text" pattern="^\w+.?\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" path="email"  id="email" cssClass="form-control" cssErrorClass="has-error"/>
				</div>

				<div class="form-group">
					<label for="account-password">Password</label>
					<form:password path="password" id="password" cssClass="form-control" cssErrorClass="has-error" />
				</div>

				<div class="form-group">
					<label for="account-passwordConfirm">Confirm Password</label>
					<form:password path="password" id="confirmPassword" cssClass="form-control" cssErrorClass="has-error" />
				</div>
				
				<button type="submit" class="btn btn-default">Submit</button>
			
			</div>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	<script src="<spring:url value="/resources/js/validateAccount.js"/>"></script>
</body>
</html>