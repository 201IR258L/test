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
		<h5 class="card-header">ユーザ登録画面</h5>
		<div class="card-body">
			<div class="alert alert-danger">
				ログインIDを入力してください。
			</div>
			<form action="${pageContext.request.contextPath}/jsp/userConfirmation.jsp" method="post" class="col-8">
				<div class="form-group row">
					<label for="loginId" class="col-md-2 col-form-label">ログインID</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="loginId" value="minato">
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-md-2 col-form-label">パスワード</label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="password" value="minato">
					</div>
				</div>
				<div class="form-group row">
					<label for="userName" class="col-md-2 col-form-label">ユーザ名</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="userName" value="みなと">
					</div>
				</div>
				<button type="submit" class="btn btn-primary">登録</button>
			</form>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/">トップへ</a>
</div>
</body>
</html>
