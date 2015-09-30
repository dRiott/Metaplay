<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Album Manager</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css" />
<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>

</head>
<body>

	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<h1>Create an Album&nbsp&nbsp<small><small>The * indicates a required field.</small></small></h1>
		</div>

		<spring:url value="/album/review" var="thisFormURL" />                                 
		<form:form action="${thisFormURL}" method="post" modelAttribute="createAlbumWrapper" onsubmit="return validate(this);">  
		<form:errors path="*" element="div" cssClass="errors"/>
        	<div class="form-group">
				<div class="row">                                                                    
					<div class="col-md-4">                                                         
						<label for="albumName">* Name</label>                                
						<form:input type="text" path="name" id="albumName" cssClass="form-control" cssErrorClass="has-error"/>          
					</div>       
				</div>		
				<div class="form-group" style="float:clear;"></div>
					
			<!-- BEGIN Artist -->
				<div class="row">
					<div class="col-md-1">
						<label for="albumArtistName">Artist</label>
						<form:select path="artistFromList" cssClass="selectpicker" id="albumArtistName" >
							<form:options items="${artistOptions}" />
						</form:select>
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				<div class="form-group" id="newAlbumArtistName" style="display:none;">
					<hr/>
					<label for="newAlbumArtistName" style="font-style:italic;">New Artist:</label>
					<form:input cssClass="form-control" type="text" path="theNewArtist" id="newAlbumArtistNameInput" cssErrorClass="has-error" />
				<div class="form-group" style="float:clear;"></div>
					<label for="newAlbumArtistButton" style="font-style:italic;">Or...</label>
					<a href="<spring:url value="/artist/add"/>" class="btn btn-default">Go To Add Artist Page</a>
					<hr/>
				</div>
			<!-- END Artist -->
				
			<!-- START RECORDLABEL -->
				<div class="form-group">
					<label for="recordLabelName">Record Label</label>
					<form:select path="recordLabelFromList" cssClass="selectpicker" id="recordLabelName" >
						<form:options items="${recordLabelOptions}" />
					</form:select>
					<div class="form-group" id="newRecordLabelName" style="display:none;">
						<hr/>
						<div class=form-group>
						<label for="newRecordLabelName" style="font-style:italic;">New Record Label:</label>
						<form:input cssClass="form-control" type="text" path="theNewRecordLabel" id="newRecordLabelNameInput" cssErrorClass="has-error" />
						</div>
						
						<div class="form-group" style="float:clear;"></div>
						<div class="row">
							<div class="col-md-4">
							<label for="recordLabelCity"><em>Record Label: City</em></label>
							<form:input path="recordLabelCity" cssClass="form-control" id="recordLabelCity" />
							</div>
							<div class="col-md-4">
								<label for="recordLabelState"><em>Record Label: State</em></label>
								<div class="form-group">
								<form:select path="recordLabelState" cssClass="selectpicker" items="${stateOptions}"
									id="recordLabelState" />
								</div>
							</div>
						</div>
						<hr/>
						<div class="form-group" style="float:clear;"></div>
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
			<!-- END RECORDLABEL -->
	
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">
					<div class="col-md-4">
						<label for="albumReleaseDate">Release Date&nbsp&nbsp<small>(e.g. 04-31-1990)</small></label>
						<form:input path="releaseDate" cssClass="form-control"
							id="albumReleaseDate" />
					</div>
					
					<div class="col-md-1">
						<label for="albumLengthMinutes">*Minutes</label>
						<form:input path="lengthMinutes" id="albumLengthSeconds"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="albumLengthSeconds">*Seconds</label>
						<form:input path="lengthSeconds" id="albumLengthSeconds" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<!--  BEGINNING TRACKS HERE -->
				
				<div class="row">			
					<div class="col-md-8">
						<label for="albumTrack">Tracks: </label>
					</div>
				</div>	
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >1. </label>
						<form:input path="tracks[0].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[0].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[0].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[0].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >2. </label>
						<form:input path="tracks[1].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[1].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[1].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[1].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >3. </label>
						<form:input path="tracks[2].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[2].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[2].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[2].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >4. </label>
						<form:input path="tracks[3].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[3].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[3].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[3].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >5. </label>
						<form:input path="tracks[4].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[4].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[4].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[4].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >6. </label>
						<form:input path="tracks[5].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[5].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[5].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[5].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >7. </label>
						<form:input path="tracks[6].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[6].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[6].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[6].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >8. </label>
						<form:input path="tracks[7].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[7].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[7].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[7].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >9. </label>
						<form:input path="tracks[8].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[8].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[8].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[8].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >10. </label>
						<form:input path="tracks[9].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[9].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[9].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[9].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >11. </label>
						<form:input path="tracks[10].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[10].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[10].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[10].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:inline;">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >12. </label>
						<form:input path="tracks[11].name" cssClass="form-control" id="input12"/>
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[11].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[11].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[11].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:none;" id="13">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >13. </label>
						<form:input path="tracks[12].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[12].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[12].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[12].bpm" cssClass="form-control"/>
					</div>
				</div>
		
				<div class="row" style="display:none;" id="14">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >14. </label>
						<form:input path="tracks[13].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[13].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[13].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[13].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="15">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >15. </label>
						<form:input path="tracks[14].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[14].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[14].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[14].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="16">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >16. </label>
						<form:input path="tracks[15].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[15].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[15].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[15].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="17">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >17. </label>
						<form:input path="tracks[16].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[16].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[16].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[16].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="18">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >18. </label>
						<form:input path="tracks[17].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[17].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[17].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[17].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="19">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >19. </label>
						<form:input path="tracks[18].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[18].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[18].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[18].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="20">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >20. </label>
						<form:input path="tracks[19].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[19].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[19].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[19].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="21">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >21. </label>
						<form:input path="tracks[20].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[20].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[20].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[20].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="22">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >22. </label>
						<form:input path="tracks[21].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[21].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[21].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[21].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="23">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >23. </label>
						<form:input path="tracks[22].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[22].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[22].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[22].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="24">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >24. </label>
						<form:input path="tracks[23].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[23].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[23].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[23].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="25">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >25. </label>
						<form:input path="tracks[24].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[24].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[24].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[24].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="26">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >26. </label>
						<form:input path="tracks[25].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[25].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[25].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[25].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="27">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >27. </label>
						<form:input path="tracks[26].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[26].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[26].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[26].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="28">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >28. </label>
						<form:input path="tracks[27].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[27].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[27].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[27].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="29">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >29. </label>
						<form:input path="tracks[28].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[28].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[28].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[28].bpm" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="row" style="display:none;" id="30">
					<div class="col-md-4 showNextTrack">
						<label for="trackNumber" >30. </label>
						<form:input path="tracks[29].name" cssClass="form-control" />
					</div>
					<div class="col-md-1">
						<label for="trackLengthMinutes">Minutes</label>
						<form:input path="tracks[29].minutes" id="trackLengthMinutes"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="trackLengthSeconds">Seconds</label>
						<form:input path="tracks[29].seconds" cssClass="form-control"/>
					</div>
					<div class="col-md-1">
						<label for="trackBpm">BPM</label>
						<form:input path="tracks[29].bpm" cssClass="form-control"/>
					</div>
				</div>
				<div class="form-group" style="float:clear;"></div>
				
			<!--  END TRACKS -->
			
				<div class="form-group" style="float:clear;"></div>
				
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>
	<script src="<spring:url value="/resources/js/albumAddShowHide.js"/>"></script>
</body>
</html>