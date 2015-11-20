$(document).ready(function() {

    /* SET THE BACKGROUND, FIX THE MARGINS, SHOW THE BODY */
    $('body').css('background-image', 'url(http://metaplay.me/resources/img/sbtrkt.gif)');

    //sets the container's padding, so that it aligns with the search nav bar item.
    $(".drContainer").css("padding-left", $("#searchLink").offset().left+12);

    //boostrap adds a -30px margin-left and margin-right.. I don't want that to happen.
    $(".drRow").css("margin-left", 0).css("margin-right", 0);

    //shows the body after margins have been fixed.
    $(document.body).removeClass("delayedReveal");


    /* CHOOSE WHETHER TO USE PDF.JS TO RENDER PDF RESUME OR JUST USE OBJECT IF IN CHROME */
    // Browser Specific Characteristic:
    //Chrome: The global chrome object, containing properties including a documented chrome.webstore object.
    var isOpera = !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
    var isChrome = !!window.chrome && !isOpera;

    if (isChrome) {
        $("#pdfDiv").show();
    } else {
        $("#the-canvas").show();
        PDFJS.getDocument('http://localhost:8080/resources/pdf/Riott_David_Nov2015.pdf').then(function(pdf) {
            pdf.getPage(1).then(function(page) {
                var scale = 1.3;
                var viewport = page.getViewport(scale);

                var canvas = document.getElementById('the-canvas');
                var context = canvas.getContext('2d');
                canvas.height = viewport.height;
                canvas.width = viewport.width;

                var renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };
                page.render(renderContext);

            }); //end pdf.getPage
        }); //end PDFJS
    } //end else

}); // end document.ready

//on resize, sets the container's padding, so that it aligns with the search nav bar item.
$(window).resize(function () {
    $(".drContainer").css("padding-left", $("#searchLink").offset().left+12);
});