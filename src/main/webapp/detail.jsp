<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.WifiService"%> 
<%@page import="wifidb.WifiInformation"%> 
<%@page import="wifidb.BookmarkService"%> 
<%@page import="wifidb.BookmarkGroup"%>
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
		width: 50%;
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
		height: 35px;
	}
	
</style>
</head>
<body>
	<%
		String MRG_NO = request.getParameter("MRG_NO");
		WifiService wifiService = new WifiService();
		WifiInformation wifi = wifiService.getSelectedWifi(MRG_NO);
		BookmarkService bookmarkService = new BookmarkService();
		List<BookmarkGroup> bookmarkGroupList = bookmarkService.getBookmarkGroup();
	%>
	<h1>와이파이 정보 구하기</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
		<form method="post" action="./bookmark-add-result.jsp?mrg-no=<%=MRG_NO %>">
		<select id="bookmark" name="bookmark">
			<option value='' selected>북마크 그룹 이름 선택</option>
			<%
				for(BookmarkGroup bookmarkGroup : bookmarkGroupList) {
			%>
				<option value=<%=bookmarkGroup.getID() %>>
					<%=bookmarkGroup.getBOOKMARK_NAME() %>
				</option>
			<%		
				}
			%>
		</select>
		<button>북마크 추가하기</button>
		</form>
		
	</div>
	<div>
		<table>
			<tr>
				<th>
					거리(KM)
				</th>
				<td>
					<%=wifi.getDistance() %>
				</td>
			</tr>
			<tr>
				<th>
					관리번호
				</th>
				<td>
					<%=wifi.getMRG_NO() %>
				</td>
			</tr>
			<tr>
				<th>
					자치구
				</th>
				<td>
					<%=wifi.getWRDOFC() %>
				</td>
			</tr>
			<tr>
				<th>
					와이파이명
				</th>
				<td>
					<%=wifi.getMAIN_NM() %>
				</td>
			</tr>
			<tr>
				<th>
					도로명주소
				</th>
				<td>
					<%=wifi.getADRES1() %>
				</td>
			</tr>
			<tr>
				<th>
					상세주소
				</th>
				<td>
					<%=wifi.getADRES2() %>
				</td>
			</tr>
			<tr>
				<th>
					설치위치(층)
				</th>
				<td>
					<%=wifi.getINSTL_FLOOR() %>
				</td>
			</tr>
			<tr>
				<th>
					설치유형
				</th>
				<td>
					<%=wifi.getINSTL_TY() %>
				</td>
			</tr>
			<tr>
				<th>
					설치기관
				</th>
				<td>
					<%=wifi.getINSTL_MBY() %>
				</td>
			</tr>
			<tr>
				<th>
					서비스구분
				</th>
				<td>
					<%=wifi.getSVC_SE() %>
				</td>
			</tr>
			<tr>
				<th>
					망종류
				</th>
				<td>
					<%=wifi.getCMCWR() %>
				</td>
			</tr>
			<tr>
				<th>
					설치년도
				</th>
				<td>
					<%=wifi.getCNSTC_YEAR() %>
				</td>
			</tr>
			<tr>
				<th>
					실내외구분
				</th>
				<td>
					<%=wifi.getINOUT_DOOR() %>
				</td>
			</tr>
			<tr>
				<th>
					WIFI접속환경
				</th>
				<td>
					<%=wifi.getREMARS3() %>
				</td>
			</tr>
			<tr>
				<th>
					X좌표
				</th>
				<td>
					<%=wifi.getLAT() %>
				</td>
			</tr>
			<tr>
				<th>
					Y좌표
				</th>
				<td>
					<%=wifi.getLNT() %>
				</td>
			</tr>
			<tr>
				<th>
					작업일자
				</th>
				<td>
					<%=wifi.getWORK_DTTM() %>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>