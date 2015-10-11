			
			function showHideBiography() {
				var hiddenSpan = document.getElementById("hiddenBiography");
				console.log(hiddenSpan.innerHTML);
				
				var moreButton = document.getElementById("showButton");
				console.log("More Button InnerHTML: " + moreButton.innerHTML);
			
				var lessButton = document.getElementById("hideButton");
				console.log("Less Button InnerHTML: " + lessButton.innerHTML);

				moreButton.onclick=function() {
					hiddenSpan.style.display="inline";
					lessButton.style.display="inline";
					moreButton.style.display="none";
				}
				
				lessButton.onclick=function() {
					hiddenSpan.style.display="none";
					moreButton.style.display="inline";
					lessButton.style.display="none";
				}
			}
