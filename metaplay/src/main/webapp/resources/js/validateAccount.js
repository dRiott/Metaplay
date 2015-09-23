/**
 * 
 */

function getSubmitButton () {
	return document.getElementById("loginButton");
}

function alertSubmitButton () {
	getSubmitButton().onclick = "alert('Login to Music!');"
}

function fnAlertLogin() {
		alert("Login to Music");
	}