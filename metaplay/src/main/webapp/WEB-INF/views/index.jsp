<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>You're Here! Omg.. You're here?</title>

 	<link rel="stylesheet" href="<spring:url value="/resources/css/reset.css"/>" type="text/css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/index.css"/>" type="text/css" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script>
	var dillaSays = ["Yo WTF? This cracka naked", "Just Blaze, B... Everything is coo.", "I'm a God, not a rapper. PFF Drake", "Fools rush in. Naw'm sayin'?", "Paypal david.riott1@gmail.com $5", "Say what? I'm a hustler bitch."];
	var randomNum = Math.floor(Math.random() * dillaSays.length);
	function fnSetStatus(){document.getElementById("myStatus").innerHTML="<p>"+dillaSays[randomNum]+"</p>"}
	
	
	</script>	
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
	
</head>
<body>
	<div id="wrapper">
		<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>
		<div class=text id=mainTest>
			<h1>W3lcome to the Fr0nt Page</h1>
 			 <button type="button" id="permaRaveButton" class="btn btn-primary btn-lg"
 			 style="background: url(<spring:url value="/resources/img/raveGuy.jpg" />); 
 			 position:absolute; top: 150px; left: 220px; width: 15em; height: 9em; font-size: 30px;">PermaRave</button>
 			 
 			 <button type="button" id="noMoreParty" class="btn btn-primary btn-lg"
 			 style="position:absolute; top: 500px; left: 450px; 
 			 width: 10em; height: 2.5em;">(Too Old For This)</button>
 			 
		</div>
	</div>
	
	<div class="oval-thought-border" id="myStatus" style="position:fixed; left:45%; bottom:40%;"></div>	
	
	<script>
	
	var permaRave = document.getElementById("permaRaveButton");
	permaRave.onclick = function() {
	    var currentColor = '#E5E5E5';
	    intervalId = setInterval(function() {
	        document.body.style.backgroundColor = currentColor;
	        currentColor = currentColor === '#E5E5E5' ? 'black' : '#E5E5E5';
	    }, 200);
	    
		
	};

	var noMoreParty = document.getElementById("noMoreParty");
	noMoreParty.onclick = function() {
		clearInterval(intervalId);
		document.body.style.backgroundColor = '#E5E5E5';
	}
	fnSetStatus()
/* 	
	$("img").mousedown(function() {
		document.body.style.backgroundColor='black';
	})
	$("img").mouseup(function() {
		document.body.style.backgroundColor='#C1E4E0';
	})
	 */
	
	</script>
</body>
</html>