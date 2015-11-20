<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Playlist</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">
			<h1 class="dH1">Playlist: ${playlist.name}</h1>

			<div class="form-group">
				<label>Description</label>
				<div class="row">
					<div class="col-md-8">
						<c:out value="${playlist.description}"/>
					</div>			
				</div>
			</div>
			
			<div class="form-group">
				<label>Accounts</label>
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
				<table class="table table-hover browseTable" id="playlistTable">
					<thead>
						<tr>
							<th>No.</th>
							<th id="playlistTHeadTh0">Name</th>
							<th id="playlistTHeadTh1">Artist</th>
							<th id="playlistTHeadTh2">Album</th>
							<th id="playlistTHeadTh3">Length</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${playlist.tracks}" var="track" varStatus="count">
							<tr trackId="${track.id}">
								<td>${count.index+1}</td>
								<td class="tdWidth">
									<a href="<spring:url value="/browse/track/${track.id}"/>">${track.name}</a>
								</td>
								<td class="tdWidth">
									<a href="<spring:url value="/browse/artist/${track.album.artist.id}"/>">${track.album.artist.name}</a>
								</td>
								<td class="tdWidth">
									<a href="<spring:url value="/browse/album/${track.album.id}"/>">${track.album.name}</a>
								</td>

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
								</c:choose>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="form-group">
				<br/><hr/>
				<a href="<spring:url value="/browse/playlists"/>" class="btn btn-default">Back To Browse</a>
			</div>

		</div>
	</div>
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>