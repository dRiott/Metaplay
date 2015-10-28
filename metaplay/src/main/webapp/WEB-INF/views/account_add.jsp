<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome!</title>
	
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>

</head>

<body>
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>
	<div class="container"  id="1">
		<h2>Want to make Friendship?</h2>
		<h3>HonestDad.docx love friends: <span style="padding-left: 10px"></span><small>Prepare to be account'd.</small></h3>
	</div>

	<div class="container" >

		<div class="row">
			<h2>Fill In Your Desired Credentials</h2>
		</div>

		<spring:url value="/account/save" var="thisFormURL" />                                 
		<form:form  name='f' action="${thisFormURL}" method="post" enctype="multipart/form-data" modelAttribute="createAccountWrapper"  onsubmit="return validatePassword();">  
		<form:errors path="*" element="div" cssClass="errors"/>
            
            <div class="row" id="errorWritingSpace"></div>                                                                                    
			
			<div class="row" id="mainRow">                                                                    
				<div class="row">
					<div class="col-md-4">                                                         
						<label for="accountname">Account Name</label>                                
						<form:input type="text" path="accountname" id="accountname" cssClass="form-control" cssErrorClass="has-error" placeholder="PoorYorick"/>          
	                </div>
				</div>
				<div class="form-group" style="float:clear;"></div>
               
                <div class="row">     
					<div class="col-md-6">                                                         
						<label for="account-passwordConfirm">Email Address</label>
						<form:input type="text" pattern="^\w+.?\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" path="email"  id="email" cssClass="form-control" cssErrorClass="has-error" placeholder = "jane.doe@email.com"/>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>

				<div class="row">     
					<div class="col-md-6">
						<label for="account-password">Password</label>
						<form:password path="password" id="password" cssClass="form-control" cssErrorClass="has-error" placeholder = "Password"/>
					</div>
				</div>
	
				<div class="form-group" style="float:clear;"></div>

                <div class="row">     
					<div class="col-md-6">
						<label for="account-passwordConfirm">Confirm Password</label>
						<form:password path="" id="confirmPassword" cssClass="form-control" cssErrorClass="has-error" placeholder = "Confirm Password" />
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				 <div class="row">     
					<div class="col-md-6">
						<label for="account-profilePicture">Profile Picture</label><span style="padding-left: 10px;"></span><small>jpeg, jpg, png, or gif</small>
							<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="profilePicture" id="confirmPassword" cssClass="btn btn-default btn-file" />
					</div>
				</div>
					
				<div class="form-group" style="float:clear;"></div>
				
				<button type="submit" class="btn btn-default">Submit</button>
			
			</div>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>