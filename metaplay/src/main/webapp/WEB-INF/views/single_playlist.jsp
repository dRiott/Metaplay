<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Playlist</title>

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1 style="font-family: Times, serif;"><em>Playlist: ${playlist.name}</em></h1>

			<div class="form-group">
				<label for="project-name">Description</label> <span>${playlist.description}</span>
			</div>
			
			<div class="form-group">
				<label for="playlist-accounts">Accounts</label>
				<c:choose>
					<c:when test="${playlist.accounts.size()==0 }">
						<c:out value="This playlist has no accounts associated with it. Hmm..." /><span style="padding-left: 6px;"></span>
						<a href="<spring:url value="/account/add"/>" class="btn btn-default">Create A New Account</a>
						<a href="<spring:url value="/playlist/add"/>" class="btn btn-default">Go To Add Playlist Page</a>
					</c:when>
					<c:otherwise>
						<ul>
							<c:forEach items="${playlist.accounts}" var="account">
								<li><a href="<spring:url value="/browse/account/${account.id}"/>">${account.name}</a></li>
							</c:forEach>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="form-group">
				<label for="playlist-tracks">Tracks</label>
				<c:choose>
					<c:when test="${playlist.tracks.size()==0 }">
						<c:out value="This playlist has no trackss associated with it. Hmm..." /><span style="padding-left: 6px;"></span>
						<a href="<spring:url value="/track/add"/>" class="btn btn-default">Add Track</a>
					</c:when>
					<c:otherwise>
						<ol>
							<c:forEach items="${playlist.tracks}" var="track">
								<li><a href="<spring:url value="/browse/track/${track.id}"/>">${track.name}</a></li>
							</c:forEach>
						</ol>
					</c:otherwise>
				</c:choose>
			</div>

		<a href="<spring:url value="/browse/playlists"/>" class="btn btn-default">Back To Browse</a>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>