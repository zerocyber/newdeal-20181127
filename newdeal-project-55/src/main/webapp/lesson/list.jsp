<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>수강 목록</title>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<h1>수강 목록</h1>
	
	<p><a href='add'>새 수강 등록</a></p>
	
	<table border='1'>
		<tr>
			<th>번호</th>
			<th>수업 이름</th>
			<th>시작일</th>
			<th>종료일</th>
		</tr>
		<c:forEach items="${list}" var="lesson">
			<tr>
				<td>${lesson.no}</td>
				<td><a href="detail?no=${lesson.no}">${lesson.title}</a></td>
				<td>${lesson.startDate}</td>
				<td>${lesson.endDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
