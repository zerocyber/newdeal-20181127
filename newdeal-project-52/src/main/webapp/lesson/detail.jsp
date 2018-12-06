<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>수업정보</title>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<h1>수업 상세정보</h1>
	<form action="update" method="post">
		<table border='1'>
			<tr>
				<th>번호</th>
				<td><input type="text" name="no" value="${lesson.no}" readonly></td>
			</tr>
			<tr>
				<th>수강명</th>
				<td><input type="text" value="${lesson.title}" readonly></td>
			</tr>
			<tr>
				<th>수업 내용</th>
				<td><textarea name="contents" rows="5" cols="50">${lesson.contents}</textarea></td>
			</tr>
			<tr>
				<th>시작일</th>
				<td><input type="text" value="${lesson.startDate}" readonly></td>
			</tr>
			<tr>
				<th>종료일</th>
				<td><input type="text" value="${lesson.endDate}" readonly></td>
			</tr>
			<tr>
				<th>총 수업시간</th>
				<td><input type="text" value="${lesson.totalHours}" readonly></td>
			</tr>
			<tr>
			
			<tr>
        <th>일 수업시간</th>
        <td><input type="text" value="${lesson.dayHours}" readonly></td>
      </tr>
      <tr>
				<th></th>
				<td>
					<button>변경</button>
					<button type="button" onclick="remove();">삭제</button>
				</td>
			</tr>
		</table>
	</form>
	
	<script>
	function remove(){
	 location.href = "delete?no=${lesson.no}"
	}	
	</script>
</body>
</html>
