package jp.co.chaos.game.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.chaos.game.servlet.AbstractServlet;

/**
 * ログアウト処理
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログアウト処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * ログアウト処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().invalidate();

		//ログアウト画面を表示する
		response.sendRedirect(request.getContextPath() + "/LogoutInitServlet");
	}
}
