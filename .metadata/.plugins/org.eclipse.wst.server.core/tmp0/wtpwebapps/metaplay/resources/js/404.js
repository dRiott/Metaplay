$(document).ready(function () {
	
	//fix the main text so not moved around by the drakes
	var mainDiv = document.getElementById("main");
	mainDiv.style.position = "absolute";
	mainDiv.style.left = "130px";
	mainDiv.style.top = "65px";
	
	var drakes = [];
	var moveDrakesIntervals = [];
	
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
			img.style.left = "150px";		
			img.style.top = "150px";	
		} else{
		img.style.left = (left + x) + "px";
		}
		if((top+y)> 500 || (top + y) < 0) {
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
	
	function makeDrakeCry() {
		var drakeStatus = document.getElementById("drakeStatus");
		drakeStatus.innerHTML = "<h1 style=\"color:red; position:relative left:92px; top:92px;\">You made Drake cry!</h1>";
		var subElement = document.createElement("h3");
		subElement.innerHTML = "<h1 style=\"color:red; position:relative left:92px; top:92px;\">...How could you??</h1>";
		drakeStatus.appendChild(subElement);

		moveDrakeNote = setInterval(function() {
			move(drakeStatus);
		}, 300);
		
		clearInterval(addDrakesInterval);
		replaceDrake();
		$("#noMoreDrake").hide();
		$("#main").hide();
	}
	
		$("#noMoreDrake").click(makeDrakeCry);
		
		
		$(document.body).css('background-image', 'url(../resources/img/draketennis.gif');
		$(document.body).css('background-repeat', 'repeat');
		
		
});