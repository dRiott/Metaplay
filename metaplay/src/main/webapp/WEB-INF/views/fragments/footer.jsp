<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="containter">
	<div class="container-fluid">
	
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<small style="color: grey;">&copy David Riott, 2015</small>
	
	</div>
</div>

<script>
$(document).ready(
		function () {
			
		var backgrounds = ["fourtet.gif", "j2.gif", "daedelus.gif", "dilla.gif", "drake.gif", "fatherjohn.gif", "francisdrake.gif", "umo.gif", "toroymoi.gif",
		                   "kendrick.gif", "rashad.gif", "thomyorke.gif", "tupac.gif", "wiz.gif", "talib.gif", "mosdef.gif", "eminem.gif", "goldpanda.gif",
		                   "tameimpala.gif", "kurtcobain.gif", "earlsweatshirt.gif", "burial.gif", "aphex.gif", "chancetherapper.gif", "deadmau5.gif", 
		                   "davidbowie.gif", "goldpanda.gif", "yoshimi.gif", "youthlagoon.gif", "xxyyxx.gif", "actress.gif", "tuneyards.gif", "flyinglotus.gif",
		                   "unicorns.gif", "tallestman.gif", "soad.gif"
						   ];
			
		var randomNum = Math.floor(Math.random() * backgrounds.length);
			$('body').css('background-image', 'url(http://localhost:8080/metaplay/resources/img/' + backgrounds[randomNum] + ')');
		}
);
</script>