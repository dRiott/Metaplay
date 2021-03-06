	
$(document).ready(function () {
	
			//Genre Options "Other" Box Hide/Unhide
			var selectOtherGR = document.getElementById("genreName");
			selectOtherGR.onchange=function(){
				var inputBoxGR = document.getElementById("newGenreName");
				var inputBoxGRdescription = document.getElementById("newGenreDescription");
				
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
			selectCountry.change(function(){
				var city = $("#location-city");
				var statesDiv = $("#location-state-div");
				var newCountryDiv = $("#newCountryDiv");
				var newCountryInput = $("#newCountryInput");
				var selectCountryValue = selectCountry.val();

				if(selectCountryValue=="United States") {
					statesDiv.show();
					newCountryDiv.hide();				
					city.attr("placeholder", "Birmingham");
				} else if (selectCountryValue=="** New Country **") {
					
					city.focus();
					city.blur(function () {
						newCountryInput.focus()
					})
					
					statesDiv.hide();
					newCountryInput.attr("placeholder", "England");
					newCountryDiv.show();
					city.attr("placeholder", "London");
				} else {
					statesDiv.hide();
					newCountryDiv.hide();				
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