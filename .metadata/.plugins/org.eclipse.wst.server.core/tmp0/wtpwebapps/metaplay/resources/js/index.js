$(document).ready(function () {		
	
	//give us a fun, random title.
		var dillaSays = ["Yo WTF? This cracka naked", "Just Blaze, B... Everything is coo.", "I'm a God, not a rapper. PFF Drake", "Fools rush in. Naw'm sayin'?", "Paypal david.riott1@gmail.com $5", "Say what? I'm a hustler bitch."];
		var randomNum = Math.floor(Math.random() * dillaSays.length);
		console.log(dillaSays[randomNum]);
		var mainh1 = $("#main-h1");
		mainh1.css("color", "SlateGray");
		mainh1.html(dillaSays[randomNum]);
	
	function stopRave() {
		for (var i = 1; i < 999; i++) {
			window.clearInterval(i);
		}
		$(document.body).css("backgroundColor", '#E5E5E5');
	}	
	
	function startRave() {
	    var currentColor = '#E5E5E5';
	    intervalId = setInterval(function() {
			$(document.body).css("backgroundColor", currentColor);
	        currentColor = currentColor === '#E5E5E5' ? 'black' : '#E5E5E5';
	    }, 150);
	};
	
	//start and stop the rave!
	var permaRave = $("#permaRaveButton");
	permaRave.click(startRave);
	var noMoreParty = $("#noMoreParty");
	noMoreParty.click(stopRave);
	
}); //end document.ready