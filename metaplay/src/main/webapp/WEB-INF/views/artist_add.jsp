<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Artist Manager</title>
  
    <link id="favicon" rel="shortcut icon" href="<spring:url value='/resources/img/favicon.ico'/>" type="image/x-icon" />
   	<link rel="icon" type="image/x-icon" href="<spring:url value='/resources/img/favicon.ico'/>"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet"	href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script	src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
	<script src="<spring:url value="/resources/js/artistAdd.js"/>"></script>
</head>

<body class="delayedReveal">
	<jsp:include page="../views/fragments/headerSecurity.jsp"/>

	<div class="drContainer">

		<h1 class="dH1">Add an Artist<span style="padding-left: 10px"></span><small><small>The * indicates a required field.</small></small></h1>
	
		<security:authorize access="!hasAuthority('God')">
			<h3 class="dH1">Looks like you don't have priviledges to submit new info. <a href="<spring:url value="/account/requestRole"/>">Make a request!</a></h3>
		</security:authorize>

		<spring:url value="/artist/save" var="thisURL" />
		<form:form action="${thisURL}" method="POST" enctype="multipart/form-data" modelAttribute="createArtistWrapper">

			<div class="row" id="artistNameAndImage">
				<div class="col-md-3">
					<label for="artist-name">* Name</label>
					<form:input path="name" cssClass="form-control" id="artist-name" placeholder="Michael Jackson" />
				</div>
				
				<div class="col-md-4">
					<label for="artistImage">Artist Image<small><small><span style="padding-left: 8px">jpeg, jpg, png, or gif</span></small></small></label>
					<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="artistImage" id="artistImageInput" cssClass="btn btn-default btn-file" />
				</div>
			</div>
		
			<div class="form-group" style="clear:both;"></div>
			
			<div class="row" id="artistLocation">
				<div class="col-md-3">
					<label for="location-city">City</label>
					<form:input path="locationCity" cssClass="form-control"	id="location-city" placeholder="Birmingham"/>
				</div>
				<div class="col-md-2" id="location-state-div" style="display:inline;">
					<label for="location-state">State</label>
					<div class="form-group">
					<form:select path="locationState" cssClass="selectpicker" items="${stateOptions}"
						id="location-state" />
					</div>
				</div>
				<div class="col-md-3">
					<label for="countries">Country</label>
					<div class="form-group">
					<form:select path="locationCountry" cssClass="selectpicker" items="${countryOptions}" id="countries" />
					</div>
				</div>
				<div class="col-md-3" id="newCountryDiv" style="display:none;">
					<label for="newCountryInput"><em>* New Country:</em></label>
					<form:input cssClass="form-control" path="newLocationCountry" id="newCountryInput" cssErrorClass="has-error" />
				</div>
			</div>
			
			<div class="form-group" style="clear:both;"></div>
			
			<div class="row">
                <div class="col-md-9">
                    <label for="biography">Biography</label>
                    <form:textarea path="biography" cssClass="form-control" id="biography" rows="10" cols="30" />
                </div>
            </div>
			
			<div class="form-group" style="clear:both;"></div>
			
			<div class="row">
				<div class="col-md-3">
					<label for="genreName">Genre</label>
					<div>
						<form:select path="genreName" id="genreName" cssClass="selectpicker">
							<form:options items="${genreOptions}" id="genreOptions"/>
						</form:select>
					</div>
				</div>
				
				<div class="col-md-3" id="newGenreName" style="display:none;">
					<label for="newGenreName"><em>* New Genre:</em></label>
					<form:input cssClass="form-control" path="newGenreName" id="newGenreNameInput" cssErrorClass="has-error" />
				</div>
			</div>

			<div class="form-group" style="clear:both;"></div>
				
			<div class="row">
                <div class="col-md-9" id="newGenreDescription" style="display:none;">
				    <label for="newGenreDescription"><em>New Genre Description:</em></label>
				    <form:textarea rows="3" cssClass="form-control" path="newGenreDescription" id="newGenreDescriptionInput" cssErrorClass="has-error" />
				    <hr/>
                </div>
			</div>

			<div class="form-group" style="clear:both;"></div>

			<!--  Artist Member fields here -->
			<div class="row" id="${val}">
				<c:forEach begin="0" end="2" var="val" varStatus="valStatus">
						<div class="col-md-3">
							<label>Member #${val+1}</label>
							<form:input path="members[${val}].unparsedName" id="memberUnparsedName${val}" cssClass="form-control" 
							placeholder="Full Name"/>
							<label><input type="checkbox" class="stageNameCheckbox" id="stageNameCheck${val+1}"> Stage Name?</label>
							<div id="stageNameDiv${val+1}" class="stageNameDiv" style="display:none;">
								<form:input path="members[${val}].stageName" id="memberStageName${val}" cssClass="form-control" placeholder="Stage Name"/>
							</div>
						</div>	
				</c:forEach>
			</div>
			
			<div class="form-group" style="clear:both;"></div>
			
			<div class="row" id="${val}">
				<c:forEach begin="3" end="5" var="val" varStatus="valStatus">
						<div class="col-md-3">
							<label>Member #${val+1}</label>
							<form:input path="members[${val}].unparsedName" id="memberUnparsedName${val}" cssClass="form-control" placeholder="Full Name"/>
							<label><input type="checkbox" class="stageNameCheckbox" id="stageNameCheck${val+1}"> Stage Name?</label>
							<div id="stageNameDiv${val+1}" class="stageNameDiv" style="display:none;">
								<form:input path="members[${val}].stageName" id="memberStageName${val}" cssClass="form-control" placeholder="Stage Name"/>
							</div>
						</div>	
				</c:forEach>	
			</div>

			<div class="form-group" style="clear:both;"></div>

