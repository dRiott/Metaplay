/**
 * AJAX search to get the track id from the database based on the track's name.
 */

$(document).ready(function () {

		$("#findTrack").click(function() {
			$("#searchTrackDiv").show();
			$("#searchTracks").focus();
		});

		$('#searchTracks').on("keyup", function(e) {
			clearTimeout(window.timer);
			window.timer=setTimeout(function(){ // setting the delay for each keypress
				var field = e.target;
				if(field.value.length!=0) {
					$('tr').remove();
					$(document.body).css({'cursor' : 'wait'});
					$.ajax({
						headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
						url: '/rest/track?query='+field.value,
						type: 'POST',
						dataType: 'json',
						success: successfulSearch,
						error: errorFunction
					})
				} else { $('tr').remove(); }
			 }, 200); //sets the delay before ajax call.. prevents a ton of calls while client types.
		});
		
		function successfulSearch(returnedData, status) {
			var table = $('#allResultsTable');
			$(document.body).css({'cursor' : 'default'});
			if(returnedData.length===0) {
				var $row = $("<tr>");
				$row.append("<td>No results found, try another search!</td>");
				$("#allResultsTable tbody").append($row);
			} else {
				for(var i=0; i<returnedData.length; i++){
					var $row = $("<tr>");
					var url = "<td><a href='/browse/"+returnedData[i].entityType+"/"+returnedData[i].id+"'>"+returnedData[i].name+"</a> has ID: "+returnedData[i].id+"</td>"
					if(!$("#allResultsTable tr > a").is(":contains("+returnedData[i].name+")")) {
						$row.append($(url));
					}
					if(!$("#allResultsTable tr").is(":contains("+$row+")")) {
						$("#allResultsTable tbody").append($row);
					}
					$("#trackId").val(returnedData[i].id);
					$("#trackName").val(returnedData[i].name);
				}
			}
		} //end successfulSearch
		
		function errorFunction(returnedData, status) {
			console.log("ERROR: button/search/search went wrong");
			console.log("ERROR: WE GOT THIS DATA: " + returnedData);
			console.log("ERROR: WE GOT THIS STATUS: " + status)
		}
		
		
}); //end document.ready


function checkUploadContents() {
	console.log("checking the upload contents");
	
	if($("#audioFileInput").val("")) {
		$("#messages").html("Oops, the file you uploaded wasn't there.").delay(4000).hide(500);
		return false;
	} else {
		console.log("About to return true: " + $("#audioFileInput").val(""));
		return true;
	}
}
