<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Metaplay Playlist</title>

	<link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/playlistEdit.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
 	<script src="<spring:url value="/resources/js/getAudio.js"/>"></script>
 	<script src="<spring:url value="/resources/js/playlistEdit.js"/>"></script>
	
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">
		<div class="row drRow">
			
			<h1 data-playlistId ="${playlist.id}" id="playlistHeader" class="dH1">Playlist: ${playlist.name}</h1>
	
			<sec:authorize access="!isAuthenticated()">
				<h3 class="drSecurity">Looks like you're not logged in! You won't be able to stream music.</h3>
			</sec:authorize>
	
			<span id="drEditButtonDiv">
				<button id="drEditBtn" class="btn btn-default playlistButtton">Edit</button>
				<button id="drDeleteBtn" class="btn btn-default playlistButtton">Delete</button>
				<span id="drConfirmDeleteDiv">
					<button id="drConfirmDeleteBtn" class="btn btn-default playlistButtton">Confirm</button>
					<button id="drCancelBtn" class="btn btn-default playlistButtton">Cancel</button>
				</span>
			</span>		
			
			<div class="form-group" style="clear:both;"></div>					
			
			<!-- for writing a message on unsuccessful ajax call -->
			<div class="row">
				<div id="messageDiv" class="error col-md-7"></div>
			</div>

			<div class="form-group" style="clear:both;"></div>					
		
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
									<sec:authorize access="isAuthenticated()">				
										<c:set var="activeAccount" value="<%= request.getUserPrincipal().getName() %>"></c:set>
										<c:choose>
											<c:when test="${activeAccount.equals(account.name)}">
												<li><a class="playlistOwner" href="<spring:url value="/browse/account/${account.id}"/>">${account.name} (That's you!)</a></li>
											</c:when>
											<c:otherwise>
												<li><a class="playlistOwner" href="<spring:url value="/browse/account/${account.id}"/>">${account.name}</a></li>							
											</c:otherwise>
										</c:choose>
									</sec:authorize>

									<sec:authorize access="isAnonymous()">
										<li><a href="<spring:url value="/browse/account/${account.id}"/>">${account.name}</a></li>				
									</sec:authorize>
								</c:forEach>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="form-group" style="font-family: monospace, sans-serif; font-style: italic;">
				<c:if test="${playlist.tracks.size() == 0}">
					<p>Whoops! You may have arrived here early. Refresh the page! </p>
					<p>Otherwise, this playlist has no tracks.</p>
				</c:if>				
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
									<span class="dSpan"></span>		
									<sec:authorize access="isAuthenticated()">				
										<span data-id="${track.id}" data-name="${track.name}" class="audioSpan"></span>
									</sec:authorize>
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