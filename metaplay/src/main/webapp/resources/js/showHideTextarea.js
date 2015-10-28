/**
 * 
 */

$(document).ready(function () {
	
	//handles the browse_genres and browse_artists pages which have a table of textareas to show/hide
	if($("#tableWithLongText").length != 0) {
		for(var i = $("#tableWithLongText tr").length; i-=1;) {		
			//grabs the more button, sets up click function
			$("#containsBio"+i).next().children().first().click(function () {
				$(this).parent().prev().children().first().show();
				$(this).siblings().show();
				$(this).hide();
			});
			
			//grabs the less button, sets up click function
			$("#containsBio"+i).next().children().last().click(function() {
				$(this).parent().prev().children().first().hide();
				$(this).siblings().show();
				$(this).hide();
			});
		}
		
	//handles the single_genre and single_artist pages which only have one textarea to show/hide
	} else {
		$("#showButton").click(function() {
			$(this).prev().show();
			$("#hideButton").show();
			$(this).hide();
		});
		
		$("#hideButton").click(function() {
			$("#showButton").prev().hide();
			$("#showButton").show();
			$(this).hide();
		});
	}
	
}); //end document.ready

