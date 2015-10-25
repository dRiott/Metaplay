$(document).ready(function() {
	
	var artistTableNumRows = document.getElementById("artistsTable").rows.length-1;
	
	//Artist Options "Other" Box Hide/Unhide
	//having to use .onchange= instead of an addEventListener method because of how the page is rendered
	//by Spring form... It's altering the form:form to be a button - I can no longer select the right element.
	function showHideBiography() {
		var artistTableNumRows = document.getElementById("artistsTable").rows.length-1;
		console.log("inside something() function...");
		for(var i = 1; i < artistTableNumRows; i++) {
			console.log("inside for loop, iteration (begins @ 1): " + i);
			var spanId = document.getElementById("moreLessBiography"+i);
			console.log("moreLessBiography"+i);
			var showButton = document.getElementById("showButton" + (i));
			console.log("showButton"+i);
			var hideButton = document.getElementById("hideButton" + (i));					console.log("showButton"+i);
			console.log("hideButton"+i);
	
			
			showButton.onclick=function(){
				spanId.style.display="inline";
				hideButton.style.display="inline";
				showButton.style.display="none";
			}
			
			hideButton.onclick=function(){
				spanId.style.display="none";
				hideButton.style.display="none";
				showButton.style.display="inline";
			}
		}
	}
	
	function fixFinalRow() {
		var numRows = document.getElementById("artistsTable").rows.length-1;
		console.log("Number of Rows in the artist table: " + numRows);
		
		var finalSpan = document.getElementById("moreLessBiography"+numRows);
		console.log(finalSpan.innerHTML);
		
		var finalMoreButton = document.getElementById("showButton"+numRows);
		console.log("More Button InnerHTML: " + finalMoreButton.innerHTML);
	
		var finalLessButton = document.getElementById("hideButton"+numRows);
		console.log("Less Button InnerHTML: " + finalLessButton.innerHTML);
	
		finalMoreButton.onclick=function() {
			finalSpan.style.display="inline";
			finalLessButton.style.display="inline";
			finalMoreButton.style.display="none";
		}
		
		finalLessButton.onclick=function() {
			finalSpan.style.display="none";
			finalMoreButton.style.display="inline";
			finalLessButton.style.display="none";
		}
	}
	
			
});

			
