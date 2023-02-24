<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div {
		margin-bottom: 10px;
	}
	table {
		width: 100%;
		border-collapse: collapse;
	}
	th {
		border: solid 1px gray;
		background-color: #04AA6D;
		color: white;
		text-align: center;
	}
	td {
		border: solid 1px gray;
		text-align: center;
		width: 100%;
		height: 70px;
	}
	
</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
		<label>LAT: <input type="text" value="0.0" id = "lat" size="20"></label>
		<label>, LNT: <input type="text" value="0.0" id = "lnt" size="20"></label>
		<button onclick = "changeLatLNT()">내 위치 가져오기</button>
		<button  onclick="getWifi()">근처 WIFI 정보 보기</button>
	</div>
	<div>
	<table>
		<thead>
			<tr>
				<th>
					거리(KM)
				</th>
				<th>
					관리번호
				</th>
				<th>
					자치구
				</th>
				<th>
					와이파이명
				</th>
				<th>
					도로명주소
				</th>
				<th>
					상세주소
				</th>
				<th>
					설치위치(층)
				</th>
				<th>
					설치유형
				</th>
				<th>
					설치기관
				</th>
				<th>
					서비스구분
				</th>
				<th>
					망종류
				</th>
				<th>
					설치년도
				</th>
				<th>
					실내외구분
				</th>
				<th>
					WIFI접속환경
				</th>
				<th>
					X좌표
				</th>
				<th>
					Y좌표
				</th>
				<th>
					작업일자
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="17">
					위치 정보를 입력한 후에 조회해 주세요.
				</td>
			</tr>
		</tbody>		
	</table>
	</div>
</body>
<script>
	function changeLatLNT() {
		
		navigator.geolocation.getCurrentPosition(
			function(position) {
				var x = position.coords.latitude;
				var y = position.coords.longitude;
				document.getElementById("lat").value = x;
				document.getElementById("lnt").value = y;
			}, 
		);
	}
	
	function getWifi() {
		var lat = document.getElementById("lat").value;
		var lnt = document.getElementById("lnt").value;
		
		location.href = "wifi.jsp?lat=" + lat + "&lnt=" + lnt;
	}
	
</script>
</html>