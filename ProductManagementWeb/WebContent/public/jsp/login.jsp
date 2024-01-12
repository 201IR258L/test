<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
<title>商品管理ログイン</title>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">ログイン画面</h5>
		<div class="card-body">
			<c:if test="${not empty message}">
				<div class="alert alert-danger">
					<c:out value="${message}"/>
				</div>
			</c:if>
			<form action="${pageContext.request.contextPath}/public/LoginServlet" method="post" class="col-8">
				<div class="form-group">
					<label for="loginId">ログインID</label>
					<input type="text" class="form-control" id="loginId" name="loginId" value="<c:out value="${loginForm.loginId}"/>">
				</div>
				<div class="form-group">
					<label for="password">パスワード</label>
					<input type="password" class="form-control" id="password" name="password" value="<c:out value="${loginForm.password}"/>">
				</div>
				<button type="submit" class="btn btn-primary">ログイン</button>
			</form>
		</div>
	</div>

	<a href="${pageContext.request.contextPath}/public/UserInputInitServlet">新規ユーザ登録はこちら</a>
</div>
</body>
</html>