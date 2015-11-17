<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome!</title>
    
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

		<div class="row drRow" id="errorWritingSpace">
			<c:if test="${not empty error}">
			   <div class="col-md-4 error" id="errorDiv">${error}</div>
			</c:if>
		</div>
		
		<div class="row drRow" id="mainRow">
			<form:form name='f' action='/account/login' method='POST'>
				<div class="row drRow" id="innerRow">
					<h3>Login with Account and Password</h3>
					<div class="row">
						<div class="col-md-4">
							<label>Account</label>
							<input type='text' name='username' value='' class="form-control" placeholder="Accountname (Not Necessarily An Email)" />
						</div>
					</div>

                    <div class="form-group" style="clear:both;"></div>

                    <div class="row">
						<div class="col-md-4">
							<label for="password">Password</label>
							<input name="password" type="password" id="password" class="form-control" placeholder="Password"/>
						</div>
					</div>

                    <div class="form-group" style="clear:both;"></div>

                    <div class="row">
						<div class="col-md-4">
							<input id="remember_me" name="remember_me" type="checkbox"  />
							<label for="remember_me" class="inline" >Remember Me</label>
						</div>
					</div>	

                    <div class="form-group" style="clear:both;"></div>

                    <button id="loginButton" type="submit" class="btn btn-default" >Login</button>
				</div>
			</form:form>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
    <script>
        var timeoutId;

        $(document).ready(function () {
            //hides the messages div after 2500ms if the logout message appears
            if($("#errorDiv").html().indexOf(" ") === -1) {
            } else {
                timeoutId = setTimeout(hideDiv, 2500);
            }

//            $(document.body).removeClass("delayedReveal");
        }); //end document.ready

        function hideDiv() {
            $("#errorDiv").slideUp(500);
            clearTimeout(timeoutId);
        }
    </script>
</body>	
</html>