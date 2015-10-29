/**
 * This grabs the Accountname to do an ajax call to get the id for the Profile dropdown link under the Accountname header item.
 */
$(document).ready(function(){
		var accountname = $('#accountname').attr('value');

		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			url: "/metaplay/rest/account?query="+accountname, 
			method: "post", 
			dataType: "json",
			success: successfulAccountGrab,
			error: errorFunction
		})
		
		function errorFunction(returnedData, status) {
			console.log("Accountname Grab Error, Data: " + returnedData);
			console.log("Accountname Grab Error, Status: " + status)
		}
		
		function successfulAccountGrab(returnedData, status) {
			var url = "<spring:url value='/account/"+returnedData.id+"'/>";
			$('#targetLink').attr("href", url);
		}
		
}); //end document.ready