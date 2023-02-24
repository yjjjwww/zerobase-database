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
	<h1>북마크 그룹 추가</h1>
	<div>
		<p><a href="home.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="getwifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">북마크 보기</a> | <a href="bookmark-group.jsp">북마크 그룹 관리</a></p>
	</div>
	<div>
		<table>
			<tr>
				<th>북마크 이름</th>
				<td><input type="text" value="" id = "name" size="20"></td>
			</tr>
			<tr>
				<th>순서</th>
				<td><input type="text" value="" id = "order" size="20"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button onclick="addBookmarkGroup()">추가</button>
				</td>
			</tr>
		</table>
	</div>
</body>
<script>
	
	function addBookmarkGroup() {
		var name = document.getElementById("name").value;
		var order = document.getElementById("order").value;
		
		location.href = "bookmark-group-add-result.jsp?name=" + name + "&order=" + order;
	}
	
</script>
</html>