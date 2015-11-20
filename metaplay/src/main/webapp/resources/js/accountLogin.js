$(document).ready(function () {
    //hides the messages div after 2500ms if the logout message appears
    var timeoutId;

    if($("#errorDiv").length) {
        timeoutId = setTimeout(hideDiv, 2500);
    }

}); //end document.ready

function hideDiv() {
    console.log("hideDiv method called");
    $("#errorDiv").slideUp(500);
    clearTimeout(timeoutId);
}
