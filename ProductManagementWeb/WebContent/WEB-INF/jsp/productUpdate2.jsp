<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pm" uri="http://minato.co.jp/jsp/pmtl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
<title>商品変更</title>
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/jsp/header2.jsp">
			<jsp:param name="menuCode" value="PRODUCT_LIST" />
		</jsp:include>
		<div class="card mt-3">
			<h5 class="card-header">商品変更画面</h5>
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
				<form action="${pageContext.request.contextPath}/ProductUpdateServlet" method="post" class="col-8">
					<div class="form-group row">
						<label for="id" class="col-md-2 col-form-label">商品ID</label>
						<div class="col-md-10">
							<input type="text" class="form-control" id="id" name="productId" value='<c:out value="${productForm.productId}"/>'>
						</div>
					</div>
					<div class="form-group row">
						<label for="name" class="col-md-2 col-form-label">商品名</label>
						<div class="col-md-10">
							<input type="text" class="form-control" id="name" name="name" value="<c:out value="${productForm.name}"/>">
						</div>
					</div>
					<div class="form-group row">
						<label for="category" class="col-md-2 col-form-label">商品分類</label>
						<div class="col-md-10">
							<select class="form-control" id="category" name="categoryCode">
								<option value=""></option>
								<c:forEach var="productCategory" items="${pm:ProductCategory()}">
									<c:set var="selected" value=""/>
									<c:if test="${productForm.categoryCode == productCategory.code}">
										<c:set var="selected" value="selected"/>
									</c:if>
									<option value="<c:out value='${productCategory.code}'/>" ${selected}><c:out value='${productCategory.name}'/></option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label for="price" class="col-md-2 col-form-label">販売単価</label>
						<div class="col-md-10">
							<input type="text" class="form-control" id="price" name="unitPrice" value="<c:out value="${productForm.unitPrice}"/>">
						</div>
					</div>
					<div class="form-group row">
						<label for="purchasePrice" class="col-md-2 col-form-label">仕入単価</label>
						<div class="col-md-10">
							<input type="text" class="form-control" id="purchasePrice" name="purchaseUnitPrice" value="<c:out value="${productForm.purchaseUnitPrice}"/>">
						</div>
					</div>
					<div class="form-group row">
						<label for="registrationDate" class="col-md-2 col-form-label">登録日</label>
						<div class="col-md-10">
							<input type="text" class="form-control" id="registrationDate" name="registrationDate" value="<c:out value="${productForm.registrationDate}"/>">
						</div>
					</div>
					<button class="btn btn-primary">変更</button>
					<a href="${pageContext.request.contextPath}/ProductDeleteServlet?id=${productForm.id}&versionNo=${productForm.versionNo}" class="btn btn-danger">削除</a>
					<input type="hidden" name="id" value="${productForm.id}">
					<input type="hidden" name="versionNo" value="${productForm.versionNo}">
				</form>
			</div>
		</div>
	</div>

</body>
</html>
