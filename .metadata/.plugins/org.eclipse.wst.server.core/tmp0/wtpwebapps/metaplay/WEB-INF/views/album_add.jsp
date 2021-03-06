<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Album Manager</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/lib/bootstrap-select.min.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<script src="<spring:url value="/resources/lib/bootstrap-select.min.js"/>"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>

<body>

	<jsp:include page="../views/fragments/headerSecurity.jsp"></jsp:include>

	<div class="container">

		<h1 class="dH1">Add an Album<span style="padding-left: 10px"></span><small><small>The * indicates a required field.</small></small></h1>

		<sec:authorize access="!hasAuthority('God')">
			<h3 class="dH1">Looks like you don't have priviledges to submit new info. <a href="<spring:url value="/account/requestRole"/>">Make a request!</a></h3>
		</sec:authorize>

		<spring:url value="/album/save" var="thisFormURL" />                                 
		<form:form action="${thisFormURL}" method="post" enctype="multipart/form-data" modelAttribute="createAlbumWrapper" onsubmit="ensureVoidsAreZeroes();">  
		<form:errors path="*" element="div" cssClass="errors"/>
        	<div class="form-group">
				<div class="row">                                                                    
					<div class="col-md-4">                                                         
						<label for="albumName">* New Album</label>                                
						<form:input type="text" path="name" id="albumName" cssClass="form-control" cssErrorClass="has-error" placeholder="Thriller"/>          
					</div>  
					
					<div class="col-md-4">    
						<label>...Or</label>                                                     
						<form:select path="albumFromList" cssClass="selectpicker" id="albumListName" >
							<form:options items="${albumOptions}" />
						</form:select>					
					</div>       
				</div>		
				
				<div class="form-group" style="float:clear;"></div>
					
			<!-- BEGIN Artist -->
				<div class="row">
					<div class="col-md-2">
						<label for="albumArtistName">Artist</label>
						<div class="form-group">
							<form:select path="artistFromList" cssClass="selectpicker" id="albumArtistName" >
								<form:options items="${artistOptions}" />
							</form:select>
						</div>
					</div>
					
					<div class="col-md-4" id="newAlbumArtistName" style="display:none;">
						<label for="newAlbumArtistName" style="font-style:italic;">New Artist:</label>
						<form:input cssClass="form-control" type="text" path="theNewArtist" id="newAlbumArtistNameInput" cssErrorClass="has-error" placeholder="The Beach Boys"/>
					</div>
					
					<div class="col-md-2" id="newAlbumArtistAddButton" style="display:none;">
						<label for="newAlbumArtistButton" style="font-style:italic;">Or...</label>
						<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Go To Add Artist Page</a>
					</div>
				</div>
			<!-- END Artist -->
				
			<!-- START RECORDLABEL -->
				<div class="row">
					<hr id="recordLabelHr1" style="display:none;">
					<div class="col-md-3">
						<label for="recordLabelName">Record Label</label>
						<div class="form-group">
							<form:select path="recordLabelFromList" cssClass="selectpicker" id="recordLabelName" >
								<form:options items="${recordLabelOptions}" />
							</form:select>
						</div>
					</div>
					
					<div class="col-md-4" id="newRecordLabelName" style="display:none;">
						<label for="newRecordLabelName" style="font-style:italic;">New Record Label:</label>
						<form:input cssClass="form-control" type="text" path="theNewRecordLabel" 
							id="newRecordLabelNameInput" cssErrorClass="has-error" placeholder="Young Turks"/>
					</div>
				</div>
						
				<div class="form-group" style="float:clear;"></div>
						
				<div class="row" id="newRecordLabelLocation" style="display:none;">
					<div class="col-md-4">
						<label for="recordLabelCity"><em>Record Label: City</em></label>
						<form:input path="recordLabelCity" cssClass="form-control" id="recordLabelCity" placeholder="Birmingham"/>
					</div>
					<div class="col-md-2" id="rlState">
						<label for="recordLabelState"><em>Record Label: State</em></label>
						<div class="form-group">
						<form:select path="recordLabelState" cssClass="selectpicker" items="${recordLabelStateOptions}"
							id="recordLabelState" />
						</div>
					</div>
					
					<div class="col-md-2">
						<label for="recordLabelCountry">Country</label>
						<div class="form-group">
						<form:select path="recordLabelCountry" cssClass="selectpicker" items="${recordLabelCountryOptions}" id="rlCountries" />
						</div>
					</div>
					
					<div class="col-md-4" id="rlCountry" style="display:none;">
						<label for="newCountry"><em>* New Country:</em></label>
						<form:input cssClass="form-control" path="recordLabelNewCountry" id="newCountryInput" cssErrorClass="has-error" />
					</div>
				</div>
				<hr id="recordLabelHr2" style="display:none;">
		<!-- END RECORDLABEL -->
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">
					<div class="col-md-4">
						<label for="albumCover">Album Cover<span style="padding-left:10px;"><small>jpg, png, gif</small></span></label>
						<form:input type="file" accept="image/jpeg, image/png, image/gif, image/jpg" path="albumCover" id="albumCoverInput" cssClass="btn btn-default btn-file" />
					</div>
				
					<div class="col-md-3">
						<label for="albumReleaseDate">Release Date</label>
						<form:input path="releaseDate" cssClass="form-control" type="date" id="albumReleaseDate" />
					</div>
					
					<div class="col-md-1">
						<label for="albumLengthMinutes">*Minutes</label>
						<form:input path="lengthMinutes" id="albumMinutes" class="albumLength"  cssClass="form-control" value="" type="number" min="0"/>
					</div>
	
					<div class="col-md-1">
						<label for="albumLengthSeconds">*Seconds</label>
						<form:input path="lengthSeconds" id="albumSeconds" class="albumLength" cssClass="form-control" value="" type="number" min="0"/>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<!--  BEGINNING TRACKS HERE -->
				
				<div class="row">
					<div class="col-md-8">
						<label for="albumTrack">Tracks: </label>
					</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<c:forEach begin="0" end="29" var="val" varStatus="valStatus">
					<c:choose>
						<c:when test="${val+1<=3}">
							<div class="row" style="display:inline;" id="${val+1}">
								<div class="col-md-4 showNextTrack">
									<label for="trackNumber" >${val + 1}. </label>
									<form:input path="createTrackWrappers[${val}].name" cssClass="form-control" onclick="reveal(${val+1});"/>
								</div>
								<div class="col-md-1">
									<label for="trackLengthMinutes">Minutes</label>
									<form:input path="createTrackWrappers[${val}].lengthMinutes" type="number" min="0" id="minutes${val+1}" class="minutes" cssClass="form-control"/>
								</div>	
								<div class="col-md-1">
									<label for="trackLengthSeconds">Seconds</label>
									<form:input path="createTrackWrappers[${val}].lengthSeconds" type="number" min="0" id="seconds${val+1}" class="seconds" cssClass="form-control"/>
								</div>
								<div class="col-md-1">
									<label for="trackBpm">BPM</label>
									<form:input path="createTrackWrappers[${val}].bpm" type="number" min="0" id="bpm${val+1}" class="bpm" cssClass="form-control"/>
								</div>
								<div class="col-md-3">
									<label for="trackBpm">MP3</label>
									<form:input type="file" path="createTrackWrappers[${val}].mp3" id="mp3${val+1}" cssClass="form-control"/>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="row" style="display:none;" id="${val+1}">
								<div class="col-md-4 showNextTrack">
									<label for="trackNumber" >${val + 1}. </label>
									<form:input path="createTrackWrappers[${val}].name" cssClass="form-control" onclick="reveal(${val+1});"/>
								</div>
								<div class="col-md-1">
									<label for="trackLengthMinutes">Minutes</label>
									<form:input path="createTrackWrappers[${val}].lengthMinutes" class="minutes" type="number" min="0" id="minutes${val+1}" cssClass="form-control"/>
								</div>	
								<div class="col-md-1">
									<label for="trackLengthSeconds">Seconds</label>
									<form:input path="createTrackWrappers[${val}].lengthSeconds" class="seconds" type="number" min="0" id="seconds${val+1}" cssClass="form-control"/>
								</div>
								<div class="col-md-1">
									<label for="trackBpm">BPM</label>
									<form:input path="createTrackWrappers[${val}].bpm" id="bpm${val+1}" class="bpm" type="number" min="0" cssClass="form-control"/>
								</div>
								<div class="col-md-3">
									<label for="trackBpm">MP3</label>
									<form:input type="file" path="createTrackWrappers[${val}].mp3" id="mp3${val+1}" cssClass="form-control"/>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			<!--  END TRACKS -->
				</div>	
			
				<div class="row" id="tracksContainer">
				<!-- I'm going to insert the above divs dynamically -->
				 </div>
			
			
				<div class="form-group" style="float:clear;"></div>
				
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>

	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>	
	
	<script src="<spring:url value="/resources/js/albumAddShowHide.js"/>"></script>
	
</body>
</html>