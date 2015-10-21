<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artist Manager</title>

<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet"	href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script	src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>


</head>


<body class="DVBody">

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container" style="padding-left: 7%" style="padding-left: 8%">

		<div class="row">
			<h1><a>Artist</a><span style="padding-left: 10px;"></span><small><small>The * indicates a required field.</small></small></h1>
		</div>

		<spring:url value="/artist/save" var="thisURL" />
		<form:form action="${thisURL}" method="POST" enctype="multipart/form-data"
			modelAttribute="createArtistWrapper">

			<div class="row" id="artistNameAndImage">
				<div class="col-md-6">
					<label for="artist-name">* Name</label>
					<form:input path="name" cssClass="form-control" id="artist-name" />
				</div>
				
				<div class="col-md-4">
					<label for="artistImage">Artist Image<small><small><span style="padding-left: 8px">jpeg, jpg, png, or gif</span></small></small></label>
					<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="artistImage" id="artistImageInput" cssClass="btn btn-default btn-file" />
				</div>
			</div>
		
			<div class="form-group" style="float:clear;"></div>
			
			<div class="row" id="artistLocation">
				<div class="col-md-4">
					<label for="location-city">City</label>
					<form:input path="locationCity" cssClass="form-control"	id="location-city" />
				</div>
				<div class="col-md-4">
					<label for="location-state">State</label>
					<div class="form-group">
					<form:select path="locationState" cssClass="selectpicker" items="${stateOptions}"
						id="location-state" />
					</div>
				</div>
			</div>
			
			<div class="form-group" style="float:clear;"></div>
			
			<div class="form-group">
				<label for="biography">Biography</label>
				<form:textarea path="biography" cssClass="form-control"
					id="biography" rows="10" cols="30" />
			</div>
			
			<div class="form-group" style="float:clear;"></div>
			
			<div class="row">
				<div class="col-md-2">
					<label for="genreName">Genre</label>
					<div>
						<form:select path="genreName" id="genreName" cssClass="selectpicker">
							<form:options items="${genreOptions}" id="genreOptions"/>
						</form:select>
					</div>
				</div>
				
				<div class="col-md-10" id="newGenreName" style="display:none;">
					<label for="newGenreName"><em>* New Genre:</em></label>
					<form:input cssClass="form-control" path="newGenreName" id="newGenreNameInput" cssErrorClass="has-error" />
				</div>
			</div>

			<div class="form-group" style="float:clear;"></div>
				
			<div class="form-group" id="newGenreDescription" style="display:none;">
				<label for="newGenreDescription"><em>New Genre Description:</em></label>
				<form:textarea rows="3" cssClass="form-control" path="newGenreDescription" id="newGenreDescriptionInput" cssErrorClass="has-error" />
				<hr/>
			</div>

			<div class="form-group" style="float:clear;"></div>

