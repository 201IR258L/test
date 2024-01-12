<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>権限エラー</title>
</head>
<body>
<p>管理者権限では登録できません。</p><br>
<a href="${pageContext.request.contextPath}
/LoginServlet"><button>ログインページへ</button></a>
</body>
</html>