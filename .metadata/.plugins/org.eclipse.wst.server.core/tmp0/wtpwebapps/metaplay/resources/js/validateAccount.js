/**
 * 
 */

alert("HEY!");

function validatePassword () {
	var password1 = document.getElementById("account-password").value;
	var password2 = document.getElementById("account-passwordConfirm").value;
	var mainRow = document.getElementById("mainRow");

	
	if(password1!==password2) {
		alert("inside if");
		var node = document.getElementById('errorWritingSpace');
		var newNode = document.createElement('h3');
		newNode.style["color"]="red";
		newNode.appendChild(document.createTextNode('(!) The Passwords Must Match.'));
		node.appendChild(newNode);
		return false;

	} else {
		return true;
	}
}
