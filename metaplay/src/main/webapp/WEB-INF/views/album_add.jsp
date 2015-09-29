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
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">
					<div class="col-md-1">
						<label for="albumReleaseDate">Release Date</label>
						<form:input path="releaseDate" cssClass="form-control"
							id="albumReleaseDate" />
					</div>
					
					<div class="col-md-1">
						<label for="albumLengthMinutes">* Minutes</label>
						<form:input path="lengthMinutes" id="albumLengthSeconds"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="albumLengthSeconds">* Seconds</label>
						<form:input path="lengthSeconds" id="albumLengthSeconds" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">			
				<label for="albumTrack">Tracks: </label>	
					<div class="col-md-4 showNextTrack">
						<span>1. </span>
						<form:input path="tracks[0].trackNumber" value="1" style="display:none"/>	
						<form:input path="tracks[0].name" cssClass="form-control" id="albumTrack1" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>2. </span>
						<form:input path="tracks[1].trackNumber" value="2" style="display:none"/>			
						<form:input path="tracks[1].name" cssClass="form-control" id="albumTrack2" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>3. </span>		
						<form:input path="tracks[2].trackNumber" value="3" style="display:none"/>	
						<form:input path="tracks[2].name" cssClass="form-control" id="albumTrack3" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>4. </span>		
						<form:input path="tracks[3].trackNumber" value="4" style="display:none"/>	
						<form:input path="tracks[3].name" cssClass="form-control" id="albumTrack4" />
					</div>
					<div class="col-md-4">
						<span>5. </span>			
						<form:input path="tracks[4].trackNumber" value="5" style="display:none"/>
						<form:input path="tracks[4].name" cssClass="form-control" id="albumTrack5" />
					</div>
					<div class="col-md-4">
						<span>6. </span>			
						<form:input path="tracks[5].trackNumber" value="6" style="display:none"/>
						<form:input path="tracks[5].name" cssClass="form-control" id="albumTrack6" />
					</div>
					<div class="col-md-4">
						<span>7. </span>			
						<form:input path="tracks[6].trackNumber" value="7" style="display:none"/>
						<form:input path="tracks[6].name" cssClass="form-control" id="albumTrack7" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>8. </span>			
						<form:input path="tracks[7].trackNumber" value="8" style="display:none"/>
						<form:input path="tracks[7].name" cssClass="form-control" id="albumTrack8" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>9. </span>			
						<form:input path="tracks[8].trackNumber" value="9" style="display:none"/>
						<form:input path="tracks[8].name" cssClass="form-control" id="albumTrack9" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>10. </span>			
						<form:input path="tracks[9].trackNumber" value="10" style="display:none"/>
						<form:input path="tracks[9].name" cssClass="form-control" id="albumTrack10" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>11. </span>			
						<form:input path="tracks[10].trackNumber" value="11" style="display:none"/>
						<form:input path="tracks[10].name" cssClass="form-control" id="albumTrack11" />
					</div>
					<div class="col-md-4" id="12">
						<span>12. </span>			
						<form:input path="tracks[11].trackNumber" value="12" style="display:none"/>
						<form:input path="tracks[11].name" cssClass="form-control" id="albumTrack12" />
					</div>

					<div class="col-md-4" style="display:none;" id="13">
						<span>13. </span>			
						<form:input path="tracks[12].trackNumber" value="13" style="display:none"/>
						<form:input path="tracks[12].name" cssClass="form-control" id="albumTrack13" />
					</div>
					<div class="col-md-4" style="display:none;" id="14">
						<span>14. </span>			
						<form:input path="tracks[13].trackNumber" value="14" style="display:none"/>
						<form:input path="tracks[13].name" cssClass="form-control" id="albumTrack14" />
					</div>
					<div class="col-md-4" style="display:none;" id="15">
						<span>15. </span>			
						<form:input path="tracks[14].trackNumber" value="15" style="display:none"/>
						<form:input path="tracks[14].name" cssClass="form-control" id="albumTrack15" />
					</div>
					<div class="col-md-4" style="display:none;" id="16">
						<span>16. </span>			
						<form:input path="tracks[15].trackNumber" value="16" style="display:none"/>
						<form:input path="tracks[15].name" cssClass="form-control" id="albumTrack16" />
					</div>
					<div class="col-md-4" style="display:none;" id="17">
						<span>17. </span>			
						<form:input path="tracks[16].trackNumber" value="17" style="display:none"/>
						<form:input path="tracks[16].name" cssClass="form-control" id="albumTrack17" />
					</div>
					<div class="col-md-4" style="display:none;" id="18">
						<span>18. </span>			
						<form:input path="tracks[17].trackNumber" value="18" style="display:none"/>
						<form:input path="tracks[17].name" cssClass="form-control" id="albumTrack18" />
					</div>
					<div class="col-md-4" style="display:none;" id="19">
						<span>19. </span>			
						<form:input path="tracks[18].trackNumber" value="19" style="display:none"/>
						<form:input path="tracks[18].name" cssClass="form-control" id="albumTrack19" />
					</div>
					<div class="col-md-4" style="display:none;" id="20">
						<span>20. </span>			
						<form:input path="tracks[19].trackNumber" value="20" style="display:none"/>
						<form:input path="tracks[19].name" cssClass="form-control" id="albumTrack20" />
					</div>
					<div class="col-md-4" style="display:none;" id="21">
						<span>21. </span>			
						<form:input path="tracks[20].trackNumber" value="21" style="display:none"/>
						<form:input path="tracks[20].name" cssClass="form-control" id="albumTrack21" />
					</div>
					<div class="col-md-4" style="display:none;" id="22">
						<span>22. </span>			
						<form:input path="tracks[21].trackNumber" value="22" style="display:none"/>
						<form:input path="tracks[21].name" cssClass="form-control" id="albumTrack22" />
					</div>
					<div class="col-md-4" style="display:none;" id="23">
						<span>23. </span>			
						<form:input path="tracks[22].trackNumber" value="23" style="display:none"/>
						<form:input path="tracks[22].name" cssClass="form-control" id="albumTrack23" />
					</div>
					<div class="col-md-4" style="display:none;" id="24">
						<span>24. </span>			
						<form:input path="tracks[23].trackNumber" value="24" style="display:none"/>
						<form:input path="tracks[23].name" cssClass="form-control" id="albumTrack24" />
					</div>
					<div class="col-md-4" style="display:none;" id="25">
						<span>25. </span>			
						<form:input path="tracks[24].trackNumber" value="25" style="display:none"/>
						<form:input path="tracks[24].name" cssClass="form-control" id="albumTrack25" />
					</div>
					<div class="col-md-4" style="display:none;" id="26">
						<span>26. </span>			
						<form:input path="tracks[25].trackNumber" value="26" style="display:none"/>
						<form:input path="tracks[25].name" cssClass="form-control" id="albumTrack26" />
					</div>
					<div class="col-md-4" style="display:none;" id="27">
						<span>27. </span>			
						<form:input path="tracks[26].trackNumber" value="27" style="display:none"/>
						<form:input path="tracks[26].name" cssClass="form-control" id="albumTrack27" />
					</div>
					<div class="col-md-4" style="display:none;" id="28">
						<span>28. </span>			
						<form:input path="tracks[27].trackNumber" value="28" style="display:none"/>
						<form:input path="tracks[27].name" cssClass="form-control" id="albumTrack28" />
					</div>
					<div class="col-md-4" style="display:none;" id="29">
						<span>29. </span>			
						<form:input path="tracks[28].trackNumber" value="29" style="display:none"/>
						<form:input path="tracks[28].name" cssClass="form-control" id="albumTrack29" />
					</div>
					<div class="col-md-4" style="display:none;" id="30">
						<span>30. </span>
						<form:input path="tracks[29].trackNumber" value="30" style="display:none;" />			
						<form:input path="tracks[29].name" cssClass="form-control" id="albumTrack30" />
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>
	
	<script src="<spring:url value="/resources/js/albumAddShowHide.js"/>"></script>
	
</body>
</html>