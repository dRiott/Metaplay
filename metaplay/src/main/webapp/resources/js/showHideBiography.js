/**
 * 
 */

$(document).ready(function () {

	for(var i = $("#artistsTable tr").length; i-=1;) {
		$("#showButton"+i).click(function () {
			console.log("Button Clicked.");
			console.log($("#artistsTable tr td span").val());
		})
	}
	
	
//	$("tr").each(function() {
////		  $this = $(this)
////		  var value = $this.find("span.value").html();
////		  var quantity = $this.find("input.quantity").val();
//		  console.log("GET");
//		});
//	
	
}); //end document.ready

