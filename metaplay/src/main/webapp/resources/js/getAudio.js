/**
 *  Grabs the mp3 for a track from Amazon S3 using AJAX and AudioController "/retreieve" mapping and getAudioFromService() method.
 */

$(document).ready(function () {
	$(".audioSpan").each(function(index, element) {
		var id = $(element).attr("data-id"), 
			name = $(element).attr("data-name");

		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			url: "/rest/checkForAudio?id="+id+"&name="+name, 
			method: "get", 
			dataType: "json",
			success: function (data, status) {
				if(!data) {
					$(element).html("This track has no accompanying audio. <a href='/audio/upload'>Upload A Track</a>");
				} else {
					var src = "src=\"/audio/retrieve?id="+id+"&filename="+name+"\"";
					$(element).append("<audio controls preload='none'><source "+src+" type='audio/mpeg' /></audio>");
				}
			},
			error: function errorFunction(returnedData, status) {
				console.log("Load audio tags failed: " + returnedData + ", status: " + status);
			}
		}); //end AJAX
	}); //end $("audio").each()
}); // end document.ready

