<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/jsp/head.jsp"></jsp:include>
<title>商品変更</title>
</head>
<body>
<div class="container">
	<jsp:include page="/jsp/header2.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">商品変更画面</h5>
		<div class="card-body">
			<div class="alert alert-danger">
				商品IDを入力してください。
			</div>
			<form action="" method="post" class="col-8">
				<div class="form-group row">
					<label for="id" class="col-md-2 col-form-label">商品ID</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="id" value="0002" >
					</div>
				</div>
				<div class="form-group row">
					<label for="name" class="col-md-2 col-form-label">商品名</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="name" value="Tシャツ">
					</div>
				</div>
				<div class="form-group row">
					<label for="category" class="col-md-2 col-form-label">商品分類</label>
					<div class="col-md-10">
						<select class="form-control" id="category" >
							<option>衣服</option>
							<option>キッチン用品</option>
							<option>事務用品</option>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label for="price" class="col-md-2 col-form-label">販売単価</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="price" value="1000">
					</div>
				</div>
				<div class="form-group row">
					<label for="purchasePrice" class="col-md-2 col-form-label">仕入単価</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="purchasePrice" value="10000">
					</div>
				</div>
				<div class="form-group row">
					<label for="registrationDate" class="col-md-2 col-form-label">登録日</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="registrationDate" value="2020/04/06">
					</div>
				</div>
				<a href="${pageContext.request.contextPath}/jsp/productUpdateComplete.jsp" class="btn btn-primary">変更</a>
				<a href="${pageContext.request.contextPath}/jsp/productDeleteComplete.jsp" class="btn btn-danger">削除</a>
			</form>
		</div>
	</div>
</div>

</body>
</html>
