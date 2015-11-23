<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Playlists</title>
  
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>
<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		
		<h1 class="dH1">Playlists</h1>
		<table class="table table-hover browseTable">
			<tbody>
				<tr>
					<th>Name</th><th>Description</th>
				</tr>
				<sec:authorize access="isAuthenticated()">				
					<c:forEach items="${playlists}" var="playlist" varStatus="count">
					<%-- Active User is notified of which playlist they share ownership --%>
						<c:set var="accountname" value="${pageContext.request.userPrincipal.name}"/>
						<c:set var="ownsPlaylist" value="false"/>
						<c:forEach items="${playlist.accounts}" var="account">
							<c:if test="${account.name.equals(accountname)}">
								<c:set var="ownsPlaylist" value="true"/>
							</c:if>
						</c:forEach>
						
						<c:choose>
							<c:when test="${ownsPlaylist}">
								<tr>
									<td id="playlistName+${count.index}"><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a> 
										<span style='padding-left: 15px;'><small>...You share this playlist</small></span>
									</td>
									<td>${playlist.description}</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td id="playlistName+${count.index}"><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a></td>
									<td>${playlist.description}</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<c:forEach items="${playlists}" var="playlist" varStatus="count">
						<tr>
							<td id="playlistName+${count.index}"><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a></td>
							<td>${playlist.description}</td>
						</tr>
					</c:forEach>
				</sec:authorize>
			</tbody>
		</table>
		
	</div>

	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>