	$(document).ready(function(){
		var countTEXT = 0;
		var countJSON = 0;
		var json;
		
		//get JSON data
		$.ajax({
	        url: "/metaplay/rest/artist",
	        method: "get",
	        dataType: "json",
	        success: successfulJSON
		}).fail(function(data) {
	           console.log("AJAX JSON load failed");
	       });
		
		//JSON functions
		function successfulJSON(returnedData, status) {
			console.log("in success JSON");
			console.log(returnedData);
			json = returnedData; //set global variable to access on click
		}
		
		//text return
		function getMyName() {
	        console.log("in getMYName");
			$.ajax({
		        url: "/metaplay/rest/artist/?id="+countTEXT, 
		        method: "get", 
		        dataType: "text",
		        success: successfulAJAX
			}).fail(function(data) {
	            console.log("AJAX load failed");
	        });
	        countTEXT++;
		}
	
		//TEXT functions
		function successfulAJAX(returnedData, status) {
			console.log("in success TEXT");
			$('#hello').html("I am " + returnedData.name);
		}
		
		function successfulSearch(returnedData, status) {
			console.log("in successfulSearch method");
			console.log("Returned data (size) : " + returnedData.length);
			console.log("One item of returnedData (.name): " + returnedData[0].name);
			var table = $('#resultsTable');
			console.log("Got the table! Rows.length: " + table.length);
			for(var i=0; i<returnedData.length; i++){
				 var row = $("<tr>");
				 var url = "<a href='/metaplay/browse/artist/"+returnedData[i].id+"'>"+returnedData[i].name+"</a>"
				 if(!$("#resultsTable tr > a").is(":contains("+returnedData[i].name+")")) {
					 row.append($(url));
				 }
				 $("#resultsTable tbody").append(row);
			}
		}
		function errorFunction(returnedData, status) {
			console.log("SOMETHING WENT WRONG");
		}
	
/// ************** *************** Event Handlers ************** ************** ***********
			$('#button').click(function() {
		        console.log("in button");
				getMyName();
		    });
			
			$('#button2').click(function() {
		        console.log("in button2");
		        console.log(json);
 				$('#hello2').html("Hello my name is " + json.name + " " + json.id);
 	   			countJSON++;
			});	
			
			
			$('#searchInput').on("keyup", function(e) {
				var field = e.target;
				if(field.value.length>0) {
					$.ajax({
						headers: { 
					        'Accept': 'application/json',
					        'Content-Type': 'application/json' 
					    },
						url: '/metaplay/rest/artist?query='+field.value,
						type: 'POST',
						dataType: 'json',
//						data: 'query='+field.value,
						success: successfulSearch,
						error: errorFunction
					})
				}
				
			});
			
		}); //end document.ready