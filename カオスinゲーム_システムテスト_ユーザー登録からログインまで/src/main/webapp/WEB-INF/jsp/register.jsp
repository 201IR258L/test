<%-- ユーザー登録ページ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<!-- 入力した値を渡す。 -->
<form action="${pageContext.request.contextPath}/AccountRegisterServlet" method="post">
<p>すべて入力してください</p>
<!-- <p>
<input type="radio" name="role" value="1">管理者で登録する
<input type="radio" name="role" value="2" checked>ユーザーで登録する
</p>-->
<!-- 管理者としての登録機能は設けたが、ログインできるだけにして
     一般ユーザーは、ログイン時に、hiddenフィールドで、自動的に
     name="authority_number"とvalue="2"が渡るようにする。-->
　　　名前：<input type="text" name="name" required><br>
ユーザーID：<input type="text" name="user_id" required><br>
パスワード：<input type="password" name="password" required><br>
<input type="hidden" name="authority_number" value="2" checked>
<input type="submit" value="登録"><br>
</form>
</body>
</html>