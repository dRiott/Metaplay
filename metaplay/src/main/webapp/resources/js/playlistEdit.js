/**
 *  Configures the edit/delete buttons, sends playlist to playlist_add.jsp to edit a playlist.
 */

$(document).ready(function () {
	var editBtn = $("#drEditBtn");
	var deleteBtn = $("#drDeleteBtn");
	var confirmDeleteBtn = $("#drConfirmDeleteBtn");
	var cancelBtn = $("#drCancelBtn");
	var buttonsDiv = $("#drEditButtonDiv");
	var confirmButtonsDiv = $("#drConfirmDeleteDiv");
	var activeAccount = $("#accountnameOut").text().trim();
	var playlistId = $("#playlistHeader").attr('data-playlistId');
	
	//augment Function to include a method method (hides some prototype ugliness):
	Function.prototype.method = function (name, func) {
		this.prototype[name] = func;
		return this;
	};

	//augment String to include a trim method: used to get activeAccount
	String.method('trim', function () {
		return this.replace(/^\s+|\s+$/g, '');
	});
	
	//Allow those authenticated WITH Playlist OWNERSHIP to see edit and delete buttons.
	$(".playlistOwner").each(function (index, element) {
		if($(element).text() === (activeAccount + " (That's you!)")) { //they own this playlist
			$(buttonsDiv).fadeIn(500);
		}
	});
	
	//set up the confirm/cancel DELETE playlist
	$(deleteBtn).click(function() {
		$(deleteBtn).hide();
		$(editBtn).hide();
		$(confirmButtonsDiv).fadeIn(500);
	});
	
	$(cancelBtn).click(function() {
		$(confirmButtonsDiv).fadeOut(500);
		$(editBtn).show(500);
		$(deleteBtn).show(500);
	});
	
	//set up the confirm/cancel EDIT playlist
	$(editBtn).click(function() {
		$(deleteBtn).hide();
		$(editBtn).hide();
		
		$("#messageDiv")
			.removeClass("error").addClass("msg dBorder")
			.html("<h5 id='messageHeader'>To edit this playlist, you will be navigate away from this page.</h5>" +
			"<button id='confirmEdit' class='btn btn-default editButton'>Take Me There</button>" +
			"<button id='cancelEdit'  class='btn btn-default editButton'>Cancel</button>")
			.fadeIn(500);
		
		//go to the edit/{playlistId} page to edit the playlist
		$("#confirmEdit").click(function () {
			location.href = "http://metaplay.me/playlist/edit/"+playlistId;
		});
		
		$("#cancelEdit").click(function () {
			$("#messageDiv").fadeOut(500);
			$(editBtn).show(800);
			$(deleteBtn).show(800);
		});
		
	}); //end editBtn.click() -- 
	
	//delete the playlist
	$(confirmDeleteBtn).click(function () {
		$(document.body).css({'cursor' : 'wait'});
		
		$.ajax({
			headers: { 'Accept': 'application/json', 'Content-Type': 'application/json' },
			url: '/playlist/delete?id='+playlistId,
			type: 'POST',
			dataType: 'json',
			success: function(data) {
				console.log(JSON.stringify(data));
				//because the playlist we were looking at no longer exists, return to the browse playlists view.
				location.href = "http://metaplay.me/browse/playlists";
			},
			error: function (data, status) {
				$("#messageDiv")
					.removeClass("msg").addClass("error dBorder")
					.html("<h4>Something went wrong. Try again, or refresh the page.</h4>")
					.fadeIn(500).delay(3000).fadeOut(500);
				
				$(document.body).css({'cursor' : 'default'});
				console.log("ERROR: With this data: " + JSON.stringify(data));
				console.log("ERROR: And this status: " + status)
			}
			}); //end ajax call to delete playlist
	}); //end delete the playlist onclick
	

}); //end document.ready
