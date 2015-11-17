<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome To Metaplay</title>
    
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
 	<link rel="stylesheet" href="<spring:url value="/resources/css/reset.css"/>" type="text/css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/index.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script language="javascript" type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script language="javascript" type="text/javascript" src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/index.js"/>"></script>
</head>

<body>
	<div id="container">
		<jsp:include page="../views/fragments/headerSecurity.jsp"/>

		<div class="drContainer" id="headerContainer">
            <div class="row form-group">
                <div class="col-md-6">
                    <h1>Welcome to Metaplay!</h1>
                </div>
                <c:if test="${not empty msg}">
                    <div class="col-md-3 msg" id="messageDiv">${msg}</div>
                </c:if>
            </div>

			<p>Metaplay is a playlist sharing website, where several of your friends and</p>
            <p>family can build a playlist together. Stay in touch with friends all over</p>
			<p>the world by collaborating via music. This is Metaplay's core concept.</p>
            <br/>
            <br/>
		</div>

        <button type="button" id="permaRaveButton" class="drBtn btn btn-primary btn-lg">Rave > Let's Dance!</button>
        <button type="button" id="noMoreParty" class="drBtn btn btn-primary btn-lg">(Too Old For This)</button>
    </div>
</body>
</html>