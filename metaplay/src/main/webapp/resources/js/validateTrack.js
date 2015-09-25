			
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
			
			