$(document).ready(function(){
	var countTEXT = 0;
	var countJSON = 0;
	var json;
	
	// *****************************START Event Handlers***************************************
	//random result button
	$('#randomResultButton').click(function() {
        console.log("in random result button");
        getRandomSingleResult();
	});	
	
	//search artists input box
	$('#searchArtists').on("keyup", function(e) {
		var field = e.target;
		if(field.value.length!=0) {
			$('tr').remove();
			$.ajax({
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				url: '/metaplay/rest/artist?query='+field.value,
				type: 'POST',
				dataType: 'json',
			//	data: 'query='+field.value,
				success: successfulSearchArtist,
				error: errorFunction
			})
		} else {
			$('tr').remove();
		}
	});
	
	//search all input box
	$('#searchAll').on("keyup", function(e) {
		var field = e.target;
		if(field.value.length!=0) {
			$('tr').remove();
			$.ajax({
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				},
				url: '/metaplay/rest/allentities?query='+field.value,
				type: 'POST',
				dataType: 'json',
				//	data: 'query='+field.value,
				success: successfulSearchAll,
				error: errorFunction
			})
		} else {
			$('tr').remove();
		}
	});
	// *****************************END Event Handlers***************************************	
	
	//gets a random result
	function getRandomSingleResult() {
		console.log("in getRandomResult function");
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			url: "/metaplay/rest/singlerandom", 
			method: "get", 
			dataType: "json",
			success: successRandomSingleResult,
			error: errorFunction
		})
	}

	function successRandomSingleResult(returnedData, status) {
		var type = returnedData.entityType;
		if (type==="location") {
			var url = "<a href='/metaplay/browse/"+returnedData.entityType+"/"+returnedData.id+"'>"+returnedData.city+", " + returnedData.state + "</a>"
		} else if (type==="member") {
			var url = "<a href='/metaplay/browse/"+returnedData.entityType+"/"+returnedData.id+"'>"+returnedData.lastName+", " + returnedData.firstName + "</a>"
		} else {
			var url = "<a href='/metaplay/browse/"+returnedData.entityType+"/"+returnedData.id+"'>"+returnedData.name+"</a>"
		}
		$('#randomResultDiv').html(url);
	}
	
	function successfulSearchArtist(returnedData, status) {
		var table = $('#allResultsTable');
		for(var i=0; i<returnedData.length; i++){
			 var $row = $("<tr>");
			 var url = "<td><a href='/metaplay/browse/artist/"+returnedData[i].id+"'>"+returnedData[i].name+"</a></td>"
			 if(!$("#allResultsTable tr > a").is(":contains("+returnedData[i].name+")")) {
				 $row.append($(url));
			 }
			 $("#allResultsTable tbody").append($row);
		}
	}
	
	function successfulSearchAll(returnedData, status) {
		var table = $('#allResultsTable');
		for(var i=0; i<returnedData.length; i++){
			var $row = $("<tr>");
			var url = "<td><a href='/metaplay/browse/"+returnedData[i].entityType+"/"+returnedData[i].id+"'>"+returnedData[i].name+"</a></td>"
			 if(!$("#allResultsTable tr > a").is(":contains("+returnedData[i].name+")")) {
				$row.append($(url));
			}
			$("#allResultsTable tbody").append($row);
		}
	}
	
	function errorFunction(returnedData, status) {
		console.log("ERROR: button/search/search went wrong");
		console.log("ERROR: WE GOT THIS DATA: " + returnedData);
		console.log("ERROR: WE GOT THIS STATUS: " + status)
	}
	
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
			console.log("ERROR:loadAllEntities DATA: " + returnedData);
			console.log("ERROR:loadAllEntities STATUS: " + status)
		}
	})
	
}); //end document.ready
	

	
	