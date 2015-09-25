			

			function fixOtherValue() {
				if(selectOther.value == "Other") {
				selectOther.value="";	
				}
			}

			//RecordLabel Options "Other" Box Hide/Unhide
			var selectOtherRL = document.getElementById("recordLabelName");
			var inputBoxRL = document.getElementById("newRecordLabelName");
			selectOtherRL.onchange=function(){
				if(selectOtherRL.value=="** New Record Label **") {
					inputBoxRL.style.display="inline";
				} else {
					inputBoxRL.style.display="none";	
				}
			}
			
			//Genre Options "Other" Box Hide/Unhide
			var selectOtherGR = document.getElementById("genreName");
			var inputBoxGR = document.getElementById("newGenreName");
			var inputBoxGRdescription = document.getElementById("newGenreDescription");
			selectOtherGR.onchange=function(){
				if(selectOtherGR.value=="** New Genre **") {
					inputBoxGR.style.display="inline";
					inputBoxGRdescription.style.display="inline";
				} else {
					inputBoxGR.style.display="none";	
					inputBoxGRdescription.style.display="none";
				}
			}
			
			//Album Options "Other" Box Hide/Unhide
			var selectOtherAL = document.getElementById("albumAlbumName");
			var inputBoxAL = document.getElementById("newAlbumAlbumName");
			var inputBoxALnumtracks = document.getElementById("newAlbumNumTracks");
			var inputBoxALdate = document.getElementById("newAlbumReleaseDate");
			var inputBoxALcover = document.getElementById("newAlbumAlbumCover");
			selectOtherAL.onchange=function(){
				if(selectOtherAL.value=="** New Album **") {
					inputBoxAL.style.display="inline";
					inputBoxALnumtracks.style.display="inline";
					inputBoxALdate.style.display="inline";
					inputBoxALcover.style.display="inline";
				} else {
					inputBoxAL.style.display="none";	
					inputBoxALcover.style.display="none";
					inputBoxALdate.style.display="none";
					inputBoxALcover.style.display="none";
				}
			}
			
	