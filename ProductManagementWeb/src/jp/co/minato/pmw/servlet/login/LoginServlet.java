package jp.co.minato.pmw.servlet.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.minato.pmw.constant.Constants;
import jp.co.minato.pmw.dto.UserDto;
import jp.co.minato.pmw.entity.UserEntity;
import jp.co.minato.pmw.form.LoginForm;
import jp.co.minato.pmw.service.UserService;
import jp.co.minato.pmw.servlet.AbstractServlet;
import jp.co.minato.pmw.utility.Utility;

/**
 * ログイン処理
 */
@WebServlet("/public/LoginServlet")
public class LoginServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginForm form = getForm(request);

		request.setAttribute("loginForm", form);

		UserService service = new UserService();
		List<UserEntity> entities = service.find(copyLoginFormToUserDto(form));

		if (entities.isEmpty()) {
			request.setAttribute("message", "ログインIDまたはパスワードが間違っています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/public/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			//ユーザ情報をセッションスコープに保存する
			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_KEY_LOGIN_USER, entities.get(0));
			response.sendRedirect(request.getContextPath() + "/ProductListServlet");
		}
	}

	private LoginForm getForm(HttpServletRequest request) {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		LoginForm form = new LoginForm();
		form.setLoginId(loginId);
		form.setPassword(password);
		return form;
	}

	private UserDto copyLoginFormToUserDto(LoginForm form) {
		UserDto dto = new UserDto();
		dto.setLoginId(form.getLoginId());
		dto.setPassword(Utility.getHashPassword(form.getPassword()));
		return dto;
	}

}
