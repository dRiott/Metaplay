
			
			//Artist Options "Other" Box Hide/Unhide
			var selectOtherAR = document.getElementById("albumArtistName");
			var inputBoxAR = document.getElementById("newAlbumArtistName");
			selectOtherAR.onchange=function(){
				if(selectOtherAR.value=="** New Artist **") {
					inputBoxAR.style.display="inline";
				} else {
					inputBoxAR.style.display="none";	
				}
			}
			
			//all the nonsense for show and hiding add tracks...
			var input12 = document.getElementById("input12");
			var box13 = document.getElementById("13");
			var box14 = document.getElementById("14");
			var box15 = document.getElementById("15");
			var box16 = document.getElementById("16");
			var box17 = document.getElementById("17");
			var box18 = document.getElementById("18");
			var box19 = document.getElementById("19");
			var box20 = document.getElementById("20");
			var box21 = document.getElementById("21");
			var box22 = document.getElementById("22");
			var box23 = document.getElementById("23");
			var box24 = document.getElementById("24");
			var box25 = document.getElementById("25");
			var box26 = document.getElementById("26");
			var box27 = document.getElementById("27");
			var box28 = document.getElementById("28");
			var box29 = document.getElementById("29");
			var box30 = document.getElementById("30");

			input12.onkeyup=function(){
					box13.style.display="inline";
			}
			input12.onclick=function(){
				box13.style.display="inline";
			}
			box13.onkeyup=function(){
				box14.style.display="inline";
			}
			box13.onclick=function(){
				box14.style.display="inline";
			}
			
			box14.onkeyup=function(){
				box15.style.display="inline";
			}
			
			box14.onclick=function(){
				box15.style.display="inline";
			}
			box15.onkeyup=function(){
				box16.style.display="inline";
			}
			box15.onclick=function(){
				box16.style.display="inline";
			}
			box16.onkeyup=function(){
				box17.style.display="inline";
			}
			box16.onclick=function(){
				box17.style.display="inline";
			}
			box17.onkeyup=function(){
				box18.style.display="inline";
			}
			box17.onclick=function(){
				box18.style.display="inline";
			}
			box18.onkeyup=function(){
				box19.style.display="inline";
			}
			box18.onclick=function(){
				box19.style.display="inline";
			}
			box19.onkeyup=function(){
				box20.style.display="inline";
			}
			box19.onclick=function(){
				box20.style.display="inline";
			}
			box20.onkeyup=function(){
				box21.style.display="inline";
			}
			box20.onclick=function(){
				box21.style.display="inline";
			}
			box21.onkeyup=function(){
				box22.style.display="inline";
			}
			box21.onclick=function(){
				box22.style.display="inline";
			}
			box22.onkeyup=function(){
				box23.style.display="inline";
			}
			box22.onclick=function(){
				box23.style.display="inline";
			}
			box23.onkeyup=function(){
				box24.style.display="inline";
			}
			box23.onclick=function(){
				box24.style.display="inline";
			}
			box24.onkeyup=function(){
				box25.style.display="inline";
			}
			box24.onclick=function(){
				box25.style.display="inline";
			}
			box25.onkeyup=function(){
				box26.style.display="inline";
			}
			box25.onclick=function(){
				box26.style.display="inline";
			}
			box26.onkeyup=function(){
				box27.style.display="inline";
			}
			box26.onclick=function(){
				box27.style.display="inline";
			}
			box27.onkeyup=function(){
				box28.style.display="inline";
			}
			box27.onclick=function(){
				box28.style.display="inline";
			}
			box28.onkeyup=function(){
				box29.style.display="inline";
			}
			box28.onclick=function(){
				box29.style.display="inline";
			}
			box29.onclick=function(){
				box30.style.display="inline";
			}
			box29.onkeyup=function(){
				box30.style.display="inline";
			}
			box30.ondblclick=function(){
				alert("Sorry, but only 30 tracks are currently supported. Try separating into multiple albums.");
			}
