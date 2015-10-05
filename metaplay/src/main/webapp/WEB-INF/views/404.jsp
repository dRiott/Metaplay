<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
	drakes = [];
	$(document).ready(function() {
		alert("GO!");
	    var currentUrl = '../resources/img/drake.gif';
	   
	    intervalId = setInterval(function() {
	    	var drake = document.createElement("img");
	    	drakes.push(drake);
			var drakesLength = drakes.length;
	    	drake.src=currentUrl;
	    	drake.alt="Drake404 " + drakesLength;
	    	drake.title="Drake404 " + drakesLength;
	    	drake.className="drakeImg";
	    	drake.style.top="92px";
	    	drake.style.left="92px";
	    	var mainDiv = document.getElementById("main");
	        document.body.insertBefore(drake, mainDiv);
	        setInterval(function() { moveDrake(drake) },20)
	    }, 1000);
	});
	
	var noMoreDrake = document.getElementById("noMoreDrake");
	
	noMoreDrake.addEventListener("click", function(){killDrake()}, false);

	function killDrake() {
		alert("drake about to be annihilated");
		document.getElementById("myStatus").innerHTML="<h2>Drake's blood is on your hands</h2>";
		//clearInterval(intervalId);
	}
	
	function moveDrake(img) {
			var x=2,y=2;
			var left, top;
			if (Math.random() < 0.5) {
				x = -x;
			}
			if (Math.random() < 0.5) {
				y = -y;
			}
			left = parseInt(img.style.left);
			top = parseInt(img.style.top);
			img.style.left = (left + x) + "px";
			img.style.top = (top + y) + "px";
		}
		
		
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Playlist Manager</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	

</head>
<body>
	
	<jsp:include page="../views/fragments/landingPageFragment.jsp"></jsp:include>

	<div class="container" id="main">
	<h1>This page does not exist yet!</h1>
	Rome was not built in a day. SrslyTHo
	HonestDad404z
	</div>
	
	
	<div id = "drakeButtonDiv">
		<button type="button" id="noMoreDrake" class="btn btn-primary btn-lg">Bye Bye Drake</button>
	</div>
	<br/><br/><br/>	
	<div id="myStatus" style="position:fixed; left:45%; bottom:40%;">Bugs!</div>	
	<jsp:include page="../views/fragments/footer.jsp"></jsp:include>

</body>
</html>