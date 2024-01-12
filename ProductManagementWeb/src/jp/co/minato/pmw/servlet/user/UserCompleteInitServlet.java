package jp.co.minato.pmw.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.servlet.AbstractServlet;

/**
 * ユーザ登録完了画面初期化処理
 */
@WebServlet("/public/UserCompleteInitServlet")
public class UserCompleteInitServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userComplete.jsp");
		dispatcher.forward(request, response);
	}
}
