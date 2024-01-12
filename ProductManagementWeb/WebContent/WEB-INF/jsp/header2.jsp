<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top mb-3">
	<a class="navbar-brand" href="#">商品管理システム</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<c:set var="productListActive" value="" />
	<c:set var="productInputActive" value="" />
	<c:if test="${param.menuCode == 'PRODUCT_LIST'}">
		<c:set var="productListActive" value="active" />
	</c:if>
	<c:if test="${param.menuCode == 'PRODUCT_INPUT'}">
		<c:set var="productInputActive" value="active" />
	</c:if>
	<div class="collapse navbar-collapse" id="navbarColor02">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link ${productListActive}" href="${pageContext.request.contextPath}/ProductListServlet">商品一覧</a>
			</li>
			<li class="nav-item">
				<a class="nav-link ${productInputActive}" href="${pageContext.request.contextPath}/ProductInputInitServlet">商品登録</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="${pageContext.request.contextPath}/LogoutServlet">ログアウト</a>
			</li>
		</ul>
	</div>
</nav>
