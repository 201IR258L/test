<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/jsp/head.jsp"></jsp:include>
<title>ゲームログイン</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
 <script src="https://unpkg.com/three@0.146.0/build/three.min.js"></script>
<style type="text/css">
body{
    background-color:black;}
}
#chaosTable{
        margin: auto;
    }
    .scaled {
 transform: scale(0.5) translate(center, 0%);
}
    p{
    color:white;
    }
</style>
</head>
<body>
<div class="scaled">
<table>
<tr>
<td><audio controls src="${pageContext.request.contextPath}/audio/gAria.mp3" type="audio/mp3">クラシックでも聴きながら♪</audio></td>
</table>
<table id="chaosTable" align="center">
<tr align="center"><td><img src="${pageContext.request.contextPath}/image/gameTitle/gameLogo.png" alt="ゲームロゴ" oncontextmenu="return false;" /></td></tr>
<table id="chaosTable" align="center">
<tr align="center"><td><canvas id="myCanvas"></canvas></td></tr>
</table>
</table>
</div>
<div class="container">
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<div class="card mt-3">
		<h5 class="card-header">ログイン画面</h5>
		<div class="card-body">
			<form action="${pageContext.request.contextPath}/jsp/productList.jsp" method="post" class="col-8">
				<div class="form-group">
					<label for="loginId">ログインID</label>
					<input type="text" class="form-control" id="loginId" value="minato">
				</div>
				<div class="form-group">
					<label for="password">パスワード</label>
					<input type="password" class="form-control" id="password" value="minato">
				</div>
				<button type="submit" class="btn btn-primary">ログイン</button>
			</form>
		</div>
	</div>

	<a href="${pageContext.request.contextPath}/jsp/userInput.jsp">新規ユーザ登録はこちら</a>
</div>
<script src="${pageContext.request.contextPath}/static/js/index_login_red.js"></script>
</body>
</html>