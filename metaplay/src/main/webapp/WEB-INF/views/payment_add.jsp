<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Credit Card Processor</title>
   
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/validatePayment.js"/>"></script>
</head>

<body class="delayedReveal">
	<div id="wrapper">
		<jsp:include page="../views/fragments/headerSecurity.jsp"/>
		
		<div class="drContainer">
			
			<h1>Pen Your CC Info, Playa</h1>
			<h2 style="font-family: monospace, sans-serif;"><small><em>This is just for fun!</em></small></h2>
			<br/>
			<spring:url value="/payment/process" var="thisFormURL" />
			<form:form id="formCreditCard" action="${thisFormURL}" method="post" modelAttribute="creditCard" onsubmit="fixOtherValue(); return validate(this);">
				<form:errors path="*" element="div" cssClass="errors" />
	
				<div class="row">
					<div class="col-md-4">
						<label for="nameOnCard">Name On Card</label>
						<form:input type="text" path="nameOnCard" id="nameOnCard"
							 cssClass="form-control" cssErrorClass="has-error" />
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>
				
				<div class="row">
					<div class="col-md-4">
						<label for="cardNumber">Card Number</label>
						<form:input type="text" cssClass="form-control"
							pattern="^\d{4}[ -]?\d{4}[ -]?\d{4}[ -]?\d{4}$" path="cardNumber"
							id="cardNumber" cssErrorClass="has-error" />
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>
				
				<div class="row">
					<div class="col-md-4">
						<form:select path="cardType" cssClass="selectpicker" id="cardTypeOptions" >
							<form:option value="Mastercard" label="Mastercard"/>
							<form:options items="${cardTypeOptions}" />
						</form:select>
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>
				
				<div class="row">
					<div class="col-md-6" id="optionCardTypeInput" style="display:none;">
						<label for="nameOnCard" style="font-style:italic;">Other Card Type:</label>
						<form:input type="text" cssClass="form-control" path="otherCardType" id="otherCardTypeOption" cssErrorClass="has-error" />
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>
				
				<div class="row">
					<div class="col-md-4">
						<label for="cardExpDate">Expiration Date</label>
						<form:input path="cardExpDate" cssClass="form-control" id="cardExpDate" cssErrorClass="has-error" />
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>
				
				<div class="row">
					<div class="col-md-4">
						<label for="amount">Amount</label>
						<form:input path="amount" cssClass="form-control" id="amount" cssErrorClass="has-error" />
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