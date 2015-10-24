<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Credit Card Processor</title>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<!-- <%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>
 -->
<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>
		<div class="container" style="padding-left: 7%" style="border: 2px dashed;">
			<h1 >Credit Card Processing</h1>
			<h2>Pen Your CC Info, Playa</h2>
			<spring:url value="/payment/process" var="thisFormURL" />
			<form:form id="formCreditCard" action="${thisFormURL}" method="post"
					modelAttribute="creditCard" onsubmit="fixOtherValue(); return validate(this);">
			<form:errors path="*" element="div" cssClass="errors" />
	
				<div class="row">
					<div class="col-md-4">
						<label for="nameOnCard">Name On Card</label>
						<form:input type="text" path="nameOnCard" id="nameOnCard"
							 cssClass="form-control" value="Bobby Knowles" cssErrorClass="has-error" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-4">
						<label for="cardNumber">Card Number</label>
						<form:input type="text" cssClass="form-control"
							pattern="^\d{4}[ -]?\d{4}[ -]?\d{4}[ -]?\d{4}$" path="cardNumber"
							id="cardNumber" value="1234-1234-1234-1234"
							cssErrorClass="has-error" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-4">
						<label for="cardTypeOptions">Card Type</label>
						<form:select path="cardType" cssClass="selectpicker" id="cardTypeOptions" >
							<form:option value="Mastercard" label="Mastercard"/>
							<form:options items="${cardTypeOptions}" />
						</form:select>
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-6" id="optionCardTypeInput" style="display:none;">
						<label for="nameOnCard" style="font-style:italic;">Other Card Type:</label>
						<form:input type="text" cssClass="form-control" path="cardType" id="otherCardTypeOption" cssErrorClass="has-error" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-4">
						<label for="cardExpDate">Expiration Date</label>
						<form:input path="cardExpDate" cssClass="form-control" id="cardExpDate" value="4/20/2020"
							cssErrorClass="has-error" />
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="row">
					<div class="col-md-4">
						<label for="amount">Amount</label>
						<form:input path="amount" id="amount" cssClass="form-control" value="100"
							cssErrorClass="has-error" />
					</div>
				</div>	
				<div class="form-group" style="float:clear;"></div>
				<button type="submit" class="btn btn-default">Submit</button>
				<div class="form-group" style="float:clear;"></div>
			</form:form>
	
		</div>
		<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	</div>	
<script src="<spring:url value="/resources/js/validatePayment.js"/>"></script>
</body>
</html>