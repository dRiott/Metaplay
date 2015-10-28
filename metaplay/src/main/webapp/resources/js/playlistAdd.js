/**
 *  Configures the sortable, draggable, and selectable tracks. Sends ajax call on submit button click to save playlist.
 */

$(document).ready(function () {
	
	//BEGIN NORMAL PAGE MANIPULATION STUFF
	//set up the tables to be sortable, and connect them for dropping
	$("#tracksTable tbody").sortable({
		connectWith: "#playlistTable tbody"
	});
	
	$("#playlistTable tbody").sortable({
		connectWith: "#tracksTable tbody"
	});
	
	//set up each tr so that when it's grabbed, it doesn't scrunch its tds.
	$('td, th', '#tracksTable').each(function () {
        var cell = $(this);
        cell.width(cell.width());
    });
	
	$('td, th', '#playlistTable').each(function () {
		var cell = $(this);
		cell.width(cell.width());
	});

	//grab the width of the tr and set the th accordingly. workaround for scrollable tbodies that are sortable/draggable.
	$('.tdWidth').each(function(index) {
	    $("#playlistTHeadTh" + index).css("width", 1.1*$(this).width());
	    $("#trackTHeadTh" + index).css("width", 1.1*$(this).width());
	    if (index > 2) {
	    	return false;
	    }
	});
	//end NORMAL PAGE MANIPULATION STUFF.
		
	
	
	//sends ajax call to save the playlist
	$("#playlistSubmitButton").click(function(e) {
		var tracksInfo = [];
		var tracksStringified = "";
		
		e.preventDefault();
		$(document.body).css({'cursor' : 'wait'});
		console.log("You clicked the submit button!");
		
		$("#playlistTable tbody tr").each(function (index) {
			tracksInfo.push({trackNumber: index+=1, trackId: $(this).attr("trackId")});
		});
		
		$("#accountsSelected span").each(function() {
			tracksInfo.push({accountname: $(this).attr("accountname")})
		});
		
		tracksStringified = JSON.stringify(tracksInfo);
		console.log("Stringified tracksInfo data: " + tracksStringified);
		
		
		if (tracksStringified!==null) {
			
			$.ajax({
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				url: '/metaplay/playlist/save',
				type: 'POST',
				dataType: 'json',
				data: tracksStringified,
				success: playlistSuccess,
				error: playlistError
			});
			
		} else {
			console.log("The tracksInfo array was empty. Did not ajax call.");
		}

	}); //end save playlist ajax call (button on click event);
	
	//autocomplete for the accounts search input
	$("#accountSearchInput").autocomplete({
		source: '/metaplay/playlist/accountsearch',
		minLength: 2,
		delay: 1000,
		select: function(event, ui) {
			
			//appends the accountname into the div below the search input, onclick: removes it.
			if(!$("#accountsSelected").is(":contains("+ui.item.value+")")) {
				$("#accountsSelected").append("<span class='accountSpan' accountname='"+ui.item.value+"'>"+ui.item.value
						+"<img class='deleter' src='../resources/img/close.gif'/></span>");
				$(".deleter").click(function () {
					$(this).parent().remove();
				});
			} else {
				$("#messageDiv").html("<h4>You've already added them!</h4>").show(1000).delay(1500).hide(1000)
			}
		}
	}); //end account search autocomplete
	

	$("#playlistSubmitButton").hover(tintWindow, untintWindow);
//	$("#messageDiv").on("resize" function() {
//		console.log("Change occured");
//		$(this).hide(3000);
//	})
	
}); // end document.ready

	
function playlistSuccess() {
	$(document.body).css({'cursor' : 'default'});
	$("#messageDiv").html("Playlist created successfully.").show(1000);
	$("#playlistTable tbody tr").remove();
}

function playlistError(returnedData, status) {
	$(document.body).css({'cursor' : 'default'});
	console.log("ERROR:Fail to save the playlist via REST, Data: " + returnedData);
	console.log("ERROR:Fail to save the playlist via REST, Status: " + status)
}

function tintWindow() {
	$("div").not(".DR_Textarea").not("#description").not(":contains(Playlist)").css({"opacity": ".5", "-webkit-filter" : "blur(2px)"});
}

function untintWindow() {
	$("div").not("#playlistSubmitButton").css({"opacity": "1", "-webkit-filter" : "grayscale(0%)"});
}

