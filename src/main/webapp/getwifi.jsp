<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.WifiService"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		WifiService ws = new WifiService();
		int totalCount = ws.getTotalCount();
		ws.insertWifi(totalCount);
	%>

	<h1><%=totalCount %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
	<a href="home.jsp">홈으로 가기</a>

</body>
</html>