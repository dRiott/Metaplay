<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Manager</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />

	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

</head>

<body class="DVBody">

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1 class="dH1">Upload An MP3</h1>
			<p>ID corresponds to the ID of the Track you want the MP3 to be associated with.</p>
		
			<sec:authorize access="!hasAuthority('God')">
				<h3 class="dH1">Looks like you don't have priviledges to submit new info. <a href="<spring:url value="/account/requestRole"/>">Make a request!</a></h3>
			</sec:authorize>
		</div>

		<spring:url value="/audio/upload" var="thisURL" />
		<form:form action="${thisURL}" method="POST" enctype="multipart/form-data" modelAttribute="uploadTrackWrapper">

			<div class="row">
				<div class="row">
					<div class="col-md-1">
						<label for="trackNumber"><small>ID</small></label>
						<form:input path="id" cssClass="form-control" id="id" />
					</div>
					<div class="col-md-4">
						<label for="trackNumber"><small>Name</small></label>
						<form:input path="filename" cssClass="form-control" id="id" />
					</div>
				
					<!-- accept="image/jpeg, image/png, image/gif, image/jpg"  -->
					<div class="col-md-4">
						<label for="audioFile">Audio File</label><span class="dSpan"></span><small>mp3, wav, m4p, flac</small>
							<form:input type="file" path="mp3" id="audioFileInput" cssClass="btn btn-default btn-file" />
					</div>
					
				</div>	
				
				<div class="form-group" style="float:clear;"></div>
			
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
	<script src="<spring:url value="/resources/js/populateTrackAdd.js"/>"></script>
	<script src="<spring:url value="/resources/js/trackAdd.js"/>"></script>
	<script src="<spring:url value="/resources/js/validateTrack.js"/>"></script>
	
</body>
</html>