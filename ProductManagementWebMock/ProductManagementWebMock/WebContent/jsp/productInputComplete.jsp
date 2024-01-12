<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/jsp/head.jsp"></jsp:include>
<title>商品登録</title>
</head>
<body>
<div class="container">
	<jsp:include page="/jsp/header2.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">商品登録完了画面</h5>
		<div class="card-body">
			<div class="alert alert-success">
				商品を登録しました。
			</div>
		</div>
	</div>
	<a href="${pageContext.request.contextPath}/jsp/productList.jsp">商品一覧へ</a>
</div>

</body>
</html>
