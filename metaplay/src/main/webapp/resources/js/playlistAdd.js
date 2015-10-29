/**
 *  Configures the sortable, draggable, and selectable tracks. Sends ajax call on submit button click to save playlist.
 */

$(document).ready(function () {
	
	//augement Function to include a method method (hides some prototype ugliness):
	Function.prototype.method = function (name, func) {
		this.prototype[name] = func;
		return this;
	}
	
	//augment String to include a trim method: 
	String.method('trim', function () {
		return this.replace(/^\s+|\s+$/g, '');
	});
	
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
		
	
	//autocomplete for the accounts search input
	$("#accountSearchInput").autocomplete({
		source: function (term, response) {
			$.ajax({
				url: '/metaplay/playlist/accountsearch', 
				data: term,
				dataType: "json",
				success: function(data) {
					//for each returned Account, set up the ui.item attributes.
					$.each(data, function(index, element) {
						element.accountId = element.id;
						element.value=element.name;
					});
					response(data);
				},
				error: defaultError
			})
		},
		minLength: 2,
		delay: 1000,
		select: function(event, ui) {
			//prevents the autocomplete from filling the search bar with the name, instead clears the input for next search.
			event.preventDefault();
			$("#accountSearchInput").val("");
			
			//adds a span element (contains accountId attr with Acccount's id) into div below the search input, onclick: removes it.
			if (ui.item.value === $("#accountnameOut").text().trim()) {
				$("#messageDiv").html("<h4>That's you! You'll be the owner of this playlist.</h4>").show(200).delay(3000).hide(200);
			} else if (!$("#accountsSelected").is(":contains("+ui.item.value+")")) {
				$("#accountsSelected").append("<span class='accountSpan' accountId='"+ui.item.accountId+"'>"+ui.item.value
						+"<img class='deleter' src='../resources/img/close.gif'/></span>");
				$(".deleter").click(function () {
					$(this).parent().remove();
				});
			} else {
				$("#messageDiv").html("<h4>You've already added them!</h4>").show(200).delay(2000).hide(200);
			}
		}
	}); //end account search autocomplete
	
	
	//set up event handler for the hover effect over the submit button to save playlist.
	$("#playlistSubmitButton").hover(tintWindow, untintWindow);
	
	
	
	//Save the playlist!
	$("#playlistSubmitButton").click(function(e) {
		var playlistInfo = [];
		var tracksStringified = "";
		
		console.log("You clicked the submit button!");
		e.preventDefault();
		$(document.body).css({'cursor' : 'wait'});
		
		playlistInfo.push({name: $("#playlistName").val(), description: $("#playlistDescription").val()});
		
		$("#playlistTable tbody tr").each(function (index) {
			playlistInfo.push({trackNumber: index+=1, trackId: $(this).attr("trackId")});
		});
		
		$("#accountsSelected span").each(function() {
			playlistInfo.push({id: $(this).attr("accountId")})
		});
		
		tracksStringified = JSON.stringify(playlistInfo);
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
	
	
	
}); // end document.ready

	
function playlistSuccess() {
	console.log("in success");
	$(document.body).css({'cursor' : 'default'});
	$("#messageDiv").html("Playlist created successfully.").show(1000);
	$("#playlistTable tbody tr").remove();
}

function playlistError(returnedData, status) {
	$(document.body).css({'cursor' : 'default'});
	console.log("ERROR:Fail to save the playlist via REST, Data: " + JSON.stringify(returnedData));
	console.log("ERROR:Fail to save the playlist via REST, Status: " + status)
}

function defaultError(data, status) {
	console.log("ERROR: an ajax call failed:");
	console.log("ERROR: With this data: " + JSON.stringify(data));
	console.log("ERROR: And this status: " + status)
}

function tintWindow() {
	$("div").not(".DR_Textarea").not('.imageLogoDiv').not("#description").not(":contains(Playlist)").addClass("tinted");
}

function untintWindow() {
	$("div").not(".DR_Textarea").not('.imageLogoDiv').not("#description").not(":contains(Playlist)").removeClass("tinted");
}