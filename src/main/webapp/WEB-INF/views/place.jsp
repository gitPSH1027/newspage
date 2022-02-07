<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오시는길</title>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=jjwgp5cb1p"></script>
</head>
<body>
	<main>
		<div class="container pt-3">
		   	<h2 style="text-align: center;">오시는길</h2>
			<div id="map" style="width: 100%; height: 300px;"></div>
		</div>
		<div style="text-align: left; margin: 40px 380px;width: 100%;font-size: 30px;">
			장소 : 서울특별시 관악구 신림로 340 르네상스쇼핑몰 6층 <br>
			버스 : 신림사거리, 신림역 하차 <br>
			지하철 : 신림역 7번출구 앞
		</div>
	</main>
	<script>
	var map = new naver.maps.Map('map', {
	    center: new naver.maps.LatLng(37.48484938219453, 126.93005693999172),
	    zoom: 17
	});

	var marker = new naver.maps.Marker({
	    position: new naver.maps.LatLng(37.48484938219453, 126.93005693999172),
	    map: map
	});
	</script>
</body>
</html>
<%@ include file="footer.jsp"%>