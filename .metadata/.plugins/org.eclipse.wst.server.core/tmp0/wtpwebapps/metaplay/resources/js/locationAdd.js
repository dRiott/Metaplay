/**
 * Location Options "** New Country **" Show/Hide
 */

$(document).ready(function () {

	var selectCountry = $("#countries");
	var statesDiv = $("#location-state-div");
	var newCountryName = $("#newCountryName");
	
	if(selectCountry.val()=="** New Country **") {
		newCountryName.show();
		statesDiv.hide();
	}
	
	selectCountry.change(function(){
		var selectCountryValue = selectCountry.val();

		if(selectCountryValue=="** New Country **") {
			$("#location-city").attr("placeholder", "London");
			$("#location-new-country").attr("placeholder", "England");

			$("#location-city").focus();
			$("#location-city").blur(function () {
				$("#location-new-country").focus()
			})
		
			newCountryName.css("display", "inline");
			statesDiv.css("display", "none");
			
		} else if (selectCountryValue=="United States") {
			$("#location-city").attr("placeholder", "Tuscaloosa");
			statesDiv.css("display", "inline");
			newCountryName.css("display", "none");
		} else {
			newCountryName.css("display", "none");				
		}
	}); //end selectCountry.change()
	
}); //end document.ready
