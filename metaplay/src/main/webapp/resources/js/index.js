
	var dillaSays = ["Yo WTF? This cracka naked", "Just Blaze, B... Everything is coo.", "I'm a God, not a rapper. PFF Drake", "Fools rush in. Naw'm sayin'?", "Paypal david.riott1@gmail.com $5", "Say what? I'm a hustler bitch."];
	var randomNum = Math.floor(Math.random() * dillaSays.length);
	
	function fnSetStatus() {
		console.log(dillaSays[randomNum]);
		var mainh1 = document.getElementById("main-h1");
		mainh1.style.color = "SlateGray";
		mainh1.innerHTML = dillaSays[randomNum];
	}
	
	function stopRave() {
		clearInterval(intervalId);
		document.body.style.backgroundColor = '#E5E5E5';
	}	
	
	function startRave() {
	    var currentColor = '#E5E5E5';
	    intervalId = setInterval(function() {
	        document.body.style.backgroundColor = currentColor;
	        currentColor = currentColor === '#E5E5E5' ? 'black' : '#E5E5E5';
	    }, 200);
	};
	
	function addEventHandlers() {
		var permaRave = document.getElementById("permaRaveButton");
		permaRave.addEventListener("click", startRave, false);
		var noMoreParty = document.getElementById("noMoreParty");
		noMoreParty.addEventListener("click", stopRave, false);
	}