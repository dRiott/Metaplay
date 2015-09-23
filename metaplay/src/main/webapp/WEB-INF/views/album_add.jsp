<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
<%-- <script src="<spring:url value="/resources/js/validateAccount.js"/>"></script>
 --%>

</head>
<body>

	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1>Fill In Your Desired Credentials</h1>
		</div>

		<spring:url value="/ablum/review" var="thisFormURL" />                                 
		<form:form action="${thisFormURL}" method="post" modelAttribute="album">  
		<form:errors path="*" element="div" cssClass="errors"/>
                                                                                                 
			<div class="row">                                                                    
                                                                                                 
				<div class="form-group">                                                         
					<label for="album-Name">Album Name</label>                                
					<form:input type="text" path="name" id="albumName" cssErrorClass="has-error"/>          
				</div>       
				<div class="form-group">                                                         
					<label for="album-artist">Artist Name</label>                                
					<form:input type="text" path="artist" id="albumArtistname" cssErrorClass="has-error"/>          
				</div> 
				</div>
				<div class="form-group">
					<label for="album-releaseDate">Release Date</label>
					<form:input path="releaseDate" cssClass="form-control"
						id="album-releaseDate" />
				</div>
				<div class="form-group">
					<label for="album-numTracks">Number of Tracks</label>
					<form:input path="numTracks" cssClass="form-control"
						id="album-numTracks" />
				<div class="form-group">
					<label for="album-length">Length</label>
					<form:input path="length" cssClass="form-control"
						id="album-length" />
				</div>
				
				<div class="form-group">
				<label for="album-track">Track: </label>
					<form:input path="numTracks" cssClass="form-control" id="album-track1" />
				</div>

				<button type="submit" class="btn btn-default">Submit</button>

			
			</div>
		</form:form>
	</div>
</body>
</html>