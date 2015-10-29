/**
 * Set up so that on mouseover Avatar, allows upload picture to change.
 */

$(document).ready(function() {
	
	$("#avatarPic").mouseover(allowUpload);
	$("#profileContainer").mouseleave(removeUpload);
	
}); // end document.ready


	function allowUpload() {
		$("#avatarUpload").show(1000);
	}
		
	function removeUpload() {
		$("#avatarUpload").hide(1000);
	}