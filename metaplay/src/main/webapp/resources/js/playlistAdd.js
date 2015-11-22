/**
 *  Configures the sortable, draggable, and selectable tracks. Sends ajax call on submit button click to save playlist.
 */

$(document).ready(function () {
    var submitBtn = $("#playlistSubmitButton");

    
    fixWidths();
	
	//augment Function to include a method method (hides some prototype ugliness):
	Function.prototype.method = function (name, func) {
		this.prototype[name] = func;
		return this;
	};

	//augment String to include a trim method: 
	String.method('trim', function () {
		return this.replace(/^\s+|\s+$/g, '');
	});
	
	//BEGIN JQUERY SORTABLE
	//set up the tables to be sortable, and connect them for dropping
	$("#tracksTable tbody").sortable({
		connectWith: "#playlistTable tbody",
		cursor: "grabbing",
		revert: true,
		receive: function(event, ui) {
			ui.item.children().first().hide();
			fixWidths();
		}
	});
	
	$("#playlistTable tbody").sortable({
		connectWith: "#tracksTable tbody",
		cursor: "grabbing",
		revert: true,
		receive: function(event, ui) {
			//show the track number
			ui.item.children().first().text(1+ui.item.index()).show();
			orderTrackNumbers();
		},
		stop: orderTrackNumbers
	});
	//end JQUERY SORTABLE.
	
	//autocomplete for the accounts search input
	$("#accountSearchInput").autocomplete({
		search: function () {
			$(document.body).css({'cursor' : 'wait'});
		},
		source: function (term, response) {
			$.ajax({
				url: '/playlist/accountsearch', 
				data: term,
				dataType: "json",
				success: function(data) {
					//for each returned Account, set up the ui.item attributes.
					$(document.body).css({'cursor' : 'default'});

					$.each(data, function(index, element) {
						element.accountId = element.id;
						element.value=element.name;
					});
					response(data);
					
				},
				error: function (data, status) {
					$("#messageDiv").html("<h4>Something went wrong. Try again, or refresh the page.</h4>")
						.addClass("dBorder").show(200).delay(2000).hide(200);
					$(document.body).css({'cursor' : 'default'});
					console.log("ERROR: And this status: " + status)
					console.log("ERROR: With this data: " + JSON.stringify(data));
				}
			})
		},
		minLength: 2,
		delay: 300,
		select: function(event, ui) {
			//prevents the autocomplete from filling the search bar with the name, instead clears the input for next search.
			event.preventDefault();
			$("#accountSearchInput").val("");
			
			//adds a span element (contains accountId attr with Acccount's id) into div below the search input, onclick: removes it.
			if (ui.item.value === $("#accountnameOut").text().trim()) {
				$("#messageDiv").html("<h4>That's you! You'll be the owner of this playlist.</h4>")
					.addClass("dBorder").show(200).delay(3000).hide(200);
			} else if (!$("#accountsSelected").is(":contains("+ui.item.value+")")) {
				$("#accountsSelected").append("<span class='accountSpan' accountId='"+ui.item.accountId+"'>"+ui.item.value
						+"<img class='deleter' src='../resources/img/close.gif'/></span>");
				$(".deleter").click(function () {
					$(this).parent().remove();
				});
			} else {
				$("#messageDiv").html("<h4>You've already added them!</h4>")
					.addClass("dBorder").show(200).delay(2000).hide(200);
			}
		}
	}); //end account search autocomplete
	
	
	//set up event handler for the hover effect over the submit button to save playlist.
	$(submitBtn).hover(
			function () { 
				$("div").not(".DR_Textarea").not('.imageLogoDiv').not("#description").not(":contains(Playlist)").addClass("tinted");
			}, 
			function () {
				$("div").not(".DR_Textarea").not('.imageLogoDiv').not("#description").not(":contains(Playlist)").removeClass("tinted");
			}
	);
	
	//Save the playlist!
	$(submitBtn).click(function(e) {
		var playlistInfo = []; //contains all info about playlist; will be stringified into the below variable
		var tracksStringified = ""; //contains all the contents of the playlistInfo array, stringified and sent to server

		e.preventDefault();
        $(submitBtn).attr("disabled","disabled");  //disable the submit button
		$(document.body).css({'cursor' : 'wait'});  //change the cursor
		
		//add the name, description, and id to the playlistInfo array
		playlistInfo.push({name: $("#playlistName").val(), 
			description: $("#playlistDescription").val(), 
			id: $("#playlistName").attr("data-playlistId")}); //if editing an exisiting playlist, this is the playlist id, otherwise, -1
		
		//push the tracks onto the array
		$("#playlistTable tbody tr").each(function (index) {
			playlistInfo.push({trackNumber: index+=1, trackId: $(this).attr("trackId")});
		});
		
		//push the accounts onto the array
		$("#accountsSelected span").each(function() {
			playlistInfo.push({id: $(this).attr("accountId")})
		});
		
		tracksStringified = JSON.stringify(playlistInfo); //convert the array to a string to send to server

		//make the ajax call
		if (tracksStringified!==null) {
			$.ajax({
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				url: '/playlist/save',
				type: 'POST',
				dataType: 'json',
				data: tracksStringified,
				success: function (playlist) {
                    $(submitBtn).attr("disabled", false); //re-enable the button.
					console.log(JSON.stringify(playlist));
					$(document.body).css({'cursor' : 'default'});
					$("#playlistTable tbody tr").remove();
					$(".savedField").val("");
					$(".accountSpan").remove();
					var url = "<h1>Success! Allow a moment to display updates, and refresh if needed: <a href='/browse/playlist/"+playlist.id+"'>"+playlist.name+"</a></h1>";
					$("#messageDiv").html(url).addClass("dBorder").show(200).delay(3000).hide(300);
				},
				error: function (returnedData, status) {
					$(document.body).css({'cursor' : 'default'});
					console.log("Fail to save the playlist via REST, Status: " + status)
					console.log("Failed to save the playlist via REST, Data: " + JSON.stringify(returnedData));
				}
			});
		} else {
			console.log("The tracksInfo array was empty. Did not call the ajax function.");
		}
	}); //end save playlist ajax call (button on click event);
	
	
	
}); // end document.ready

//set up each tr so that when it's grabbed, it doesn't scrunch its tds.
function fixWidths() {
	$('td, th', '#tracksTable').each(function () {
        var cell = $(this);
        cell.width(cell.width());
    });
	
	$('td, th', '#playlistTable').each(function () {
		var cell = $(this);
		cell.width(cell.width());
	});

	//grab the width of the tr and set the th accordingly. workaround for scrollable tbodies that are sortable/draggable.
	$('.tdWidthTracks').each(function(index, element) {
		//console.log("1. Element Id: " + $(element).attr("id") + ", index: " + index + " has a width of " + $(element).width());
		$("#playlistTHeadTh" + index).css("width", 1.1*$(element).width());
		
		if(index > 0 && index < 3) {
			$("#trackTHeadTh" + index).css("width", 1.1*$(element).width());
			$("#editableTr"+index).css("width", 1.1*$(element).width());
		} else if (index>3) {
	    	return false;
	    }
	});
}

//set the track number to the tr's appropriate index in the tbody
function orderTrackNumbers () {
    $("#playlistTable tbody tr").each(function (index, element) {
		$(element).children().first().text(index+1);
	});
	fixWidths();
}

