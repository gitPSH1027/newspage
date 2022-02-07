<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<style>
.mySlides {
  width: 980px;
  height: 400px;
  object-fit: cover;
}
</style>
<body>
	<main>
		<div class="w3-content w3-display-container" style="position: absolute; left: 500px;top: 200px;">
			<img class="mySlides" src="${path }/resources/imgs/coin.jpg" >
			<img class="mySlides" src="${path }/resources/imgs/culture.jpg" >
			<img class="mySlides" src="${path }/resources/imgs/sports.jpg" >
			<img class="mySlides" src="${path }/resources/imgs/경제.png">

			<button class="w3-button w3-black w3-display-left"
				onclick="plusDivs(-1)">&#10094;</button>
			<button class="w3-button w3-black w3-display-right"
				onclick="plusDivs(1)">&#10095;</button>
		</div>
	</main>
	<script>
		var slideIndex = 1;
		showDivs(slideIndex);

		function plusDivs(n) {
			showDivs(slideIndex += n);
		}

		function showDivs(n) {
			var i;
			var x = document.getElementsByClassName("mySlides");
			if (n > x.length) {
				slideIndex = 1
			}
			if (n < 1) {
				slideIndex = x.length
			}
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			x[slideIndex - 1].style.display = "block";
		}
	</script>
</body>
</html>
<%@ include file="footer.jsp"%>