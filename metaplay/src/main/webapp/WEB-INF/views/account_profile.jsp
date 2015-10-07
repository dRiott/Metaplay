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

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
		<h1>Your Profile</h1>

		<div class="container">
			<div class="row">

				<div class="form-group">
					<label for="account-name">Account Name</label> <br /> <span>${account.accountname}</span>
				</div>

				<div class="form-group">
					<label for="account-email">Email</label> <br /> <span>${account.email}</span>
				</div>

				<label for="roles">Roles</label>
				<c:if test="${roles.size()==0 }">
					<c:out value="There are currently no roles assigned to ${account.accountname }." />
				</c:if>
				<c:forEach items="${roles}" var="role">
					<table style="border: 2px solid">
						<tr>
							<th style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">Role Name</th>
							<th style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">Role Description</th>
						</tr>
						<tr>
							<td style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">${role.name}</td>
							<td style="padding: 5px; border-left: 2px solid; border-top: 2px solid;">${role.description}</td>
						</tr>
					</table>
				</c:forEach>

			</div>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>