<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Track Manager</title>

    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script src="<spring:url value="/resources/js/findTrack.js"/>"></script>
</head>

<body class="DVBody delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer form-group">
		<div class="row drRow">
			<h1 class="dH1">Upload An MP3</h1>
		
			<sec:authorize access="!hasAuthority('God')">
				<h3 class="dH1">Looks like you don't have priviledges to submit new info. <a href="<spring:url value="/account/requestRole"/>">Make a request!</a></h3>
			</sec:authorize>
			
			<div class="drMessages" id="messages"></div>
		
			<div class="col-md-12">
			<spring:url value="/audio/upload" var="thisURL" />
				<form:form action="${thisURL}" method="POST" enctype="multipart/form-data" 
					modelAttribute="uploadTrackWrapper" onsubmit="return checkUploadContents();">
		
					<div class="row">
						<div class="row">
							<div class="col-md-1">
								<label for="trackId"><small>ID</small></label>
								<form:input path="id" cssClass="form-control" id="trackId" />
							</div>
							<div class="col-md-3">
								<label for="trackName"><small>Name</small></label>
								<form:input path="filename" cssClass="form-control" id="trackName" />
							</div>
						
							<!-- accept="image/jpeg, image/png, image/gif, image/jpg"  -->
							<div class="col-md-3">
								<label for="audioFileInput">Audio File</label><span class="dSpan"></span><small>mp3, wav, m4p, flac</small>
								<form:input type="file" path="mp3" id="audioFileInput" cssClass="btn btn-default btn-file" />
							</div>
					
							<div class="col-md-2" style="padding-left: 70px">
								<span style="display:block; height: 25px;"></span>
								<button type="submit" id="submitButton" class="btn btn-default">Submit</button>
							</div>	
						</div>	
					</div>
				</form:form>
			</div>
		</div>
		<br/>
		<div class="row drRow">
			<p>ID corresponds to the ID of the Track you want the MP3 to be associated with. <button class="btn btn-default" id="findTrack">Find An ID</button></p>
		
	 		<div class="row" id="searchTrackDiv" style="display:none">
	 			<div class="col-md-4">
		 	 		<input id="searchTracks" type="text" class="form-control" placeholder="Enter a track name..."/>
				</div>
			</div>
			
			<div class="form-group" style="clear:both;"></div>
				
			<div class="row">
				<div class="col-md-8">
 					<table class="table table-hover" id="allResultsTable"><tr></tr></table>
 				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>