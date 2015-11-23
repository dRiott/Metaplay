<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Track</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
 	<script src="<spring:url value="/resources/js/getAudio.js"/>"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>
	<div class="drContainer">
		<div class="row drRow">
			<h1 class="dH1">Track: ${track.name}</h1>
			
			<div class="form-group">
				<sec:authorize access="!isAuthenticated()">
					<h3 class="drSecurity">Looks like you're not logged in! You won't be able to stream music.</h3>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">				
					<span data-id="${track.id}" data-name="${track.name}" class="audioSpan"></span>
				</sec:authorize>
			</div>

			<div class="form-group">
				<label>Track Number on Album</label>
				<div class="form-group">	
					${track.trackNumber}
				</div>
			</div>

			<div class="form-group">
				<label>Album</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${track.album!=null}">
							<td><a href="<spring:url value="/browse/album/${track.album.id}"/>">${track.album.name}</a></td>
						</c:when>
						<c:otherwise>
							<td>Not yet assigned to an album.</td>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="form-group">
				<label>Length</label>
				<div class="form-group">
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
				</div>
			</div>

			<div class="form-group">
				<label>Lyrics</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${track.lyrics!=null}">
							<td>${track.lyrics}</td>
						</c:when>
						<c:otherwise>
							<td>No lyrics have been added.</td>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="form-group">
				<label>BPM</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${track.bpm==0}">
							<td>?</td>
						</c:when>
						<c:when test="${track.bpm!=null}">
							<td>${track.bpm}</td>
						</c:when>
						<c:otherwise>
							<td>?</td>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="form-group">
				<label>Playlists</label>
				<div>
					<c:if test="${track.playlists.size()==0 }">
						<c:out value="This track has no playlists." />
					</c:if>
					<ul>
						<c:forEach items="${track.playlists}" var="playlist">
							<li><a href="<spring:url value="/browse/playlist/${playlist.id}"/>">${playlist.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="form-group">
				<br/><hr/>
				<a href="<spring:url value="/browse/tracks"/>" class="btn btn-default">Back To Browse</a>
			</div>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>