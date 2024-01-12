<!-- LoginServlet.javaからフォワード -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginPage</title>
<%-- 背景全体の色を指定　--%>
<%--　色のパターンは、CSS Color Module Level_4.2.4.1.HSLexsamplesを参照。--%>
<%-- タイトル部分のフォントサイズは、大きめに。--%>
<%-- サイズ指定は、emではなく、絶対値のピクセルに変更する予定。
     まずは、見た目をチェックするためにemで指定。--%>
<%-- 背景は、ロゴにあうデザインの挿絵 --%> 
<%-- オープニングは、タイプライター風に--%>   
<style>
body{
}
title{
font-family: 'Grenze Gotisch', serif;
font-size:6.0em;
color:hsl(60,40%,40%);
}
subtitle{
font-family: 'DotGothic16', sans-serif;
font-size:2.0em;
color:hsl(270,40%,20%);
}
opmsg{
font-family: 'DotGothic16', sans-serif;
font-size:1.0em;
color:hsl(30,60%,20%);
animation: typingdemo 8s steps(36), blinking .5s step-end infinite alternate;
}
<%--　ひとまずログインからの操作は、p要素。--%>
p{}
</style>
<%--　創造神を眠りから覚まし回転させるためにJavaScriptを実装 --%>
<%-- three.min.js --%>

<script src="https://unpkg.com/three@0.146.0/build/three.min.js"></script>
</head>
<body style="">
<title>ArtisticChaosGame</title>
<subtitle>～アートとカオスが融合する不思議な世界～</subtitle>
<pre>
ここに、内田リーダーの挿絵を入れる。
</pre>
<%-- 創造神カオスクルクル「青」--%>

<%-- 創造神描画のためのキャンバスを準備 --%>
<canvas id="myCanvas"></canvas>

<p>創造神カオス「ゲームで遊ぶ場合には、必要な情報を入力せよ。</p>
<form action="${pageContext.request.contextPath}/AccountSearchServlet" method="post">
ユーザーID：<input type="text" name="loginid" required><br>
パスワード：<input type="password" name="password" required><br>
<input type="submit" value="ログイン"><br>
</form>
<actext>
<p>ユーザー登録がお済みでない方はこちらへ↓</p><br>
<form action="${pageContext.request.contextPath}/RegisterServlet" method="doGet">
  <input type="submit" value="会員登録へ進む">
</form>
<%--jspFooter.jspにまとめる予定。 --%>
<audio controls src="${pageContext.request.contextPath}/image/gAria.mp3" type="audio/mp3">BGMを流す!!</audio>
<%-- 創造神カオスクルクル「青」_login.jsを読み込む --%>
<script src="chaos_blue.js"></script>
</body>
</html>