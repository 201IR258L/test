<%-- 旧メインメインページの情報を移植。 --%>
<%-- コード10-9　つぶやき感想投稿・閲覧のメイン画面を出力するビュー --%>
<%-- main.jsp(src/main/webapp/WEB-INF/jspディテクトリ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>ArtisticChaosGame</title>
<meta name = "viewport" content="initial-scale=1,shrink-to-fit=no">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<style>
@import url('https://fonts.googleapis.com/css2?family=Ephesis&family=Grenze+Gotisch:wght@100;500;600;700;800&family=Nova+Square&family=Smooch+Sans:wght@800&display=swap');
</style>
<style>
body{
}
h1{
font-family: 'Grenze Gotisch', serif;
font-size:5em;
</style>
<style>
.btn-village{
background-color: ;
color:black;
}
.btn-forest{
background-color: red;
color:black;
}
.btn-city{
background-color: gray;
color:black;
}
.btn-cave{
background-color: red;
color:black;
}
.btn-temple{
background-color: red;
color:black;
}
.btn-instructions{
background-color: red;
color:black;
}
.btn-btn-map{
background-color: red;
color:black;
}

</style>
</head>
<body>
<header>
<table>
<tr>
<th><a hred="" class="btn btn-village">村で何をするか考える</th>
<th><a hred="" class="btn btn-forest">歩いてはじまりの森へ</th>
<th><a hred="" class="btn btn-city">電車に乗って都会へ</th>
<th><a hred="" class="btn btn-cave">ロードバイクで洞窟へ</th>
<th><a hred="" class="btn btn-temple">飛空艇で太陽神殿へ</th>
<th><a hred="" class="btn btn-instructions">ゲーム説明書</th>
<th><a hred="" class="btn btn-map">地図と羅針盤</th>
</table>
</header>

<h1>ArtisticChaosGames</h1>
<%-- ログイン時に入力したユーザー名が表<audio controls src="${pageContext.request.contextPath}/image/gAria.mp3" type="audio/mp3">BGMを流す!!</audio>示される。 --%>


</p>
<opmsg><%--オーディオコントロールボタンで、ウェルカム画面に音楽を聞くことができる。 --%>
<%--jspFooter.jspにまとめる予定。 --%>

<%-- trは、テーブルの行を造る。TableRow --%>
<%-- tは、テーブルの行を造る。 --%>
<p>ゲームは、章立てですか、スキップして先のゲームで遊ぶことが出来ます。</p>
<p>ログアウトは、ある場所に入りあるイベントをクリアするとボタンが表示されます。</p>
<p>探してみて下さい。</p>
<table>
<tr><th>第1章　はじまりの森</th></tr>
<tr><th>～フォレス～</th></tr>
<p>ユーザーが最初に降り立った場所</p>
<tr><td><a href="${pageContext.request.contextPath}/ForesServlet.java">森に戻ってみる</a></td></tr>
<%--クリックすると、スライムに遭遇。 --%>
</table>
<%--trは行、tdは、入れるデータ。 --%>
<!-- 見出しは>、ここは、グーグルフォントでカオスっぽく。 -->
<%-- <p>石板型ロボット<%= loginUser.getRobotName() %>「<%= loginUser.getName() %>さん、何をしましょうか。」</p>--%>
<table>
<tr><th>第2章　たどり着いた村</th></tr>
<tr><th>ムラムーラン</th></tr>
<audio controls src="${pageContext.request.contextPath}/image/Mearas_Village.mp3" type="audio/mp3">村のBGMを流す!!</audio>
</table>
<p>森から元の世界には戻れなかったので、どうするかを考えよう</p>
<p>村に来た。</p>
<p>村には何もないし、都会に行ってみるか。</p>

<table>
<tr><th>第4章　神域シン・フィールド</th></tr>
<tr><th>　番号が書いてある石を動かしパズルを完成させよ!!!</th></tr>
<tr><td><a href="${pageContext.request.contextPath}/ShinFieldServlet.java">スライドパズル</a></td></tr>
</table>

<table>
<tr><th>第5章　最後の洞窟</th></tr>
<tr><th>　カオスダンジョン</th></tr>
<tr><th>　「これまで、ログアウト機能は、なかったな。」</th></tr>
<tr><th>　「最後のというくらいだから、ここをくぐればいいのかな？」</th></tr>
<tr><td><a href="${pageContext.request.contextPath}/ChaosDanjonServlet.java">洞窟に入る。</a></td></tr>
</table>
<table>
<tr><th>古代遺跡</th></tr>
<tr><th>　アンナーイガーイド</th></tr>
<tr><th>　時刻を刻む古代時計「アナローグ」</th></tr>
<tr><td><a href="${pageContext.request.contextPath}/AnalogServlet.java">時刻を確認する。</a></td></tr>
<tr><th>　古代人が残したゲームの解説石板「セキバーン」</th></tr>
<tr><td><a href="${pageContext.request.contextPath}/AnalogServlet.java">説明書を読む。</a></td></tr>
<tr><th>　古代人が書き記したこの世界の地図「フルチーズ」</th></tr>
<tr><td><a href="${pageContext.request.contextPath}/StoneTabletServlet.java">説明書を読む。</a></td></tr>
</table>
<p>洞窟は、基本的に1～5番を選択すると敵を倒しつつ、エンディングが進んでいき、最後にログアウトボタンが
　表示される。</p>
<p>敵と戦わずに逃げて通常のエンディングログアウトする。</p>
</body>
</html>

