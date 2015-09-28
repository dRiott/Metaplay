			
			
			//Artist Options "Other" Box Hide/Unhide
			var selectOtherAR = document.getElementById("trackArtistName");
			var inputBoxAR = document.getElementById("newTrackArtistName");
			selectOtherAR.onchange=function(){
				if(selectOtherAR.value=="** New Artist **") {
					inputBoxAR.style.display="inline";
				} else {
					inputBoxAR.style.display="none";	
				}
			}
			
			//Album Options "Other" Box Hide/Unhide
			var selectOtherAL = document.getElementById("trackAlbumName");
			var inputBoxAL = document.getElementById("newTrackAlbumName");
			var inputBoxALcover = document.getElementById("newTrackAlbumCover");
			selectOtherAL.onchange=function(){
				if(selectOtherAL.value=="** New Album **") {
					inputBoxAL.style.display="inline";
					inputBoxALcover.style.display="inline";
				} else {
					inputBoxAL.style.display="none";	
					inputBoxALcover.style.display="none";
				}
			}
			
			
