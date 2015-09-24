			

			function fixOtherValue() {
				if(selectOther.value == "Other") {
				selectOther.value="";	
				}
			}

			//RecordLabel Options "Other" Box Hide/Unhide
			var selectOtherRL = document.getElementById("recordLabel-name");
			var inputBoxRL = document.getElementById("optionRecordLabel-name");
			selectOtherRL.onchange=function(){
				if(selectOtherRL.value=="Other") {
					inputBoxRL.style.display="inline";
				} else {
					inputBoxRL.style.display="none";	
				}
			}
			
			//Genre Options "Other" Box Hide/Unhide
			var selectOtherGR = document.getElementById("genre-name");
			var inputBoxGR = document.getElementById("optionGenre-name");
			selectOtherGR.onchange=function(){
				if(selectOtherGR.value=="Other") {
					inputBoxGR.style.display="inline";
				} else {
					inputBoxGR.style.display="none";	
				}
			}
			
	
			
	