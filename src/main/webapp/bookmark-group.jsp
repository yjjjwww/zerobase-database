<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		BookmarkService bookmarkService = new BookmarkService();
		List<BookmarkGroup> bookmarkGroupList = bookmarkService.getBookmarkGroup();
	%>

	<h1>북마크 그룹</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
		<button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	</div>
	<div>
	<table>
		<thead>
			<tr>
				<th>
					ID
				</th>
				<th>
					북마크 이름
				</th>
				<th>
					순서
				</th>
				<th>
					등록일자
				</th>
				<th>
					수정일자
				</th>
				<th>
					비고
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<%
					if (bookmarkGroupList.size() == 0) {
				%>		
					<td colspan="6">
						정보가 존재하지 않습니다.
					</td>
				<%		
					} else {
						for (BookmarkGroup bg : bookmarkGroupList) {
				%>				
				<tr>
					<td><%=bg.getID() %></td>
					<td><%=bg.getBOOKMARK_NAME() %></td>
					<td><%=bg.getORDER() %></td>
					<td><%=bg.getREGISTER_DATE() %></td>
					<td><%=bg.getEDIT_DATE() %></td>
					<td><a href="bookmark-group-edit.jsp?id=<%=bg.getID() %>">수정</a>  <a href="bookmark-group-delete.jsp?id=<%=bg.getID() %>">삭제</a></td>
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