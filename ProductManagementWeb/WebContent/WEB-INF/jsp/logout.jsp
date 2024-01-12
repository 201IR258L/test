<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
<title>ログアウト</title>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">ログアウト画面</h5>
		<div class="card-body">
			<div class="alert alert-success">
				ログアウトしました。
			</div>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/">トップへ</a>
</div>
</body>
</html>