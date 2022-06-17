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
	<h1>예기치 않은 오류(Internal Server Error 500)     :(</h1>
	<p>
		<img id="error2"
						src="${pageContext.request.contextPath }/assets/images/pinggu2.jpg"
						style="width: 280px" alt="profile image"><br/>
		죄송합니다. 요청하신 페이지를 찾을 수 없습니다.<br/>
		잠시 후, 다시 시도해 주세요.<br>
		<br> <a href="${pageContext.request.contextPath }/main">메인화면</a>으로 가기<br>
	</p>
</body>
</html>