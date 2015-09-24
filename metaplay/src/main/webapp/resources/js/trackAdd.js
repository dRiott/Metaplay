			
			function fixOtherValue() {
				if(selectOther.value == "Other") {
				selectOther.value="";	
				}
			}
			
			//Artist Options "Other" Box Hide/Unhide
			var selectOtherAR = document.getElementById("trackArtistName");
			var inputBoxAR = document.getElementById("optionTrackArtistName");
			selectOtherAR.onchange=function(){
				if(selectOtherAR.value=="Other" || selectOtherAR.value=="New Artist") {
					inputBoxAR.style.display="inline";
				} else {
					inputBoxAR.style.display="none";	
				}
			}
			
			//Album Options "Other" Box Hide/Unhide
			var selectOtherAL = document.getElementById("trackAlbumName");
			var inputBoxAL = document.getElementById("optionTrackAlbumName");
			selectOtherAL.onchange=function(){
				if(selectOtherAL.value=="Other") {
					inputBoxAL.style.display="inline";
				} else {
					inputBoxAL.style.display="none";	
				}
			}
			
			
