<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/jsp/head.jsp"></jsp:include>
<title>商品一覧</title>
</head>
<body>
<div class="container">
	<jsp:include page="/jsp/header2.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">商品一覧画面</h5>
		<div class="card-body">
			<h5>検索条件</h5>
			<form action="${pageContext.request.contextPath}/jsp/productList.jsp" method="post">
				<div class="form-group row">
					<label for="name" class="col-md-2 col-form-label">商品名</label>
					<div class="col-md-10">
						<input type="text" class="form-control" id="name" value="商品1">
					</div>
				</div>
				<button type="submit" class="btn btn-primary">検索</button>
			</form>
		</div>

		<div class="card-body">
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
				<c:forEach begin="1" end="10" step="1" varStatus="status">
					<tr>
						<td>
							<c:set var="temp" value="000${status.index}"></c:set>
							${fn:substring(temp, fn:length(temp)-4, fn:length(temp))}
						</td>
						<td>商品${status.index}</td>
						<td>衣服</td>
						<td class="text-right">
							<fmt:formatNumber pattern="#,###" value="${1000 * status.index}"></fmt:formatNumber>
						</td>
						<td class="text-right">
							<fmt:formatNumber pattern="#,###" value="${10000 * status.index}"></fmt:formatNumber>
						</td>
						<td>2020/04/06</td>
						<td>
							<a href="${pageContext.request.contextPath}/jsp/productUpdate.jsp">変更／削除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>
