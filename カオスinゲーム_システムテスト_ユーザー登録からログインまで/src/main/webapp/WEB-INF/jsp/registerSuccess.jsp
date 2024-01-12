<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
</head>
<body>
<p>おめでとうございます。登録が完了しました。</p>
<!-- オープニングへ進むボタンを押すと、オープニング画面1へ遷移。-->
<form action="${pageContext.request.contextPath}/OpeningOneServlet" method="doGet">
<input type="submit" value="image/op1button.png">
</form>
</body>
</html>