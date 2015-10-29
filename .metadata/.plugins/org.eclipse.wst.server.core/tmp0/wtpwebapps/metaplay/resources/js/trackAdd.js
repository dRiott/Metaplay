$(document).ready(function () {			
			
	//Artist Options "Other" Box Hide/Unhide
	$("#trackArtistName").on("change", function(){
		if($("#trackArtistName").val()=="** New Artist **") {
			$("#newTrackArtistName").show();
		} else {
			$("#newTrackArtistName").hide();	
		}
	});
	
	//Album Options "Other" Box Hide/Unhide
	$("#trackAlbumName").on("change", function(){
		if($("#trackAlbumName").val()=="** New Album **") {
			$("#newTrackAlbumName").show();
		} else {
			$("#newTrackAlbumName").hide();
		}
	});
	
	$('[type=number]').val("");
			
}); //end document.ready

//validate the form on submit
function validate(form) {
	var artistListValue = document.getElementById("trackArtistName").value;
	var albumListValue = document.getElementById("trackAlbumName").value;
	var errMessage = "";

	if(artistListValue.includes("No Artists exist, add one!")) {
		errMessage += "Please pick an artist, or add one!\r\n";
	}
	if(albumListValue.includes("No Albums exist, add one!")) {
		errMessage += "Please pick an album, or add one!\r\n";
	}
	if(errMessage.length >0) {
		alert(errMessage);
		return false;					
	} else {
		return true;
	}
}