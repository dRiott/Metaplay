<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Playlists</title>

	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container">
		
		<h1>Playlists</h1>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>Name</th><th>Type</th><th>Description</th>
				</tr>
				<c:forEach items="${playlists}" var="playlist">
					<tr>
						<td><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a></td>
						<td>${playlist.description}</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
		<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>