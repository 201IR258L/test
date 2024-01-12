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
		<h5 class="card-header">ユーザ登録画面</h5>
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
			<form action="${pageContext.request.contextPath}/public/UserInputServlet" method="post" class="col-8">
				<div class="form-group row">
					<label for="loginId" class="col-md-2 col-form-label">ログインID</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="loginId" name="loginId" value="<c:out value='${userForm.loginId}'/>">
					</div>
				</div>
				<div class="form-group row">
					<label for="password" class="col-md-2 col-form-label">パスワード</label>
					<div class="col-md-10">
						<input type="password" class="form-control" id="password" name="password" value="<c:out value='${userForm.password}'/>">
					</div>
				</div>
				<div class="form-group row">
					<label for="userName" class="col-md-2 col-form-label">ユーザ名</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="userName" name="name" value="<c:out value='${userForm.name}'/>">
					</div>
				</div>
				<button type="submit" class="btn btn-primary">登録</button>
			</form>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/">トップへ</a>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp"></jsp:include>
</body>
</html>
