<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/jsp/head.jsp"></jsp:include>
<title>ユーザ登録</title>
</head>
<body>
<div class="container">
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">ユーザ登録完了画面</h5>
		<div class="card-body">
			<div class="alert alert-success">
				ユーザ登録が完了しました。
			</div>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/">トップへ</a>
</div>

</body>
</html>