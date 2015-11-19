<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

	<div class="drContainer"  id="1">
		<h2>Want to make Friendship?</h2>
		<h3>Metaplay loves friends: <span class="dSpan"></span><small>Prepare to be account'd.</small></h3>
	</div>

	<div class="drContainer" >

		<div class="row drRow">
			<h2>Fill In Your Desired Credentials</h2>
		</div>

		<spring:url value="/account/save" var="thisFormURL" />                                 
		<form:form  name='f' action="${thisFormURL}" method="post" enctype="multipart/form-data" modelAttribute="createAccountWrapper"  onsubmit="return validatePassword();">  
		<form:errors path="*" element="div" cssClass="errors"/>
            
            <div class="row drRow" id="errorWritingSpace"></div>                                                                                    
			
			<div class="row drRow" id="mainRow">                                                                    
				<div class="row">
					<div class="col-md-4">                                                         
						<label for="accountname">Account Name</label>                                
						<form:input type="text" path="accountname" id="accountname" cssClass="form-control" cssErrorClass="has-error" placeholder="PoorYorick"/>          
	                </div>
				</div>
				<div class="form-group" style="clear:both;"></div>
               
                <div class="row">     
					<div class="col-md-6">                                                         
						<label for="account-passwordConfirm">Email Address</label>
						<form:input type="text" pattern="^\w+.?\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" path="email"  id="email" cssClass="form-control" cssErrorClass="has-error" placeholder = "jane.doe@email.com"/>
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>

				<div class="row">     
					<div class="col-md-6">
						<label for="account-password">Password</label>
						<form:password path="password" id="account-password" cssClass="form-control" cssErrorClass="has-error" placeholder = "Password"/>
					</div>
				</div>
	
				<div class="form-group" style="clear:both;"></div>

                <div class="row">     
					<div class="col-md-6">
						<label for="account-passwordConfirm">Confirm Password</label>
						<form:password path="" id="account-passwordConfirm" cssClass="form-control" cssErrorClass="has-error" placeholder = "Confirm Password" />
					</div>
				</div>
				
				<div class="form-group" style="clear:both;"></div>
				
				 <div class="row">     
					<div class="col-md-6">
						<label for="account-profilePicture">Profile Picture</label><span class="dSpan"></span><small>jpeg, jpg, png, or gif</small>
							<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="profilePicture" id="account-profilePicture" cssClass="btn btn-default btn-file" />
					</div>
				</div>
					
				<div class="form-group" style="clear:both;"></div>
				
				<button type="submit" class="btn btn-default">Submit</button>
			
			</div>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>