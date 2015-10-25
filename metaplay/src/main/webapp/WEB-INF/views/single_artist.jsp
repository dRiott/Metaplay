<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<!DOCTYPE html>
<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MetaPlay &copy Artist</title>
	
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>
	
	<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
	<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>
	
	<script src="<spring:url value="/resources/js/showHideBiography.js"/>"></script>

</head>

<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%">
		<div class="row">

			<h1 style="font-family: Times, serif;"><em>Artist: ${artist.name}</em></h1>
			
			<div class="form-group" style="float:clear;"></div>
			
			<div class="row">
				<div class="col-md-8">
					<label for="singleArtistBiography">Biography</label><br/>
					<c:set var="bioLength" value="${artist.biography.length()}"/>
					<span>${fn:substring(artist.biography, 0, 300)}</span><!-- 
					 --><span style="display:none;" id="hiddenBiography">${fn:substring(artist.biography, 300, bioLength)}</span>
					<button id="showButton" class="btn btn-default">More</button>
					<button id="hideButton" class="btn btn-default" style="display:none">Less</button>
				</div>
			</div>
		
			<div class="form-group" style="float:clear;"></div>
			
			<div class="form-group">
				<label for="singleArtistGenre">Genre</label>
				<c:choose>
					<c:when test="${artist.genre!=null}">
						<span><a href="<spring:url value="/browse/genre/${artist.genre.id}"/>">${artist.genre.name}</a></span>
					</c:when>
					<c:otherwise>
						<td>No genre assigned yet. They're probably Christian Death Metal, my best guess.</td>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="singleArtistLocation">Location</label>
				<c:choose>
					<c:when test="${artist.location!=null}">
						<span><a href="<spring:url value="/browse/location/${artist.location.id}"/>">${artist.location.city}, ${artist.location.state}</a></span>
					</c:when>
					<c:otherwise>
						<td>No location assigned yet.</td>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="singleArtistMembers">Members</label> 
				<div>
					<c:set var="membersSize" value="${artist.members.size()}"/>
					<c:if test="${membersSize==0 }">
						<c:out value="No members have been added." />
					</c:if>
					<c:forEach items="${artist.members}" var="member" varStatus="count">
						<c:choose>
							<c:when test="${member.lastName!=null}">
								<c:if test="${member.firstName!=null}">${member.firstName}</c:if>
								<c:if test="${member.middleName!=null}">${member.middleName}</c:if>
								<c:if test="${member.lastName!=null}">${member.lastName}</c:if>
								<c:if test="${member.stageName!=null}">- Stage Name: ${member.stageName}</c:if>
								<c:if test="${membersSize != count.index+1}"><br/></c:if>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</div>
			<div class="form-group">
				<label for="singleArtistAlbums">Albums</label>
				<c:choose>
					<c:when test="${artist.albums.size()==0 }">
						<c:out value="This artist has no albums. Hmm..." /><span style="padding-left: 6px;"></span>
						<a href="<spring:url value="/album/add"/>" class="btn btn-default">Go To Add Album Page</a>
					</c:when>
					<c:otherwise>
						<ul>
							<c:forEach items="${artist.albums}" var="album">
								<li>
									<div class="form-group" >
										<img src="/metaplay/image/retrieve?foldername=album&filename=${album.name}" style="max-width: 100px;height:auto;"
										alt="Image not found" onerror="this.onerror=null; this.src='http://localhost:8080/metaplay/resources/img/default.gif'"/>
									</div>
									<a href="<spring:url value="/browse/album/${album.id}"/>">${album.name}</a>
									<c:choose>
										<c:when test="${album.releaseDate!=null}">
											<span>(<fmt:formatDate type="date" dateStyle="long" value="${album.releaseDate}"/>)</span>
										</c:when>
										<c:otherwise>
											<span>Unknown Date, Probably Midnight, Thursday, 1 January 1970, not counting leap seconds.</span>				
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="form-group">
				<label for="singleArtistImage">Image</label>
				<div class="form-group" >
					<img src="/metaplay/image/retrieve?foldername=artist&filename=${artist.name}" style="max-width: 500px;height:auto;"
					alt="Image not found" onerror="this.onerror=null; this.src='http://localhost:8080/metaplay/resources/img/default.gif'"/>
				</div>
			</div>
		
		<a href="<spring:url value="/browse/artists"/>" class="btn btn-default">Back To Browse</a>
		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
<%-- 	<script src="<spring:url value="/resources/js/singleArtistBiographyShowHide.js"/>"></script>
 --%><!-- 	<script> $(window).load(showHideBiography()); </script>
 --></body>
</html>