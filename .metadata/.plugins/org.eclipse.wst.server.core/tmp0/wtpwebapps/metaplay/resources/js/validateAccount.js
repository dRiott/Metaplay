
	
	function validatePassword () {
		var password1 = document.getElementById("password").value;
		var password2 = document.getElementById("confirmPassword").value;
		
		if(password1!==password2) {
			var node = document.getElementById('errorWritingSpace');
			var newNode = document.createElement('h3');
			newNode.style["color"]="red";
			newNode.appendChild(document.createTextNode('(!) The Passwords Must Match.'));
			node.appendChild(newNode);
			return false;
		}
	}
