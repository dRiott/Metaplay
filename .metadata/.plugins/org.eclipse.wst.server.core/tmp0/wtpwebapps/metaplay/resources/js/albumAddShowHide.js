
$(document).ready(function () {

	//Artist Options "Other" Box Hide/Unhide
	var selectOtherAR = $("#albumArtistName");
	selectOtherAR.change(function(){
		var inputBoxAR = $("#newAlbumArtistName");
		var artistAddButton = $("#newAlbumArtistAddButton");
		if(selectOtherAR.val()=="** New Artist **") {
			inputBoxAR.show();
			artistAddButton.show();
		} else {
			inputBoxAR.hide();
			artistAddButton.hide();
		}
	});
	
	// new record label show/hide
	var selectOtherRL = $("#recordLabelName");
	selectOtherRL.change(function(){
		var inputBoxRL = $("#newRecordLabelName");
		var recordLabelLocationDiv = $("#newRecordLabelLocation");
		var hr1 = $("#recordLabelHr1");
		var hr2 = $("#recordLabelHr2");
		var newCountryDiv = $("#newCountryDiv");
		
		if(selectOtherRL.val()=="** New Record Label **") {
			inputBoxRL.show();
			recordLabelLocationDiv.show();
			hr1.show();
			hr2.show();
			newCountryDiv.show();
		} else {
			inputBoxRL.hide();
			recordLabelLocationDiv.hide();
			hr1.hide();
			hr2.hide();
			newCountryDiv.hide();
		}
	}); //end record label on change
	
	var rlCountries = $("#rlCountries");
	if(rlCountries.val()=="** New Country **") {
		console.log("new country");
		
	}
	
	rlCountries.change(function() {
		var newCountry = $("#rlCountry");
		var rlState = $("#rlState");
		var city = $("#recordLabelCity");
		
		if(rlCountries.val()=="United States") {
			rlState.show();
			newCountry.hide();				
			city.attr("placeholder", "Birmingham");
		} else if (rlCountries.val()=="** New Country **") {
			city.focus();
			city.blur(function () {
				newCountry.focus()
			})
			
			rlState.hide();
			newCountry.attr("placeholder", "England");
			newCountry.show();
			city.attr("placeholder", "London");
		} else {
			rlState.hide();
			newCountry.hide();				
		}
	});
	
	
	//gets rid of the Album Length zeros on load
	$("#albumMinutes").val("");
	$("#albumSeconds").val("");
	
	//gets rid of the zeros on load
	for(var i = 1; i<31; i++){
		var minutesId = "minutes" + i;
		var minutes = document.getElementById(minutesId);
		minutes.value = "";
		var secondsId = "seconds" + i;
		var seconds = document.getElementById(secondsId);
		seconds.value = "";
		var bpmId = "bpm" + i;
		var bpm = document.getElementById(bpmId);
		bpm.value = "";
	}
	
}); // end document.ready			

//repopulates the zeros to prevent number format error
function ensureVoidsAreZeroes() {
	var bpms = $(".bpm");
	for(var i = 1; i<31; i++){
		var minutesId = "minutes" + i;
		var minutes = document.getElementById(minutesId);
		if(minutes.value == "") {
			minutes.value = 0;
		};
		var secondsId = "seconds" + i;
		var seconds = document.getElementById(secondsId);
		if(seconds.value == "") {
			seconds.value = 0;
		};
		var bpmId = "bpm" + i;
		var bpm = document.getElementById(bpmId);
		if(bpm.value == "") {
			bpm.value = 0;
		};
	}
	
	var albumMinutes = document.getElementById("albumMinutes");
	if(albumMinutes.value == "") {
		albumMinutes.value = 0;
	};
	var albumSeconds = document.getElementById("albumSeconds");
	if(albumSeconds.value == "") {
		albumSeconds.value = 0;
	};
}

//scrolls the window down as a new track is shown.
function reveal(id) {
	id += 1;
	element = document.getElementById(id);
	element.style.display="inline";
	if(id>2) {
//		window.scrollTo(0,document.body.scrollHeight);
		scrollToElement(element, 1000);
	}
}

var scrollToElement = function(element, ms){
    var speed = (ms) ? ms : 600;
    $('html,body').animate({
        scrollTop: $(element).offset().top
    }, speed);
}

// specify id of element and optional scroll speed as arguments
