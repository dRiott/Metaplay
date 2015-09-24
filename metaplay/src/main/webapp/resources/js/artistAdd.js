			
	//RecordLabel Options "Other" Box Hide/Unhide
		
			var selectOther = document.getElementById("recordLabel-name");
			var inputBox = document.getElementById("optionRecordLabel-name");
			selectOther.onchange=function(){
				if(selectOther.value=="Other") {
					inputBox.style.display="inline";
				} else {
					inputBox.style.display="none";	
				}
			}
			
			function fixOtherValue() {
				if(selectOther.value == "Other") {
					selectOther.value="";	
				}
			}
						
	//RecordLabel Options "Other" Box Hide/Unhide
			var selectOther2 = document.getElementById("genre-name");
			var inputBox2 = document.getElementById("optionGenre-name");
			selectOther2.onchange=function(){
				if(selectOther2.value=="Other") {
					inputBox2.style.display="inline";
				} else {
					inputBox2.style.display="none";	
				}
			}
	