/**
 * If you're waiting for something to load, or a submit to complete, animate a little waiting thingy.
 */

$(document).ready(function () {
	
	var artistSearch = $("#searchArtists");
	var searchAll = $("#searchAll");
	
	artistSearch.keydown(function () {
//		console.log("keydown");
//		console.log("Table rows: " + allResultsTable.length);
	});
	
	searchAll.keydown(function () {
		if($("#allResultsTable tr").length==0 && $("#searchAll").val()>0) {
			console.log("waiting");
			$(document.body).css({'cursor' : 'wait'});
		} else {
			$(document.body).css({'cursor' : 'default'});
		}
	});
	
	
}); //end document.ready