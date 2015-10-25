	
$(document).ready(function () {
	
			//Genre Options "Other" Box Hide/Unhide
			var selectOtherGR = document.getElementById("genreName");
			var inputBoxGR = document.getElementById("newGenreName");
			var inputBoxGRdescription = document.getElementById("newGenreDescription");
			
			//having to use .onchange= instead of an addEventListener method because of how the page is rendered
			//by Spring form... It's altering the form:form to be a button - I can no longer select the right element.
			selectOtherGR.onchange=function(){
				if(selectOtherGR.value=="** New Genre **") {
					inputBoxGR.style.display="inline";
					inputBoxGRdescription.style.display="inline";
				} else {
					inputBoxGR.style.display="none";	
					inputBoxGRdescription.style.display="none";
				}
			}
			
			//Location Options "** New Country **" Show/Hide
			var selectCountry = $("#countries");
			var statesDiv = $("#location-state-div");
			var newCountryName = $("#newCountryName");
			
			selectCountry.change(function(){
				var selectCountryValue = selectCountry.val();

				if(selectCountryValue=="** New Country **") {
					newCountryName.css("display", "inline");
					statesDiv.css("display", "none");
					
				} else if (selectCountryValue=="United States") {
					statesDiv.css("display", "inline");
					newCountryName.css("display", "none");
				} else {
					newCountryName.css("display", "inline");				
				}
			}); //end selectCountry.change()
			
			//Album Options "Other" Box Hide/Unhide
			var selectOtherAL = document.getElementById("albumAlbumName");
			var inputBoxAL = document.getElementById("newAlbumAlbumName");
			var inputBoxALnumtracks = document.getElementById("newAlbumNumTracks");
			var inputBoxALdate = document.getElementById("newAlbumReleaseDate");
			var inputBoxALcover = document.getElementById("newAlbumAlbumCover");
			var inputBoxAlrecordlabel = document.getElementById("newAlbumRecordLabel");
			var orAlbumAddLink = document.getElementById("orAlbumAddLink");
		
			selectOtherAL.onchange=function(){
				if(selectOtherAL.value=="** New Album **") {
					inputBoxAL.style.display="inline";
					orAlbumAddLink.style.display="inline";
				} else {
					inputBoxAL.style.display="none";	
					orAlbumAddLink.style.display="none";
				}
			}
			
			var selectOtherRL = document.getElementById("recordLabelName");
			var inputBoxRLname = document.getElementById("newRecordLabelName");
			var inputBoxRLcity = document.getElementById("newRecordLabelCity");
			var inputBoxRLstate = document.getElementById("newRecordLabelState");
			selectOtherRL.onchange=function(){
				if(selectOtherRL.value=="** New Record Label **") {
					inputBoxRLname.style.display="inline";
					inputBoxRLcity.style.display="inline";
					inputBoxRLstate.style.display="inline";
				} else {
					inputBoxRLname.style.display="none";
					inputBoxRLcity.style.display="none";
					inputBoxRLstate.style.display="none";
				}
			}
			
			
			$( "input[type=checkbox]" ).on( "click", showHideStageName);
			
			function showHideStageName() {
				for (var i=7; i-=1;) {
					if($("#stageNameCheck"+i).is(":checked")) {
						$("#stageNameDiv"+i).css("display", "inline");
					} else {
						$("#stageNameDiv"+i).css("display", "none");
					}
				}
			}
			
});