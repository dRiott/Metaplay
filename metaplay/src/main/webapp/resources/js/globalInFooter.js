/**
 * Sets the background to one of the pictures in the backgrounds array.
 * Focuses the cursor to the first empty form input
 */

$(document).ready(function () {

	//source images to pick from when setting the background image with JavaScript directly below this array.
	var backgrounds = ["actress.gif", "Antony.gif", "aphex.gif", "builttospill.gif", "burial.gif", "chancetherapper.gif",
	                   "daedelus.gif", "dandeacon.gif", "davidbowie.gif", "deadmau5.gif", "dilla.gif", "drake.gif", 
	                   "earlsweatshirt.gif", "eminem.gif",
	                   "fatherjohn.gif", "flyinglotus.gif", "flylo.gif", "fourtet.gif", "francisdrake.gif", 
	                   "goldpanda.gif", "gorillaz.gif",
	                   "kalkbrenner.gif", "kendrick.gif", "kurtcobain.gif",
	                   "melloncollie.gif", "miles.gif", "mosdef.gif", 
	                   "rashad.gif", "sbtrkt.gif", "slowmagic.gif",
	                   "talib.gif", "tallestman.gif", "tameimpala.gif", "thomyorke.gif", "toroymoi.gif", "tuneyards.gif", "tupac.gif",  
	                   "umo.gif", "unicorns.gif", "wiz.gif",
	                   "xxyyxx.gif", "yoshimi.gif", "youthlagoon.gif"
					   ];

	//pick a random background image and set it.
	var randomNum = Math.floor(Math.random() * backgrounds.length);
	$('body').css('background-image', 'url(http://metaplay.me/resources/img/' + backgrounds[randomNum] + ')');
	

	//sets the container's padding, so that it aligns with the search nav bar item.
	$(".drContainer").css("padding-left", $("#searchLink").offset().left+12);

	//boostrap adds a -30px margin-left and margin-right.. I don't want that to happen.
	$(".drRow").css("margin-left", 0).css("margin-right", 0);

	//removes the delayedReveal class from the body, which shows the body's contents (after all of the positioning has occurred).
	$(document.body).removeClass("delayedReveal");

	//focus the cursor to the first empty form input
	var input = $("*").find('input[type=text],textarea,select').filter(':visible:first');
	if(input.length) {
        input.focus();
	}

}); // end document.ready

//on resize, sets the container's padding, so that it aligns with the search nav bar item.
$(window).resize(function () {
	$(".drContainer").css("padding-left", $("#searchLink").offset().left+12);
});

	
	
	
	
	