<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

		<spring:url value="/ablum/review" var="thisFormURL" />                                 
		<form:form action="${thisFormURL}" method="post" modelAttribute="createAlbumWrapper" onsubmit="return validate(this);">  
		<form:errors path="*" element="div" cssClass="errors"/>
	        	<div class="form-group">
					<div class="row">                                                                    
					<div class="col-md-4">                                                         
						<label for="albumName">* Album Name</label>                                
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
						<label for="albumLengthMinutes">* Album: Minutes</label>
						<form:input path="lengthMinutes" id="albumLengthSeconds"  cssClass="form-control"/>
					</div>
	
					<div class="col-md-1">
						<label for="albumLengthSeconds">* Album: Seconds</label>
						<form:input path="lengthSeconds" id="albumLengthSeconds" cssClass="form-control"/>
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				<div class="row">			
				<label for="albumTrack">Tracks: </label>	
					<div class="col-md-4 showNextTrack">
						<span>1. </span>			
						<form:input path="track1" cssClass="form-control" id="albumTrack1" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>2. </span>			
						<form:input path="track2" cssClass="form-control" id="albumTrack2" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>3. </span>			
						<form:input path="track3" cssClass="form-control" id="albumTrack3" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>4. </span>			
						<form:input path="track4" cssClass="form-control" id="albumTrack4" />
					</div>
					<div class="col-md-4">
						<span>5. </span>			
						<form:input path="track5" cssClass="form-control" id="albumTrack5" />
					</div>
					<div class="col-md-4">
						<span>6. </span>			
						<form:input path="track6" cssClass="form-control" id="albumTrack6" />
					</div>
					<div class="col-md-4">
						<span>7. </span>			
						<form:input path="track7" cssClass="form-control" id="albumTrack7" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>8. </span>			
						<form:input path="track8" cssClass="form-control" id="albumTrack8" />
					</div>
					<div class="col-md-4 showNextTrack">
						<span>9. </span>			
						<form:input path="track9" cssClass="form-control" id="albumTrack9" />
					</div>
					<div class="col-md-4">
						<span>10. </span>			
						<form:input path="track10" cssClass="form-control" id="albumTrack10" />
					</div>
					<div class="col-md-4">
						<span>11. </span>			
						<form:input path="track11" cssClass="form-control" id="albumTrack11" />
					</div>
					<div class="col-md-4" id="12">
						<span>12. </span>			
						<form:input path="track12" cssClass="form-control" id="albumTrack12" />
					</div>

					<div class="col-md-4" style="display:none;" id="13">
						<span>13. </span>			
						<form:input path="track13" cssClass="form-control" id="albumTrack13" />
					</div>
					<div class="col-md-4" style="display:none;" id="14">
						<span>14. </span>			
						<form:input path="track14" cssClass="form-control" id="albumTrack14" />
					</div>
					<div class="col-md-4" style="display:none;" id="15">
						<span>15. </span>			
						<form:input path="track15" cssClass="form-control" id="albumTrack15" />
					</div>
					<div class="col-md-4" style="display:none;" id="16">
						<span>16. </span>			
						<form:input path="track16" cssClass="form-control" id="albumTrack16" />
					</div>
					<div class="col-md-4" style="display:none;" id="17">
						<span>17. </span>			
						<form:input path="track17" cssClass="form-control" id="albumTrack17" />
					</div>
					<div class="col-md-4" style="display:none;" id="18">
						<span>18. </span>			
						<form:input path="track18" cssClass="form-control" id="albumTrack18" />
					</div>
					<div class="col-md-4" style="display:none;" id="19">
						<span>19. </span>			
						<form:input path="track19" cssClass="form-control" id="albumTrack19" />
					</div>
					<div class="col-md-4" style="display:none;" id="20">
						<span>20. </span>			
						<form:input path="track20" cssClass="form-control" id="albumTrack20" />
					</div>
					<div class="col-md-4" style="display:none;" id="21">
						<span>21. </span>			
						<form:input path="track21" cssClass="form-control" id="albumTrack21" />
					</div>
					<div class="col-md-4" style="display:none;" id="22">
						<span>22. </span>			
						<form:input path="track22" cssClass="form-control" id="albumTrack22" />
					</div>
					<div class="col-md-4" style="display:none;" id="23">
						<span>23. </span>			
						<form:input path="track23" cssClass="form-control" id="albumTrack23" />
					</div>
					<div class="col-md-4" style="display:none;" id="24">
						<span>24. </span>			
						<form:input path="track24" cssClass="form-control" id="albumTrack24" />
					</div>
					<div class="col-md-4" style="display:none;" id="25">
						<span>25. </span>			
						<form:input path="track25" cssClass="form-control" id="albumTrack25" />
					</div>
					<div class="col-md-4" style="display:none;" id="26">
						<span>26. </span>			
						<form:input path="track26" cssClass="form-control" id="albumTrack26" />
					</div>
					<div class="col-md-4" style="display:none;" id="27">
						<span>27. </span>			
						<form:input path="track27" cssClass="form-control" id="albumTrack27" />
					</div>
					<div class="col-md-4" style="display:none;" id="28">
						<span>28. </span>			
						<form:input path="track28" cssClass="form-control" id="albumTrack28" />
					</div>
					<div class="col-md-4" style="display:none;" id="29">
						<span>29. </span>			
						<form:input path="track29" cssClass="form-control" id="albumTrack29" />
					</div>
					<div class="col-md-4" style="display:none;" id="30">
						<span>30. </span>			
						<form:input path="track30" cssClass="form-control" id="albumTrack30" />
					</div>
				</div>
				
				<div class="form-group" style="float:clear;"></div>
				
				
				<a id="trackButton" class="btn btn-default">Add More Tracks</a>
				
				
				<button type="submit" class="btn btn-default">Submit</button>
			</div>
		</form:form>
	</div>
	
	<script src="<spring:url value="/resources/js/albumAddShowHide.js"/>"></script>
	
</body>
</html>