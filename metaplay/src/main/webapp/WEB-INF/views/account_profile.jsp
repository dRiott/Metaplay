<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome!</title>
   
   	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
  	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/accountProfile.js"/>"></script>

	<!-- DYNAMICALLY SET THE TITLE TO ACCOUNTNAME -->
	<c:set var="accountName" value="Welcome ${account.accountname}!"/>
	<script> $(document).ready(function() { document.title = "${accountName}";}); </script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer" id="profileContainer">
		<div class="row drRow">
			<h1 class="dH1">${account.accountname}</h1>
			
			<div class="form-group" id="avatarDiv" >
				<c:set var="profilePictures" value="profilePictures"/>
				<img src="/image/retrieve?foldername=profilePictures&filename=${account.accountname}" style="max-width: 500px; height:auto;"
					alt="Image not found" id="avatarPic" onerror="this.onerror=null; this.src='metaplay.me/resources/img/default.gif'" width="144" height="103"/>
			
				<div class="form-group" style="clear:both;"></div>
				
				<div class="form-group" id="avatarUpload" style="display:none;">
					<spring:url value="/account/changeavatar" var="thisFormURL" />
					<form:form action="${thisFormURL}" method="post" enctype="multipart/form-data" modelAttribute="account">
					
						 <div class="row">     
							<div class="col-md-6">
								<label for="avatarInput">New Avatar</label><span class="dSpan"></span><small>jpeg, jpg, png, or gif</small>
								<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="avatar" id="avatarInput" cssClass="btn btn-default btn-file" />
							</div>
						</div>
							
						<div class="form-group" style="clear:both;"></div>
						
						<button type="submit" class="btn btn-default">Submit</button>
						
					</form:form>
				</div>
			</div>
			<div class="form-group">
				<label>Email</label> <br /> <span class="dSpan">${account.email}</span>
			</div>

			<label>Roles</label>
			<c:choose>
			<c:when test="${roles.size()==0 }">
				<div class="form-group">
					<c:out value="There are currently no roles assigned to ${account.accountname}." />
				</div>
			</c:when>
			<c:otherwise>
				<table style="border: 2px solid">
					<tr>
						<th style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">Role Name</th>
						<th style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">Role Description</th>
					</tr>
					<c:forEach items="${roles}" var="role">
						<tr>
							<td style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">${role.name}</td>
							<td style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">${role.description}</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
			</c:choose>

		</div>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"/>
	
</body>
</html>