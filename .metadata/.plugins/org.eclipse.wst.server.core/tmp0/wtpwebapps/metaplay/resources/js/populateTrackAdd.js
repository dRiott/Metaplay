$(document).ready(function() {	

	var trackNumber = "1";
	var trackName = "In the Flowers";
	var trackArtistName = "Animal Collective";
	var trackAlbumName = "Merriweather Post Pavilion";
	var trackAlbumCover = "Merriweather Post Pavilion";
	var trackLengthMinutes = "5";
	var trackLengthSeconds = "22";
	var trackBpm = "111";
	var trackLyrics = "I'm a dancer \n Met a dancer \n Who was high in a field\nFrom her movement\nCaught my breath on my way home\nCouldn\'t stop that spinning force I felt in me,\nEverything around seemed to giggle glee\nShe walked up with a flower and I cared\n\nFound a dancer\nWho gets wild to the beats\nof record rhythm\nBut I\'m always away for weeks that pass slow\nMy mind gets lost\nFeeling envy for the kid who\'ll dance despite anything\nI walk out in the flowers, and feel better\n\nIf I could just leave my body for the night,\nThen we could be dancing\nNo more missing you while I\'m gone\nThere we could be dancing,\nAnd you\'d smile and say, \"I like this song\"\nAnd when our eyes will meet there\nWe will recognize nothing's wrong\nAnd I wouldn't feel so selfish\nI won\'t be this way very long\n\nTo hold you in time\nTo hold you in time\nTo hold you in time\nTo hold you in time\n\nWhile we were dancing\nEarly hours\nDrunken days finally ended\nAnd the streets turned for a pillowcase\nThen I fumbled our good lock\nThen the ecstasy turns to rising light\nThrough our windowpane\nNow I\'m gone\nI left flowers for you there";

	var trackNumberObj = document.getElementById("trackNumber");
	trackNumberObj.value = trackNumber;

	var trackNameObj = document.getElementById("trackName");
	trackNameObj.value = trackName;

	var trackArtistNameObj = document.getElementById("trackArtistName");
	trackArtistNameObj.value = trackArtistName;

	var trackAlbumNameObj = document.getElementById("trackAlbumName");
	trackAlbumNameObj.value = trackAlbumName;
	
	var trackAlbumCoverObj = document.getElementById("trackAlbumCover");
	trackAlbumCoverObj.value = trackAlbumCover;
	
	var trackLengthMinutesObj = document.getElementById("trackLengthMinutes");
	trackLengthMinutesObj.value = trackLengthMinutes;
	
	var trackLengthSecondsObj = document.getElementById("trackLengthSeconds");
	trackLengthSecondsObj.value = trackLengthSeconds;
	
	var trackBpmObj = document.getElementById("trackBpm");
	trackBpmObj.value = trackBpm;
	
	var trackLyricsObj = document.getElementById("trackLyrics");
	trackLyricsObj.value = trackLyrics;
	
	function validate(form) {
		var trackNumberValue = document.getElementById("trackNumber").value;
		var trackNameValue = document.getElementById("trackName").value;
		var trackLengthMinutesValue = document.getElementById("trackLengthMinutes").value;
		var trackLengthSecondsValue = document.getElementById("trackLengthSeconds").value;
		var errMessage = "";
		
		if(trackNumberValue.length == 0) {
			errMessage += "Please enter the track number\r\n";
		}
		if (trackNameValue.length == 0) {
			errMessage += "Please enter the track name\r\n";					
		}
		if(cardExpDateValue.length == 0) {
			errMessage += "Please enter the lenghth in minutes\r\n";					
		}
		if(amountValue.length == 0) {
			errMessage += "Please enter the lenghth in seconds";					
		}
		if(errMessage.length >0) {
			alert(errMessage);
			return false;					
		} else {
			return true;
		}
	}
			
});	//end document.ready		
			