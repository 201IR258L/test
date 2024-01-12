package jp.co.minato.pmw.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.dto.UserDto;
import jp.co.minato.pmw.form.UserForm;
import jp.co.minato.pmw.utility.Utility;

/**
 * ユーザ登録確認処理
 */
@WebServlet("/public/UserConfirmationServlet")
public class UserConfirmationServlet extends UserServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserForm form = getForm(request);
		request.setAttribute("userForm", form);

		if (form.getAction().equals("back")) {
			//リクエストスコープに登録する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userInput.jsp");
			dispatcher.forward(request, response);
			return;
		} else if (form.getAction().equals("issue")) {

			List<String> messageList = validate(form);

			if (messageList.isEmpty() == false) {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("messageList", messageList);
				//ユーザ登録入力画面を表示する
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userConfirmation.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//データベースにユーザ情報を登録する
			super.service.issue(copyUserFormToUserDto(form));

			//ユーザ登録完了画面へリダイレクト
			response.sendRedirect(request.getContextPath() + "/public/UserCompleteInitServlet");
		}

	}

	private UserDto copyUserFormToUserDto(UserForm form) {
		UserDto dto = new UserDto();
		dto.setName(form.getName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(Utility.getHashPassword(form.getPassword()));
		return dto;
	}

	protected UserForm getForm(HttpServletRequest request) {
		//リクエストパラメータ取得
		UserForm form = super.getForm(request);
		String action = request.getParameter("action");
		form.setAction(action);
		return form;
	}
}
