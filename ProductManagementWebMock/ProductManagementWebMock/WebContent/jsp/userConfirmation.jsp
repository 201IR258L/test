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
		<h5 class="card-header">ユーザ登録確認画面</h5>
		<div class="card-body">
			<div class="alert alert-danger">
				このログインIDは使用できません。
			</div>
			<form action="${pageContext.request.contextPath}/jsp/userConfirmation.jsp" method="post" class="col-8">
				<div class="form-group row">
					<label class="col-md-2 col-form-label">ログインID</label>
					<div class="col-md-10">
						<div class="form-control">minato</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 col-form-label">パスワード</label>
					<div class="col-md-10">
						<div class="form-control">※パスワードは表示されません</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 col-form-label">ユーザ名</label>
					<div class="col-md-10">
						<div class="form-control">みなと</div>
					</div>
				</div>
				<a href="${pageContext.request.contextPath}/jsp/userInput.jsp" class="btn btn-secondary">戻る</a>
				<a href="${pageContext.request.contextPath}/jsp/userComplete.jsp" class="btn btn-primary">登録</a>
			</form>
		</div>
	</div>
</div>
</body>
</html>