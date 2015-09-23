<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Credit Card Processor</title>
<link rel="stylesheet" href="<spring:url value="/resources/css/reset.css"/>" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/welcome.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
		<h1>Credit Card Processing</h1>

		<div class="container">

			<div class="row">
				<h2>Pen Your CC Info, Playa</h2>
			</div>

			<spring:url value="/payment/process" var="thisFormURL" />
			<form:form id="formCreditCard" action="${thisFormURL}" method="post"
				modelAttribute="creditCard" onsubmit="fixOtherValue(); return validate(this);">
				<form:errors path="*" element="div" cssClass="errors" />

				<div class="row">

					<div class="form-group">
						<label for="nameOnCard">Name On Card</label>
						<form:input type="text" path="nameOnCard" id="nameOnCard"
							value="Bobby Knowles" cssErrorClass="has-error" />

					</div>

					<div class="form-group">
						<label for="cardNumber">Card Number</label>
						<form:input type="text"
							pattern="^\d{4}[ -]?\d{4}[ -]?\d{4}[ -]?\d{4}$" path="cardNumber"
							id="cardNumber" value="1234-1234-1234-1234"
							cssErrorClass="has-error" />
					</div>

					<div class="form-group">
						<label for="cardTypeOptions">Card Type</label>
						<form:select path="cardType" cssClass="selectpicker" id="cardTypeOptions" >
							<form:option value="Mastercard" label="Mastercard"/>
							<form:options items="${cardTypeOptions}" />
						</form:select>
							
						<div class="form-group" id="optionCardTypeInput" style="display:none;">
							<label for="nameOnCard">Other Card Type:</label>
							<form:input type="text" path="cardType" id="otherCardTypeOption" cssErrorClass="has-error" />
						</div>
					</div>
				

					<div class="form-group">
						<label for="cardExpDate">Expiration Date</label>
						<form:input path="cardExpDate" id="cardExpDate" value="4/20/2020"
							cssErrorClass="has-error" />
					</div>

					<div class="form-group">
						<label for="amount">Amount</label>
						<form:input path="amount" id="amount" value="100"
							cssErrorClass="has-error" />
					</div>

					<button type="submit" class="btn btn-default">Submit</button>

				</div>
			</form:form>
		</div>
	</div>
<script src="<spring:url value="/resources/js/validatePayment.js"/>"></script>
</body>
</html>