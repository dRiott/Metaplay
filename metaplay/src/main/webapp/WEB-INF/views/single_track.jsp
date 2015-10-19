<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Track</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		<div class="row">
			<h1 style="font-family: Times, serif;"><em>Track: ${track.name}</em></h1>

			<div class="form-group">
				<sec:authorize access="isAuthenticated()">				
					<audio controls><source src="/metaplay/audio/retrieve?id=${track.id}&filename=${track.name}" type="audio/mpeg" /></audio>
				</sec:authorize>
			</div>

			<div class="form-group">
				<label for="project-name">Number</label> <span>${track.trackNumber}</span>
			</div>

			<div class="form-group">
				<label for="project-name">Album</label>
				<c:choose>
					<c:when test="${track.album!=null}">
						<td><a href="<spring:url value="/browse/album/${track.album.id}"/>">${track.album.name}</a></td>
					</c:when>
					<c:otherwise>
						<td>Not yet assigned to an album.</td>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label for="project-name">Length</label> 
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

			<div class="form-group">
				<label for="project-name">Lyrics</label>
				<c:choose>
					<c:when test="${track.lyrics!=null}">
						<td>${track.lyrics}</td>
					</c:when>
					<c:otherwise>
						<td>No lyrics have been added.</td>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label for="project-name">BPM</label> 
				<c:choose>
					<c:when test="${track.bpm!=null}">
						<td>${track.bpm}</td>
					</c:when>
					<c:otherwise>
						<td>?</td>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label for="project-name">Playlists</label>
				<div>
					<c:if test="${track.playlists.size()==0 }">
						<c:out value="This track has no playlists." />
					</c:if>
					<ul>
						<c:forEach items="${track.playlists}" var="playlist">
							<li><a href="<spring:url value="/browse/playlist/${track.playlist.id}"/>">${track.playlist.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<a href="<spring:url value="/browse/tracks"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>