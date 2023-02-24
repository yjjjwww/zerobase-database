<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.HistoryService"%> 
<%@page import="wifidb.History"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		HistoryService historyService = new HistoryService();
		
		historyService.deleteHistory(Integer.parseInt(id));
	%>
	
	<h1>위치 히스토리 목록</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	
	<div>
		<p>히스토리가 삭제되었습니다.</p>
		<button onclick="location.href='history.jsp'">돌아가기</button>
	</div>
</body>
</html>