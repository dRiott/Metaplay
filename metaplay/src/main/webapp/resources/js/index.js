$(document).ready(function () {
    var timeoutId;
    var permaRave = $("#permaRaveButton");
    var noMoreParty = $("#noMoreParty");
    var searchLink = $("#searchLink");
    var drContainer = $(".drContainer");

    //set the background image
    $('body').css('background-image', 'url(http://metaplay.me/resources/img/fourtet.gif)');

    //fix the title to align with the search nav bar item just like on pages that include the footer.
    $(drContainer).css("padding-left", $(searchLink).offset().left+12);
    $(permaRave).css("left", $(searchLink).offset().left+12);
    $(noMoreParty).css("left", $(searchLink).offset().left+220);

    //redo the alignment on window resize.
    $(window).resize(function () {
        $(drContainer).css("padding-left", $(searchLink).offset().left+12);
        $(permaRave).css("left", $(searchLink).offset().left+12);
        $(noMoreParty).css("left", $(searchLink).offset().left+220);
    });

    //start and stop the rave!
    $(permaRave).click(startRave);
    $(noMoreParty).click(stopRave);

    //que the removal of the message if it is present.
    if(typeof $("#messageDiv").html() !== 'undefined') {
        timeoutId = setTimeout(hideDiv, 2500);
    }

}); //end document.ready


function stopRave() {
    for (var i = 1; i < 999; i++) {
        window.clearInterval(i);
    }
    $(document.body).css("backgroundColor", '#E5E5E5');
}

function startRave() {
    var currentColor = '#E5E5E5';
    intervalId = setInterval(function() {
        $(document.body).css("backgroundColor", currentColor);
        currentColor = currentColor === '#E5E5E5' ? 'black' : '#E5E5E5';
    }, 150);
}

function hideDiv() {
    $("#messageDiv").slideUp(500);
    clearTimeout(timeoutId);
}


