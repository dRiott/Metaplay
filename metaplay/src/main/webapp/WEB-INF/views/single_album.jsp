<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Album</title>

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		<div class="row">
			<h1 style="font-family: Times, serif;"><em>Album: ${album.name}</em></h1>

			<div class="form-group">
				<label for="project-name">Artist</label>
				<c:choose>
					<c:when test="${album.artist!=null}">
						<span><a href="<spring:url value="/browse/artist/${album.artist.id}"/>">${album.artist.name}</a></span>
					</c:when>
					<c:otherwise>
						<td>No artist assigned yet.</td>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="project-name">Record Label</label>
				<c:choose>
					<c:when test="${album.recordLabel!=null}">
						<span><a href="<spring:url value="/browse/recordlabel/${album.recordLabel.id}"/>">${album.recordLabel.name}</a></span>
					</c:when>
					<c:otherwise>
						<td>No Record Label assigned yet.</td>
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="form-group" >
				<img src="/metaplay/image/retrieve?foldername=album&filename=${album.name}" style="max-width: 350px;height:auto;"
				alt="Image not found" onerror="this.onerror=null; this.src='http://localhost:8080/metaplay/resources/img/default.gif'"/>
			</div>
				
			<div class="form-group">
				<label for="project-name">Tracks</label>
				 <c:if test="${album.numTracks!=null}">
				 	<span>${album.numTracks}</span>
				 </c:if>
				  <c:if test="${album.numTracks==null}">
				 	<span>?</span>
				 </c:if>
			</div>
			<div class="form-group">
				<label for="project-name">Length</label>
				<c:choose>
					<c:when test="${album.length==0}">
						<span>?</span>
					</c:when>
					<c:when test="${album.length!=null}">
						<%-- Formatting the minutes from track.length --%>
						<fmt:formatNumber var="minutes" pattern="##" value="${album.length div 60}"/>
						<c:if test="${minues>60}">
							<fmt:formatNumber var="hours" pattern="##" value="${minutes div 60}"/>
							<td><c:out value="${hours}hr, ${minutes-60}"/></td>
						</c:if>
						<c:if test="${minutes<60}">
							<td><c:out value="${minutes}"/>:<c:out value="${album.length%60}"/></td>
						</c:if>
					</c:when>
					<c:otherwise>
						<span>?</span>				
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="project-name">Release Date</label>
				<c:choose>
					<c:when test="${album.releaseDate!=null}">
						<span><fmt:formatDate type="date" dateStyle="long" value="${album.releaseDate}"/></span>
					</c:when>
					<c:otherwise>
						<span>No release date has been added.</span>				
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="project-name">Description</label> 
				<c:choose>
					<c:when test="${album.description!=null}">
						<span>${album.description}</span>					
					</c:when>
					<c:otherwise>
						<span>No description.</span>				
					</c:otherwise>
				</c:choose>
			</div>

		<a href="<spring:url value="/browse/albums"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>