package jp.co.chaos.game.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.chaos.game.form.UserForm;

/**
 * ユーザ登録処理
 */
@WebServlet("/public/UserInputServlet")
public class UserInputServlet extends UserServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserForm form = super.getForm(request);

		//リクエストスコープに登録する
		request.setAttribute("userForm", form);

		List<String> messageList = super.validate(form);

		if (messageList.isEmpty()) {
			//ユーザ登録確認画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userConfirmation.jsp");
			dispatcher.forward(request, response);
		} else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("messageList", messageList);
			//ユーザ登録入力画面を表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userInput.jsp");
			dispatcher.forward(request, response);
		}
	}

}
