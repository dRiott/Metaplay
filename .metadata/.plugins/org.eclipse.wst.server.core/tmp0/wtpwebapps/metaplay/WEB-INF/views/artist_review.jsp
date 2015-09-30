<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artist Review</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/header.jsp"></jsp:include>

	<div class="container">
	
		<spring:url value="/artist/save" var="thisURL" />
		<form:form action="${thisURL}" method="post" modelAttribute="createArtistWrapper">
		
			<div class="row">
				<h2>Please Review the Artist for Accuracy</h2>
		<!-- START Artist fields here -->
				<div class="form-group">
					<label for="artist-name">Name</label> <span>${createArtistWrapper.name}</span>
				</div>
				<div class="form-group">
					<label for="biography">Biography</label> <span>${createArtistWrapper.biography }</span>
				</div>
				
				<!--	<div class="form-group">
					<label for="artist-image">Artist Image</label><span>${artist.artistImage }</span>
				</div> -->
				
		<!-- START Genre fields here -->	
				<div class="form-group">
					<!-- Genre is selected from Dropdown -->
					<c:if test="${!createArtistWrapper.genreName.contains('** New Genre **') }">
						<div class="form-group">
							<label for="genreName">Genre</label> 
							<c:out value="${createArtistWrapper.genreName }"/>
						</div>
					</c:if>
					<!-- new Genre is created -->
					<c:if test="${createArtistWrapper.genreName.contains('** New Genre **') }">
						<div class="form-group">
							<label for="genreName"><em>* New Genre</em></label> 
							<c:out value="${createArtistWrapper.newGenreName }"/>
						</div>
						<div class="form-group">
							<label for="newGenreDescription"><em>New Genre Description:</em></label>
							<c:out value="${createArtistWrapper.newGenreDescription }"/>
						</div>
					</c:if>
				</div>
				
				
		<!-- START Location fields here -->	
				<div class="form-group">
					<label for="location-city">City</label> <span>${createArtistWrapper.locationCity}</span>
				</div>
				<div class="form-group">
					<label for="location-state">State</label> <span>${createArtistWrapper.locationState}</span>
				</div>
				
		<!--  START Member fields here -->
				<c:if test="${createArtistWrapper.member1!=''}">
					<div class="form-group">
						<label for="member1">Artist Member #1</label> <span>${createArtistWrapper.member1}</span>
					</div>
				</c:if>
				<c:if test="${createArtistWrapper.member2!=''}">
					<div class="form-group">
						<label for="member2">Artist Member #2</label> <span>${createArtistWrapper.member2}</span>
					</div>
				</c:if>
				<c:if test="${createArtistWrapper.member3!=''}">
					<div class="form-group">
						<label for="member3">Artist Member #3</label> <span>${createArtistWrapper.member3}</span>
					</div>
				</c:if>
				<c:if test="${createArtistWrapper.member4!=''}">
					<div class="form-group">
						<label for="member4">Artist Member #4</label> <span>${createArtistWrapper.member4}</span>
					</div>	
				</c:if>
				<c:if test="${createArtistWrapper.member5!=''}">					
					<div class="form-group">
						<label for="member5">Artist Member #5</label> <span>${createArtistWrapper.member5}</span>
					</div>
				</c:if>
				<c:if test="${createArtistWrapper.member6!=''}">					
					<div class="form-group">
						<label for="member6">Artist Member #6</label> <span>${createArtistWrapper.member6}</span>
					</div>						
				</c:if>
				
		<!--  START Album fields here -->
				<div class="form-group">
					<c:if test="${!createArtistWrapper.albumNameFromList.contains('** New Album **')}">
						<div class="form-group">
							<label for="trackArtistName">Album: Name</label>
							<c:out value="${createArtistWrapper.albumNameFromList}"/>
						</div>
					</c:if>
					<c:if test="${createArtistWrapper.albumNameFromList.contains('** New Album **') }">
						<div class="form-group">
							<label for="trackArtistName"><em>New Album: Name</em></label>
							<c:out value="${createArtistWrapper.theNewAlbumName}"/>
						</div>
						<div class="form-group">
							<label for="album-numTracks"><em>New Album: Number of Tracks</em></label><span>${createArtistWrapper.albumNumTracks }</span>
						</div>
						<div class="form-group">
							<label for="album-releaseDate"><em>New Album: Release Date</em></label><span>${createArtistWrapper.albumReleaseDate }</span>
						</div>
						<div class="form-group">
							<label for="album-albumCover"><em>New Album: Album Cover</em></label><span>${createArtistWrapper.albumAlbumCover }</span>
						</div>
					</c:if>
				</div>
				
				
				
		<!--  START Member ForEach -->
				<!-- <div class="form-group">
					<label>Members</label>
					<c:forEach var="members" items="${artist.members}">
						<span>${members}</span>
					</c:forEach>
				</div> -->
					
				<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Edit</a>
				<a href="<spring:url value="/artist/save"/>" class="btn btn-default">Save</a>
				
			</div>
		</form:form>
	</div>
</body>
</html>