<!--  Album fields here -->
			<div class="row">
				<div class="col-md-3">
					<label for="albumAlbumName">Album</label>
					<div class="form-group">
                        <form:select path="albumNameFromList" cssClass="selectpicker" id="albumAlbumName" >
						    <form:options items="${albumOptions}" />
    					</form:select>
                    </div>
				</div>
				
				<div class="col-md-4" id="orAlbumAddLink" style="display:none">	
					<label style="font-style:italic;"><small>Or...</small></label>
					<div>
						<a href="<spring:url value="/album/add"/>" class="btn btn-default">Go To Add Album Page</a>
					</div>
				</div>
			</div>
			
			<div class="form-group" style="clear:both;"></div>

<!--  ALBUM FIELDS THAT POP OUT -->
			<div class="form-group" id="newAlbumAlbumName" style="display:none;">

                <hr/>
					
					<div class="row">
						<div class="col-md-3">
							<label for="newAlbumAlbumName" style="font-style:italic;"><small>New Album: Name</small></label>
							<form:input cssClass="form-control" path="theNewAlbumName" id="newAlbumAlbumNameInput" cssErrorClass="has-error" />
						</div>
						<div class="col-md-2">
							<label for="albumNumTracks" style="font-style:italic;"><small>Total Tracks</small></label>
							<form:input path="albumNumTracks" cssClass="form-control" id="album-numTracks" min="0" type="number"/>
						</div>
                    </div>

                <div class="form-group" style="clear:both;"></div>

                <div class="row">
						<div class="col-md-3">
							<label for="newAlbumReleaseDate" style="font-style:italic;"><small>Release Date</small></label>
							<form:input path="albumReleaseDate" cssClass="form-control" id="newAlbumReleaseDate" type="date"/>
						</div>
						<div class="col-md-3">
							<label for="albumCoverInput" style="font-style:italic;"><small>New Album: Cover</small>
							<small><small><span style="padding-left: 5px">jpg, png, gif</span></small></small></label>
							<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="albumCover" id="albumCoverInput" cssClass="btn btn-default btn-file" />
						</div>
					</div>
				
				<div class="form-group" style="clear:both;"></div>
				
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
                        </div>

                        <div class="form-group" style="clear:both;"></div>

                        <div class="row">
							<div class="col-md-3" id="newRecordLabelCity" style="display:none;">
                                <label for="recordLabelCity"><em><small>Record Label: City</small></em></label>
                                <form:input path="recordLabelCity" cssClass="form-control" id="recordLabelCity" />
							</div>

							<div class="col-md-3" id="newRecordLabelState" style="display:none;">
								<label for="recordLabelState"><em><small>Record Label: State</small></em></label>
								<div class="form-group">
                                    <form:select id="recordLabelState" path="recordLabelState" cssClass="selectpicker" items="${recordLabelStateOptions}"/>
								</div>
							</div>
                        </div>

                    </div>
					
                    <hr/>
				
                </div><!--END NEW RECORD LABEL-->
			</div> <!-- END ALBUM POP OUT FIELDS -->

			<div class="form-group" style="clear:both;"></div>

			<button type="submit" class="btn btn-default">Submit</button>
		</form:form>
	</div>
	
	<jsp:include page="../views/fragments/footer.jsp"/>
</body>
</html>