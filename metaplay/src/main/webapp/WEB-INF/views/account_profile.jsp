<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome {account.accountName}!</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">

		<div class="container">
			<div class="row" style="padding-left: 5%;">
				<h1 style="font-family: Times, serif;"><em>${account.accountname}</em></h1>
				
				<div class="form-group" >
					<c:set var="profilePictures" value="profilePictures"/>
					<img src="/metaplay/image/retrieve?foldername=profilePictures&filename=${account.accountname}" style="max-width: 300px; height:auto;"
						alt="Image not found" onerror="this.onerror=null; this.src='http://localhost:8080/metaplay/resources/img/default.gif'" width="144" height="103"/>
				</div>

				<div class="form-group">
					<label for="account-email">Email</label> <br /> <span>${account.email}</span>
				</div>

				<label for="roles">Roles</label>
				<c:choose>
				<c:when test="${roles.size()==0 }">
					<c:out value="There are currently no roles assigned to ${account.accountname}." />
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
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>