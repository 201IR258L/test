<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/head.jsp"></jsp:include>
<title>商品一覧</title>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/jsp/header2.jsp">
		<jsp:param name="menuCode" value="PRODUCT_LIST" />
	</jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">商品一覧画面</h5>
		<div class="card-body">
			<h5>検索条件</h5>
			<form action="${pageContext.request.contextPath}/ProductListServlet" method="post">
				<div class="form-group row">
					<label for="name" class="col-md-2 col-form-label">商品名</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="name" name="name" value="${productForm.name}">
					</div>
				</div>
				<button type="submit" class="btn btn-primary">検索</button>
			</form>
		</div>

		<div class="card-body">
			<c:if test="${empty products}">
			<div class="alert alert-danger">
				${message}
			</div>
			</c:if>
			<c:if test="${not empty products}">
			<h5>検索結果</h5>
			<table class="table table-bordered">
				<tr>
					<th scope="col">商品ID</th>
					<th scope="col">商品名</th>
					<th scope="col">商品分類</th>
					<th scope="col">販売単価</th>
					<th scope="col">仕入単価</th>
					<th scope="col">登録日</th>
					<th scope="col"></th>
				</tr>
				<c:forEach var="product" items="${products}" varStatus="status">
					<tr>
						<td>
							<c:out value="${product.productId}"/>
						</td>
						<td><c:out value="${product.name}"/></td>
						<td>${product.productCategory.name}</td>
						<td class="text-right">
							<fmt:formatNumber pattern="#,###" value="${product.unitPrice}"/>
						</td>
						<td class="text-right">
							<fmt:formatNumber pattern="#,###" value="${product.purchaseUnitPrice}"/>
						</td>
						<td><fmt:formatDate pattern="yyyy/MM/dd" value="${product.registrationDate}"/></td>
						<td>
							<a href="${pageContext.request.contextPath}/ProductUpdateInitServlet?id=${product.id}">変更／削除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>
