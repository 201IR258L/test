<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
<title>ユーザ登録</title>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">ユーザ登録確認画面</h5>
		<div class="card-body">
			<c:if test="${not empty messageList}">
			<div class="alert alert-danger alert-dismissible fade show" role="alert">
			<c:forEach var="message" items="${messageList}">
				 <c:out value="${message}"/><br/>
			</c:forEach>
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/public/UserConfirmationServlet" method="post" class="col-8">
				<div class="form-group row">
					<label class="col-md-2 col-form-label">ログインID</label>
					<div class="col-md-10">
						<div class="form-control"><c:out value="${userForm.loginId}"/></div>
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
						<div class="form-control"><c:out value="${userForm.name}"/></div>
					</div>
				</div>
				<button name="action" value="back" class="btn btn-secondary">戻る</button>
				<button name="action" value="issue" class="btn btn-primary">登録</button>
				<input type="hidden" name="loginId" value="${userForm.loginId}">
				<input type="hidden" name="password" value="${userForm.password}">
				<input type="hidden" name="name" value="${userForm.name}">
			</form>
		</div>
	</div>
</div>
</body>
</html>