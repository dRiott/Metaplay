			//Artist Options "Other" Box Hide/Unhide
			//having to use .onchange= instead of an addEventListener method because of how the page is rendered
			//by Spring form... It's altering the form:form to be a button - I can no longer select the right element.
			var selectOtherAR = document.getElementById("albumArtistName");
			var inputBoxAR = document.getElementById("newAlbumArtistName");
			selectOtherAR.onchange=function(){
				if(selectOtherAR.value=="** New Artist **") {
					inputBoxAR.style.display="inline";
				} else {
					inputBoxAR.style.display="none";	
				}
			}
			
			//having to use .onchange= instead of an addEventListener method because of how the page is rendered
			//by Spring form... It's altering the form:form to be a button - I can no longer select the right element.
			var selectOtherRL = document.getElementById("recordLabelName");
			var inputBoxRL = document.getElementById("newRecordLabelName");
			selectOtherRL.onchange=function(){
				if(selectOtherRL.value=="** New Record Label **") {
					inputBoxRL.style.display="inline";
				} else {
					inputBoxRL.style.display="none";	
				}
			}
			
			function reveal(id) {
				id += 1;
				element = document.getElementById(id);
				element.style.display="inline";
				if(id>11) {
					window.scrollTo(0,document.body.scrollHeight);
				}
			}

			function getRidOfZeroes() {
				for(var i = 1; i<31; i++){
					var minutesId = "minutes" + i;
					var minutes = document.getElementById(minutesId);
					minutes.value = "";
					var secondsId = "seconds" + i;
					var seconds = document.getElementById(secondsId);
					seconds.value = "";
					var bpmId = "bpm" + i;
					var bpm = document.getElementById(bpmId);
					bpm.value = "";
				}
				var albumMinutes = document.getElementById("albumMinutes");
				albumMinutes.value="";
				var albumSeconds = document.getElementById("albumSeconds");
				albumSeconds.value="";
			}
			
			function fixNumberFormatException() {
				for(var i = 1; i<31; i++){
					var minutesId = "minutes" + i;
					var minutes = document.getElementById(minutesId);
					if(minutes.value == "") {
						minutes.value = 0;
					};
					var secondsId = "seconds" + i;
					var seconds = document.getElementById(secondsId);
					if(seconds.value == "") {
						seconds.value = 0;
					};
					var bpmId = "bpm" + i;
					var bpm = document.getElementById(bpmId);
					if(bpm.value == "") {
						bpm.value = 0;
					};
				}
				var albumMinutes = document.getElementById("albumMinutes");
				if(albumMinutes.value == "") {
					albumMinutes.value = 0;
				};
				var albumSeconds = document.getElementById("albumSeconds");
				if(albumSeconds.value == "") {
					albumSeconds.value = 0;
				};
			}
			
