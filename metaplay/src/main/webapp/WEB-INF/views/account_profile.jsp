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

				<div class="form-group">
					<label for="roles">Roles</label><br/>
					<c:if test="${roles.size()==0 }">
						<c:out value="There are currently no roles assigned to ${account.accountname }." />
					</c:if>
					<c:forEach items="${roles}" var="role">
						<th>Role Name</th>
						<th>Role Description></th>
						<tr>
							<td>${role.name}</td>
							<td>${role.description}</td>
						</tr>
					</c:forEach>
				</div>

			</div>
		</div>
	</div>
</body>
</html>