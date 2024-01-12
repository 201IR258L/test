<%-- ログインするとページが割られる。--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勇者アドミンの部屋</title>
</head>
<body>
<p>ここは最後の洞窟で、ラスボスを倒した人だけが閲覧できる古代勇者アドミン(管理者のページです。)</p>
<c:choose>
<c:when test="${account.authorityNumber == 1 }">
<p>1番のページ。</p>
</c:when>
<c:when test="${account.authorityNumber == 2 }">
<p>2番のページ。ユーザーページへ飛ぶ</p>
</c:when>
<c:otherwise>
<a href="${pageContext.request.contextPath}/LoginServlet">ログインページへ</a>
</c:otherwise>
</c:choose>
</body>
</html>