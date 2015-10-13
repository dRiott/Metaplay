<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Metaplay &copy Tracks</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body>
	
	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>			

	<div class="container" style="padding-left: 7%">
		
		<h1>Tracks</h1>
		<table class="table table-hover">
			<tbody>
				<tr>
					<th>Name</th><th>Number</th><th>Album</th><th>Length</th>
				</tr>
				<c:forEach items="${tracks}" var="track">
					<tr>
						<td><a href="<spring:url value="/browse/track/${track.id}"/>">${track.name}</a></td>
						<c:choose>
							<c:when test="${track.trackNumber!=null }">
								<td><c:out value="${track.trackNumber}" /></td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${track.album!=null}">
								<td><a href="<spring:url value="/browse/album/${track.album.id}"/>">${track.album.name}</a></td>
							</c:when>
							<c:otherwise>
								<td>Not yet assigned to an album.</td>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${track.length!=null }">
								<%-- Formatting the minutes from track.length --%>
								<fmt:formatNumber var="minutes" pattern="##" value="${track.length div 60}"/>
								<td><c:out value="${minutes}"/>:<c:out value="${track.length%60}"/></td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>