<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MetaPlay &copy Playlist</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>
<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1 class="dH1">Playlist: ${playlist.name}</h1>

			<div class="form-group">
				<label for="project-name">Description</label> 
				<div class="row">
					<div class="col-md-8">
						<c:out value="${playlist.description}"/>
					</div>			
				</div>
			</div>
			
			<div class="form-group">
				<label for="playlist-accounts">Accounts</label>
				<div class="form-group">
					<c:choose>
						<c:when test="${playlist.accounts.size()==0 }">
							<c:out value="This playlist has no accounts associated with it. Hmm..." /><span style="padding-left: 6px;"></span>
							<div class="form-group">
								<a href="<spring:url value="/account/add"/>" class="btn btn-default">Create A New Account</a>
								<a href="<spring:url value="/playlist/add"/>" class="btn btn-default">Go To Add Playlist Page</a>
							</div>
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
			</div>

			<div class="form-group">
				<%-- <label for="playlist-tracks">Tracks</label>
				<div class="form-group">
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
				</div> --%>
				<table class="table table-hover" id="playlistTable">
					<thead>
						<tr>
							<th id="playlistTHeadTh0">Name</th>
							<th id="playlistTHeadTh1">Artist</th>
							<th id="playlistTHeadTh2">Album</th>
							<th id="playlistTHeadTh3">Length</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tracks}" var="track">
							<tr trackId="${track.id}">
								<!--  <td class="hidenTrackId" style="display:none"></td>-->
								<td class="tdWidth">${track.name}</td>
								<td class="tdWidth" id="trackTdArtist">${track.album.artist.name}</td>
								<td class="tdWidth" id="trackTdName">${track.album.name}</td>
								
								<%-- Formatting the minutes from track.length --%>
								<c:choose>
									<c:when test="${track.length!=null }">
										<fmt:formatNumber var="minutes" pattern="##" value="${track.length div 60}"/>
										<td class="tdWidth"><c:out value="${minutes}"/>:<!-- 
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

		<div class="form-group">
			<hr/>
			<a href="<spring:url value="/browse/playlists"/>" class="btn btn-default">Back To Browse</a>
		</div>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
</body>
</html>