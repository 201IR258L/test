<%--第3教室_中島作成
    提出日：令和5年12月7日 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ画面</title>
</head>
<body>
<h3>　　RPSオンライン</h3>
<p>　　最初はグー、じゃんけん・・・</p>
<table>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グー" oncontextmenu="return false;" /></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキ" oncontextmenu="return false;" /></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パー" oncontextmenu="return false;" /></th>
　</tr>
<tr>
<th><form action="${pageContext.request.contextPath}/GameStart" method="post">
  <input type="hidden" name="userHand" value="0">
  <input type="image" src="upload/Button_Rock.png" alt="グー"
   align="middle" oncontextmenu="return false;">
</form></th>
<th><form action="${pageContext.request.contextPath}/GameStart" method="post">
  <input type="hidden" name="userHand" value="1">
  <input type="image" src="upload/Button_Scissors.png" alt="チョキ" 
  align="middle" oncontextmenu="return false;">
</form></th>　
<th><form action="${pageContext.request.contextPath}/GameStart" method="post">
  <input type="hidden" name="userHand" value="2">
  <input type="image" src="upload/Button_Paper.png" alt="パー" 
  align="middle" oncontextmenu="return false;">
</form></th>　
</tr>
</table>
</body>
</html>