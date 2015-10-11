<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Accounts</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container">
		
		<h1>Accounts</h1>
		<table class="table table-hover" style="width:60%;">
			<tbody>
				<tr>
					<th>Account</th><th>Playlists</th>
				</tr>
				<c:forEach items="${accounts}" var="account">
					<tr>
						<td><a href="<spring:url value="/browse/account/${account.id}"/>">${account.accountname}</a></td>
						<td>
							<div>
							<c:if test="${account.playlists.size()==0 }">
								<c:out value="This account has no playlists. Weird." />
							</c:if>
							<ul>
								<c:forEach items="${account.playlists}" var="playlist">
									<li>${playlist}</li>
								</c:forEach>
							</ul>
							</div>
						</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>

	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
</body>
</html>