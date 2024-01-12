<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" %>
<%@ page import="model.Hand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
// セッションスコープから情報を取得
Hand gameResults = (Hand) session.getAttribute("gameResuls");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果画面</title>
</head>
<body>
<c:if test="${gameResults.getGameResults()==1}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グーイメージ" 
oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グーイメージ" 
oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Rock.png" alt="グーボタン" 
oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Rock.png" alt="グーボタン" 
oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="footerMsgDraw.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==2}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グーイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Rock.png" alt="グーボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Scissors.png" alt="チョキボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="footerMsgUserWin.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==3}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グーイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パーイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Rock.png" alt="グーボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Paper.png" alt="パーボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="fotterMsgUserLose.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==4}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グーイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Scissors.png" alt="チョキボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Rock.png" alt="グーボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="fotterMsgUserLose.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==5}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Scissors.png" alt="チョキボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Scissors.png" alt="チョキボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="footerMsgDraw.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==6}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パーイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Scissors.png" alt="チョキボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Paper.png" alt="パーボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="footerMsgUserWin.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==7}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パーイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Rock.png" alt="グーイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Paper.png" alt="パーボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Rock.png" alt="グーボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="footerMsgUserWin.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==8}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パーイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Scissors.png" alt="チョキイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Paper.png" alt="パーボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Scissors.png" alt="チョキボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="fotterMsgUserLose.jsp"></jsp:include>
</c:if>
<c:if test="${gameResults.getGameResults()==9}">
<table>
<jsp:include page="gameResultHeader.jsp"></jsp:include>
<tr>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パーイメージ" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/RPS_Paper.png" alt="パーイメージ" oncontextmenu="return false;" /></th>
　</tr>
　<tr>
<th><img src="${pageContext.request.contextPath}/upload/Button_Paper.png" alt="パーボタン" oncontextmenu="return false;" /></th>
<th width="80"></th>
<th><img src="${pageContext.request.contextPath}/upload/Button_Paper.png" alt="パーボタン" oncontextmenu="return false;" /></th>
　</tr>
</table>
<jsp:include page="footerMsgDraw.jsp"></jsp:include>
</c:if>
</form>
<p>　　<a href="index.jsp">もう一度、じゃんけんする。</a></p>
</body>
</html>