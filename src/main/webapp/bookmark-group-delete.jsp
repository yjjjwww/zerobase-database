<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="wifidb.BookmarkService"%> 
<%@page import="wifidb.BookmarkGroup"%>
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
		String id = request.getParameter("id");
		BookmarkService bookmarkService = new BookmarkService();
		BookmarkGroup bookmarkGroup = bookmarkService.getOneBookmarkGroup(Integer.parseInt(id));
	%>

	<h1>북마크 그룹 삭제</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
		<p>북마크 그룹을 삭제하시겠습니까?</p>
	</div>
	<div>
		<table>
			<tr>
				<th>북마크 이름</th>
				<td><input type="text" value="<%=bookmarkGroup.getBOOKMARK_NAME() %>" id = "name" size="20" disabled></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" value="<%=bookmarkGroup.getORDER() %>" id = "order" size="20" disabled></td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="bookmark-group.jsp">돌아가기</a> | <button onclick="deleteBookmarkGroup(<%=id %>)">삭제</button>
				</td>
			</tr>
		</table>
	</div>
</body>
<script>
	
	function deleteBookmarkGroup(id) {
		
		location.href = "bookmark-group-delete-result.jsp?id=" +id;
	}
	
</script>
</html>