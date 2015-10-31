//Load up all the entities into allEntitiesList
$.ajax({
	headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
	url: "/metaplay/rest/loadAllEntities", 
	method: "get", 
	dataType: "json",
	success: function () {
		console.log("The load was successful.")
	},
	error: function (returnedData, status) {
		console.log("ERROR:loadAllEntities Fail to loadAllEntities via REST");
		console.log("ERROR:loadAllEntities DATA: " + JSON.stringify(returnedData));
		console.log("ERROR:loadAllEntities STATUS: " + status)
	}
});

$(document).ready(function(){
	var countTEXT = 0;
	var countJSON = 0;
	var json;
	
	// *****************************START Event Handlers***************************************
	//random result button
	$('#randomResultButton').click(function () {
		$("#randomResultButton").attr("disabled","disabled"); //re-enabled in successRandomSingleResult.
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			url: "/metaplay/rest/singlerandom", 
			method: "get", 
			dataType: "json",
			success: function (returnedData, status) {
				$('#randomResultDiv').html("<a href='/metaplay/browse/"+returnedData.entityType+"/"+returnedData.id+"'>"+returnedData.name+"</a>");
				$("#randomResultButton").attr("disabled", false); //re-enable the button.
			},
			error: errorFunction
		});
	});
	
	//search artists input box
	$('#searchArtists').on("keyup", function(e) {
		clearTimeout(window.timer);
		window.timer=setTimeout(function(){ // setting the delay for each keypress
			var field = e.target;
			if(field.value.length!=0) {
				$('tr').remove();
				$(document.body).css({'cursor' : 'wait'});
				$.ajax({
					headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
					url: '/metaplay/rest/artist?query='+field.value,
					type: 'POST',
					dataType: 'json',
					success: successfulSearch,
					error: errorFunction
				})
			} else { $('tr').remove(); }
		 }, 200); //sets the delay before ajax call.. prevents a ton of calls while client types.
	});
	
	//search all input box
	$('#searchAll').on("keyup", function(e) {
		clearTimeout(window.timer);
		window.timer=setTimeout(function(){ // setting the delay for each keypress
			var field = e.target;
			if(field.value.length!=0) {
				$('tr').remove();
				$(document.body).css({'cursor' : 'wait'});
				$.ajax({
					headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
					url: '/metaplay/rest/allentities?query='+field.value,
					type: 'POST',
					dataType: 'json',
					success: successfulSearch,
					error: errorFunction
				});
			} else {
				$('tr').remove();
			}
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
				var url = "<td><a href='/metaplay/browse/"+returnedData[i].entityType+"/"+returnedData[i].id+"'>"+returnedData[i].name+"</a></td>"
				if(!$("#allResultsTable tr > a").is(":contains("+returnedData[i].name+")")) {
					$row.append($(url));
				}
				if(!$("#allResultsTable tr").is(":contains("+$row+")")) {
					$("#allResultsTable tbody").append($row);
				}
			}
		}
	} //end successfulSearch
	
	function errorFunction(returnedData, status) {
		console.log("ERROR: button/search/search went wrong");
		console.log("ERROR: WE GOT THIS DATA: " + returnedData);
		console.log("ERROR: WE GOT THIS STATUS: " + status)
	}
	
}); //end document.ready
	

	
	