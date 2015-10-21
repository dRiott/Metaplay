	drakes = [];
	moveDrakesIntervals = [];
	function addDrakes() {
	    var currentUrl = '../resources/img/tinyDrake.gif';
	   
	    addDrakesInterval = setInterval(function() {
	    	var drake = document.createElement("img");
			var drakesLength = drakes.length;
	    	drake.src=currentUrl;
	    	drake.alt="Drake404 " + drakesLength;
	    	drake.title="Drake404 " + drakesLength;
	    	drake.id="Drake404 " + drakesLength;
	    	drake.className="drakeImg";
	    	drake.style.top=Math.random()*600 +"px";
	    	drake.style.left=Math.random()*300 +"px";
	    	drake.style.position="relative";
	    	var mainDiv = document.getElementById("main");
	    	drakes.push(drake);
	    	moveDrakesInterval = setInterval(function() {
	    		move(drake);
	    	}, 300);
	    	moveDrakesIntervals.push(moveDrakesInterval);
	        document.body.insertBefore(drake, mainDiv);
	    }, 300);
	}

	function move(img) {
		var x = 100, y = 30;
		var left, top;
		if (Math.random() < 0.5) {
			x = -x;
		}
		if (Math.random() < 0.5) {
			y = -y;
		}
		left = parseInt(img.style.left);
		top = parseInt(img.style.top);
		if((left + x) > 500 || (left + x) < 0) {
			console.log("I'm over the line: left");
			img.style.left = "150px";		
			img.style.top = "150px";	
		} else{
		img.style.left = (left + x) + "px";
		}
		if((top+y)> 500 || (top + y) < 0) {
			console.log("I'm over the line: top");
			img.style.left = "150px";		
			img.style.top = "150px";			
		} else {
			img.style.top = (top + y) + "px";
		}
	}
	
	function replaceDrake() {
		for(var d = 0; d < drakes.length; d++) {
			clearInterval(moveDrakesIntervals[d]);
			var drake = document.getElementById("Drake404 " + d);
			if(d === 0) {
			drake.src = '../resources/img/cryingDrake.gif'; //used to be deadDrake.gif
			} else {
				drake.src = '../resources/img/cryingDrake.gif';
			}
		}
	}
	
	function alterMainDiv() {
		var mainDiv = document.getElementById("main");
		mainDiv.style.position = "absolute";
		mainDiv.style.left = "30px";
		mainDiv.style.top = "90px";
		
		mainDiv.innerHTML = "<h1>You made Drake cry. w0w.</h1><small>Also you killed Drake. WTF?</small>";
	}

	
	function killDrake() {
		var drakeStatus = document.getElementById("drakeStatus");
		drakeStatus.innerHTML = "<h1 style=\"color:red; position:relative left:92px; top:92px;\">Drake's blood is on your hands</h1>";
		var subElement = document.createElement("h3");
		subElement.innerHTML = "<h1 style=\"color:red; position:relative left:92px; top:92px;\">AND IT WON'T WASH OFF</h1>";
		drakeStatus.appendChild(subElement);

		moveDrakeNote = setInterval(function() {
			move(drakeStatus);
		}, 1000);
		clearInterval(addDrakesInterval);
		replaceDrake();
		alterMainDiv();
	}
	
	function addEventListeners() {
		var noMoreDrake = document.getElementById("noMoreDrake");
		noMoreDrake.addEventListener("click", killDrake, false);
	}

/*drakes = [];
	moveDrakesIntervals = [];
	$(document).ready(function() {
	    var currentUrl = '../resources/img/tinyDrake.gif';
	   
	    addDrakesInterval = setInterval(function() {
	    	var drake = document.createElement("img");
			var drakesLength = drakes.length;
	    	drake.src=currentUrl;
	    	drake.alt="Drake404 " + drakesLength;
	    	drake.title="Drake404 " + drakesLength;
	    	drake.id="Drake404 " + drakesLength;
	    	drake.className="drakeImg";
	    	drake.style.top=Math.random()*600 +"px";
	    	drake.style.left=Math.random()*300 +"px";
	    	drake.style.position="relative";
	    	var mainDiv = document.getElementById("main");
	    	drakes.push(drake);
	    	moveDrakesInterval = setInterval(function() {
	    		move(drake);
	    	}, 300);
	    	moveDrakesIntervals.push(moveDrakesInterval);
	        document.body.insertBefore(drake, mainDiv);
	    }, 300);
	});

	function move(img) {
		var x = 100, y = 30;
		var left, top;
		if (Math.random() < 0.5) {
			x = -x;
		}
		if (Math.random() < 0.5) {
			y = -y;
		}
		left = parseInt(img.style.left);
		top = parseInt(img.style.top);
		if((left + x) > 500 || (left + x) < 0) {
			console.log("I'm over the line: left");
			img.style.left = "150px";		
			img.style.top = "150px";	
		} else{
		img.style.left = (left + x) + "px";
		}
		if((top+y)> 500 || (top + y) < 0) {
			console.log("I'm over the line: top");
			img.style.left = "150px";		
			img.style.top = "150px";			
		} else {
			img.style.top = (top + y) + "px";
		}
	}
	
	function replaceDrake() {
		for(var d = 0; d < drakes.length; d++) {
			clearInterval(moveDrakesIntervals[d]);
			var drake = document.getElementById("Drake404 " + d);
			if(d === 0) {
			drake.src = '../resources/img/deadDrake.gif';
			} else {
				drake.src = '../resources/img/cryingDrake.gif';
			}
		}
	}
	
	function alterMainDiv() {
		var mainDiv = document.getElementById("main");
		mainDiv.style.position = "absolute";
		mainDiv.style.left = "30px";
		mainDiv.style.top = "90px";
		
		mainDiv.innerHTML = "<h1>You made Drake cry. w0w.</h1><small>Also you killed Drake. WTF?</small>";
	}*/