package jp.co.chaos.game.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.chaos.game.servlet.AbstractServlet;

/**
 * ユーザ登録画面初期化処理
 */
@WebServlet("/public/UserInputInitServlet")
public class UserInputInitServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userInput.jsp");
		dispatcher.forward(request, response);
	}
}
