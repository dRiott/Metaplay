			//Artist Options "Other" Box Hide/Unhide
			var selectOtherAR = document.getElementById("albumArtistName");
			var inputBoxAR = document.getElementById("newAlbumArtistName");
			selectOtherAR.onchange=function(){
				if(selectOtherAR.value=="** New Artist **") {
					inputBoxAR.style.display="inline";
				} else {
					inputBoxAR.style.display="none";	
				}
			}
//
//			var box30 = document.getElementById("30");
//			box30.ondblclick=function(){
//				alert("Sorry, but only 30 tracks are currently supported. Try separating into multiple albums.");
//			}
			
			function reveal(id) {
				id += 1;
				element = document.getElementById(id);
				element.style.display="inline";
				if(id>11) {
					window.scrollTo(0,document.body.scrollHeight);
				}
			}
			
//			function revealAndScroll(id) {
//				id += 1;
//				element = document.getElementById(id);
//				element.style.display="inline";
//				window.scrollTo(0,document.body.scrollHeight);
//			}

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
			
			alert("YO!");
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
			
