<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> --%>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Tracks</title>
  
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
 	<script src="<spring:url value="/resources/js/getAudio.js"/>"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		
		<h1 class="dH1">Tracks</h1>
		<sec:authorize access="!isAuthenticated()">
			<h3 class="dH1">Looks like you're not logged in - you won't be able to stream music. <a href="<spring:url value="/account/login"/>">Login!</a></h3>
		</sec:authorize>
		<br/>
		
		<table class="table table-hover browseTable">
			<tbody>
				<tr>
					<th>No.</th><th>Name</th><th>Album</th><th>Length</th>
				</tr>
				<c:forEach items="${tracks}" var="track" varStatus="count">
					<tr>
						<c:choose>
							<c:when test="${track.trackNumber!=null }">
								<td><c:out value="${track.trackNumber}" /></td>
							</c:when>
							<c:otherwise>
								<td>-</td>
							</c:otherwise>
						</c:choose>

						<td>
							<a href="<spring:url value="/browse/track/${track.id}"/>">${track.name}</a>
							<span class="dSpan"></span>		
							<sec:authorize access="isAuthenticated()">				
								<span data-id="${track.id}" data-name="${track.name}" class="audioSpan"></span>
							</sec:authorize>
						</td>
						

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
								<td><c:out value="${minutes}"/>:<!-- 
								 --><c:choose><c:when test="${(track.length%60)<10}"><c:out value="0${track.length%60}"/></c:when><c:otherwise><!--
								 		   --><c:out value="${track.length%60}"/></c:otherwise>
									</c:choose>
								</td>
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
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>