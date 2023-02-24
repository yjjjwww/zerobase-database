<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.HistoryService"%> 
<%@page import="wifidb.WifiService"%> 
<%@page import="wifidb.WifiInformation"%> 
<%@page import="java.util.List"%>
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
		height: 70px;
	}
	
</style>
</head>
<body>
	
	<%
		String lat = request.getParameter("lat");
		String lnt = request.getParameter("lnt");
		
		HistoryService hs = new HistoryService();
		hs.registerHistory(Double.parseDouble(lat), Double.parseDouble(lnt));
		
		WifiService wifiService = new WifiService();
		List<WifiInformation> wifiList = wifiService.getWifiByDistance(Double.parseDouble(lat), Double.parseDouble(lnt));
		
	%>

	<h1>와이파이 정보 구하기</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
		<label>LAT: <input type="text" value="<%=lat %>" id = "lat" size="20"></label>
		<label>, LNT: <input type="text" value="<%=lnt %>" id = "lnt" size="20"></label>
		<button>내 위치 가져오기</button>
		<button  onclick="location.href='wifi.jsp'">근처 WIFI 정보 보기</button>
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
				
				<%		
						for (WifiInformation wifi : wifiList) {
				%>			
				<tr>	
					<td><%=wifi.getDistance() %></td>
					<td><%=wifi.getMRG_NO() %></td>
					<td><%=wifi.getWRDOFC() %></td>
					<td>
						<a href="detail.jsp?MRG_NO=<%=wifi.getMRG_NO() %>">
							<%=wifi.getMAIN_NM() %>
						</a>
					</td>
					<td><%=wifi.getADRES1() %></td>
					<td><%=wifi.getADRES2() %></td>
					<td><%=wifi.getINSTL_FLOOR() %></td>
					<td><%=wifi.getINSTL_TY() %></td>
					<td><%=wifi.getINSTL_MBY() %></td>
					<td><%=wifi.getSVC_SE() %></td>
					<td><%=wifi.getCMCWR() %></td>
					<td><%=wifi.getCNSTC_YEAR() %></td>
					<td><%=wifi.getINOUT_DOOR() %></td>
					<td><%=wifi.getREMARS3() %></td>
					<td><%=wifi.getLAT() %></td>
					<td><%=wifi.getLNT() %></td>
					<td><%=wifi.getWORK_DTTM() %></td>				
				</tr>
				<%			
					}
				%>				
			</tr>
		</tbody>
	</table>
	</div>
</body>
</html>