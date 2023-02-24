<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.BookmarkService"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
	
		BookmarkService bookmarkService = new BookmarkService();
		bookmarkService.deleteBookmark(Integer.parseInt(id));
	%>
	
	<h1>북마크 삭제</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	
	<div>
		<p>북마크 삭되었습니다.</p>
		<button onclick="location.href='bookmark-list.jsp'">돌아가기</button>
	</div>
</body>
</html>