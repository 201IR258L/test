package jp.co.minato.pmw.servlet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.servlet.AbstractServlet;

/**
 * ログアウト画面処理
 */
@WebServlet("/LogoutInitServlet")
public class LogoutInitServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ログアウト画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp");
		dispatcher.forward(request, response);
	}
}