<%-- 			<div class="row" id="${val}">
				<c:forEach begin="0" end="2" var="val" varStatus="valStatus">
						<div class="col-md-4">
							<label for="memberUnparsedName" >Member #${val+1} Full Name</label>
							<form:input path="members[${val}].unparsedName" id="memberUnparsedName${val}" cssClass="form-control"/>
							<label for="memberStage">Member #${val+1} Stage Name</label>
							<form:input path="members[${val}].stageName" id="memberStageName${val}" cssClass="form-control"/>
						</div>	
				</c:forEach>
			</div>
			
			<div class="form-group" style="float:clear;"></div>
			
			<div class="row" id="${val}">
				<c:forEach begin="3" end="5" var="val" varStatus="valStatus">
						<div class="col-md-4">
							<label for="memberUnparsedName" >Member #${val+1} Full Name</label>
							<form:input path="members[${val}].unparsedName" id="memberUnparsedName${val}" cssClass="form-control"/>
							<label for="memberStage">Member #${val+1} Stage Name</label>
							<form:input path="members[${val}].stageName" id="memberStageName${val}" cssClass="form-control"/>
						</div>	
				</c:forEach>	
			</div>

			<div class="form-group" style="float:clear;"></div> --%>


			<!--  Artist Member fields here -->
			<div class="row">
				<div class="col-md-4">
					<label for="member1">Member #1 Full Name</label>
					<form:input path="member1" cssClass="form-control" id="member1" />
					<label for="member1">Member #1 Stage Name</label>
					<form:input path="member1StageName" cssClass="form-control" id="member1" />
				</div>
				<div class="col-md-4">
					<label for="member2">Member #2 Full Name</label>
					<form:input path="member2" cssClass="form-control" id="member2" />
					<label for="member2">Member #2 Stage Name</label>
					<form:input path="member2StageName" cssClass="form-control" id="member2" />
				</div>
				<div class="col-md-4">
					<label for="member3">Member #3 Full Name</label>
					<form:input path="member3" cssClass="form-control" id="member3" />
					<label for="member3">Member #3 Stage Name</label>
					<form:input path="member3StageName" cssClass="form-control" id="member3" />
				</div>
				<div class="form-group" style="float:clear;"></div>
			</div>
			<div class="form-group" style="float:clear;"></div>
			<div class="row">
				<div class="col-md-4">
					<label for="member4">Member #4 Full Name</label>
					<form:input path="member4" cssClass="form-control" id="member4" />
					<label for="member4">Member #4 Stage Name</label>
					<form:input path="member4StageName" cssClass="form-control" id="member4" />
				</div>
				<div class="col-md-4">
					<label for="member5">Member #5 Full Name</label>
					<form:input path="member5" cssClass="form-control" id="member5" />
					<label for="member5">Member #5 Stage Name</label>
					<form:input path="member5StageName" cssClass="form-control" id="member5" />
				</div>
				<div class="col-md-4">
					<label for="member6">Member #6 Full Name</label>
					<form:input path="member6" cssClass="form-control" id="member6" />
					<label for="member6">Member #6 Stage Name</label>
					<form:input path="member6StageName" cssClass="form-control" id="member6" />
				</div>
			</div>
			
			<div class="form-group" style="float:clear;"></div>
			
			<!--  Album fields here -->
			<div class="row">
				<div class="col-md-2">
					<label for="albumAlbumName">Album</label>
					<form:select path="albumNameFromList" cssClass="selectpicker" id="albumAlbumName" >
						<form:options items="${albumOptions}" />
					</form:select>
				</div>
				
				<div class="col-md-4" id="orAlbumAddLink" style="display:none">	
					<label for="newAlbumArtistButton" style="font-style:italic;"><small>Or...</small></label>
					<div>
						<a href="<spring:url value="/album/add"/>" class="btn btn-default">Go To Add Album Page</a>
					</div>
				</div>
			</div>
			
			<div class="form-group" style="float:clear;"></div>
			
			<div class="form-group" id="newAlbumAlbumName" style="display:none;"> <!--  ALBUM FIELDS THAT POP OUT -->
				
				<hr/>
					
					<div class="row">
						<div class="col-md-3">
							<label for="newAlbumAlbumName" style="font-style:italic;"><small>New Album: Name</small></label>
							<form:input cssClass="form-control" path="theNewAlbumName" id="newAlbumAlbumNameInput" cssErrorClass="has-error" />
						</div>
						<div class="col-md-2">
							<label for="albumNumTracks" style="font-style:italic;"><small>Total Tracks</small></label>
							<form:input path="albumNumTracks" cssClass="form-control" id="album-numTracks" />
						</div>
						<div class="col-md-3">
							<label for="newAlbumReleaseDate" style="font-style:italic;"><small>Release Date</small> <small><small>(e.g. 04-31-1990 or 4/22/1990)</small></small></label>
							<form:input path="albumReleaseDate" cssClass="form-control" id="newAlbumReleaseDate" />
						</div>
						<div class="col-md-3">
							<label for="newAlbumAlbumCover" style="font-style:italic;"><em>New Album: Cover</em>
							<small><small><span style="padding-left: 5px">jpeg, jpg, png, or gif</span></small></small></label>
							<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="albumCover" id="albumCoverInput" cssClass="btn btn-default btn-file" />
						</div>
					</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<!-- START RECORDLABEL -->
				<div class="form-group" id="newAlbumRecordLabel">
					
					<div class="form-group">
						
						<div class="row">
							
							<div class="col-md-3" id="artistAlbumRecordLabel">
								<label for="recordLabelName" style="font-style:italic;"><small>New Album: Record Label</small></label>
								<form:select path="recordLabelFromList" cssClass="selectpicker" id="recordLabelName" >
									<form:options items="${recordLabelOptions}" />
								</form:select>
							</div>	
							
							<div class="col-md-3" id="newRecordLabelName" style="display:none;">
								<label for="newRecordLabelName" style="font-style:italic;"><small>New Record Label:</small></label>
								<form:input cssClass="form-control" type="text" path="theNewRecordLabel" id="newRecordLabelNameInput" cssErrorClass="has-error" />
							</div>
							
							<div class="col-md-3" id="newRecordLabelCity" style="display:none;">
									<label for="recordLabelCity"><em><small>Record Label: City</small></em></label>
									<form:input path="recordLabelCity" cssClass="form-control" id="recordLabelCity" />
							</div>
							
							<div class="col-md-3" id="newRecordLabelState" style="display:none;">
								<label for="recordLabelState"><em><small>Record Label: State</small></em></label>
								<div class="form-group">
								<form:select path="recordLabelState" cssClass="selectpicker" items="${recordLabelStateOptions}"
									id="recordLabelState" />
								</div>
							</div>
							
						</div>
					
						<hr/>
				
					</div>
				</div><!-- END RECORDLABEL -->
				
				<div class="form-group" style="float:clear;"></div>
				
			</div> <!-- END ALBUM POP OUT FIELDS -->
			<div class="form-group" style="float:clear;"></div>
				<!--  End ARTIST fields -->

			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>
	
		<script src="<spring:url value="/resources/js/artistAdd.js"/>"></script>
<!-- 		<script>$(window).load(addEventHandlers());</script>
 -->	</body>
</html>