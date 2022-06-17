<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Not Found(404)     :(</h1>
	<img id="error"
						src="${pageContext.request.contextPath }/assets/images/pinggu.jpg"
						style="width: 280px" alt="profile image">
	<p>
		죄송합니다. 요청하신 페이지를 찾을 수 없습니다.<br>
		<br> <a href="${pageContext.request.contextPath }/main">메인화면</a>으로 가기<br>
	</p>
</body>
</html>