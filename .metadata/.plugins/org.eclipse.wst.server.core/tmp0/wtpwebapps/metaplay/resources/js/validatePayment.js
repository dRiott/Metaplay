

$(document).ready(function () {			
	$("#amount").val("");
	
	alert("Before changing values");
	
	$("#nameOnCard").attr("placeholder", "James Brown");
	$("#cardNumber").attr("placeholder", "1234-1235-1236-1237");
	$("#cardExpDate").attr("placeholder", "4/2/2030");
	$("#amount").attr("placeholder", "5000.00");
	
	function validate(form) {
		var errMessage = "";

		if($("#nameOnCard").val().length === 0) {
			errMessage += "Please enter your name\r\n";
		}
		if ($("#cardNumber").val().length === 0) {
			errMessage += "Please enter your card number\r\n";					
		}
		if($("#cardExpDate").val().length === 0) {
			errMessage += "Please enter your card date\r\n";					
		}
		if($("#amount").val().length === 0) {
			errMessage += "Please enter the amount due";					
		}
		if(errMessage.length >0) {
			alert(errMessage);
			return false;					
		} else {
			alert("Thank you for your order!");
			return true;
		}
	}
	
	var selectCardType = $("#cardTypeOptions");
	var newCardTypeInput = $("#optionCardTypeInput");
	
	selectCardType.change(function(){
		if(selectCardType.val()=="Other") {
			newCardTypeInput.show();
		} else {
			newCardTypeInput.hide();
		}
	});
	
}); //end document.ready			
			