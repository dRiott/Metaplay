<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Track Manager</title>

<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap3-3-4.css"/>" type="text/css" />
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<%-- <link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" /> --%>

<script src="<spring:url value="/resources/lib/jquery.js"/>"></script>
	<script src="<spring:url value="/resources/lib/jquery-ui-min.js"/>"></script>
<script src="<spring:url value="/resources/lib/bootstrap-min.js"/>"></script>
<%-- <script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script> --%>

</head>


<body class="DVBody">

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<h1>Upload An MP3</h1>
			<p>ID corresponds to the ID of the Track you want the MP3 to be associated with.</p>
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
						<label for="audioFile">Audio File</label><span style="padding-left: 10px;"></span><small>mp3, wav, m4p, flac</small>
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