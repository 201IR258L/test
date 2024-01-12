package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GameLogic;
import model.Hand;

@WebServlet("/GameStart")
public class GameStart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userHand = request.getParameter("userHand");
		Hand hand = new Hand(userHand);
		// じゃんけんゲーム処理
		GameLogic gameLogic = new GameLogic();
		hand.setGameResults(gameLogic.execute(hand));
		// じゃんけん情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("gameResults", hand);
		// 結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/gameResult.jsp");
		dispatcher.forward(request, response);
	}
}
//http://localhost:8080/ジャンケンプログラム