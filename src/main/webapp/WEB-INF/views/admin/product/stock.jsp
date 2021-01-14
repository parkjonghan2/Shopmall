<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<th>시발</th>
<c:forEach items="${ProductVO}" var="vo">
	<tr><td>${vo.pdt_num}</td></tr>
	<tr><td>${vo.pdt_amount}</td></tr>
	<tr><td>${vo.pdt_name}</td></tr>
	
	</c:forEach>
	
	</table>
</body>
</html>