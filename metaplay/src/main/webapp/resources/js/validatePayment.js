			var nameOnCard = "James Brown";
			var cardNumber = "1234-1235-1236-1237";
			var cardType = "VISA";
			var expDate = "4/2/2030";
			var paymentAmount = "5000.00";

			function validateTransaction(nameOnCard, cardNumber, cardType,
					expDate, paymentAmount) {
				alert("Ready to validate!??");
			}

			alert("Before changing values");

			var nameOnCardObj = document.getElementById("nameOnCard");
			nameOnCardObj.value = nameOnCard;

			var cardNumberObj = document.getElementById("cardNumber");
			cardNumberObj.value = cardNumber;

			var cardExpDateObj = document.getElementById("cardExpDate");
			cardExpDateObj.value = expDate;

			var amountObj = document.getElementById("amount");
			amountObj.value = paymentAmount;
			
			function validate(form) {
				var nameOnCardValue = document.getElementById("nameOnCard").value;
				var cardNumberValue = document.getElementById("cardNumber").value;
				var cardExpDateValue = document.getElementById("cardExpDate").value;
				var amountValue = document.getElementById("amount").value;
				var errMessage = "";
				
				if(nameOnCardValue.length == 0) {
					errMessage += "Please enter your name\r\n";
				}
				if (cardNumberValue.length == 0) {
					errMessage += "Please enter your card number\r\n";					
				}
				if(cardExpDateValue.length == 0) {
					errMessage += "Please enter your card date\r\n";					
				}
				if(amountValue.length == 0) {
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
			
			var selectOther = document.getElementById("cardTypeOptions");
			var inputBox = document.getElementById("optionCardTypeInput");
			selectOther.onchange=function(){
				if(selectOther.value=="Other") {
					inputBox.style.display="inline";
				} else {
					inputBox.style.display="none";	
				}
			}
			
			function fixOtherValue() {
				if(selectOther.value == "Other") {
					selectOther.value="";	
				}
			}
			
			
			