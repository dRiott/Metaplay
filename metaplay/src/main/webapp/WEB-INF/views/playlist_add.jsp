<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Playlist Manager</title>

    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/jquery-ui.min.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	<link rel="stylesheet"	href="<spring:url value="/resources/css/add_playlist.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
	<script	src="<spring:url value="/resources/js/playlistAdd.js"/>"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer drBlockLeft">
		<div class="row">
			<div class="form-group">
				<label for="playlistName">Name Your Playlist</label>
				<input class="form-control savedField" id="playlistName" placeholder="TrapHead Chillum"/>
			</div>
		</div>	
		
		<div class="row DR_Textarea">	
			<div class="form-group" id="description">
				<textarea class="form-control savedField" id="playlistDescription" rows="4" cols="20" placeholder="Description: This amazing banger will set your party on fire..."></textarea>
			</div>
		</div>
	</div>		
	
	<div class="drBlockRight">
		<div class="row">
			<div class="col-md-8">
				<label for="playlistName">Add Friends</label>
				<input class="form-control" id="accountSearchInput" placeholder="PoorYorick, Cup, JamesBrown..."/>
			</div>
			<div class="col-md-3">
				<div class="form-group" style="display:block; height:9px;"></div><!-- Spacer DIV to bump button down to appropriate height -->
				<button type="submit" class="btn btn-default" id="playlistSubmitButton">Save Playlist!</button>
			</div>
		</div>
		
		<div class="form-group" style="clear:both;"></div>
		
		<div class="form-control DR_Textarea savedField" id="accountsSelected"></div>
		
	</div>

	<div class="form-group" style="clear:both;"></div>
	
	<div class="DR_OuterDiv" id="outerDiv">
		<div class="DR_Table DR_InnerTable" id = "tracksDiv">
			<h4><label for="tracksTable">Pull Tracks From Here</label></h4>
			<table class="table table-hover" id="tracksTable">
				<thead>
					<tr>
						<th id="trackTHeadTh1">Name</th>
						<th id="trackTHeadTh2">Artist</th>
						<th id="trackTHeadTh3">Album</th>
						<th id="trackTHeadTh4">Length</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tracks}" var="track">
						<tr trackId="${track.id}">
							<td class="tdWidthTracks" id="trackNumber" style="display:none"></td>
							<td class="tdWidthTracks" id="trackName" >${track.name}</td>
							<td class="tdWidthTracks" id="trackArtist">${track.album.artist.name}</td>
							<td class="tdWidthTracks" id="trackAlbum">${track.album.name}</td>
							
							<%-- Formatting the minutes from track.length --%>
							<c:choose>
								<c:when test="${track.length!=null }">
									<fmt:formatNumber var="minutes" pattern="##" value="${track.length div 60}"/>
									<td class="tdWidthTracks"><c:out value="${minutes}"/>:<!-- 
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
		</div> <!-- end tracks div -->
		
		<div class="DR_Table DR_InnerTable" id = "playlistDiv">
			<h4><label for="playlistTable">To Here: <em>Your New Playlist</em></label></h4>
			<table class="table table-hover" id="playlistTable">
				<thead>
					<tr>
						<th id="playlistTHeadTh0">#</th>
						<th id="playlistTHeadTh1">Track</th>
						<th id="playlistTHeadTh2">Artist</th>
						<th id="playlistTHeadTh3">Album</th>
						<th id="playlistTHeadTh4">Length</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div> <!-- end playlist div -->
		
		
		<div id="messageDiv" style="display:none">
		<!-- for writting message on success/error AJAX call -->
		</div>
		
		<div style="clear:both;"></div>
	</div> <!-- END OUTER DIV -->
	
	<div class="navbar-fixed-bottom">
		<footer class="nav navbar-footer" style="padding-left:10px; padding-bottom: 3px;">&copy David Riott, 2015</footer>
	</div>	
</body>
</html>