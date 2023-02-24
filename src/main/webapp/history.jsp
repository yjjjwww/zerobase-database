<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.HistoryService"%> 
<%@page import="wifidb.History"%>
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

		height: 50px;
	}
	
</style>
</head>
<body>
	
	<%
		HistoryService historyService = new HistoryService();
		List<History> historyList = historyService.getHistory();
	%>
	
	<h1>위치 히스토리 목록</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
	<table>
		<thead>
			<tr>
				<th>
					ID
				</th>
				<th>
					X좌표
				</th>
				<th>
					Y좌표
				</th>
				<th>
					조회일자
				</th>
				<th>
					비고
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					if (historyList.size() == 0) {
				%>		
					<td colspan="5">
						정보가 존재하지 않습니다.
					</td>
				<%		
					} else {
						for (History history : historyList) {
				%>			
				<tr>	
					<td><%=history.getId() %></td>
					<td><%=history.getLat() %></td>
					<td><%=history.getLnt() %></td>
					<td><%=history.getHistory_date() %></td>
					<td><button onclick="location.href='deleteHistory.jsp?id=<%=history.getId()%>'">삭제</button></td>
				</tr>
				<%			
						}
					}
				%>				
			</tr>
		</tbody>		
	</table>
	</div>
</body>
</html>