package jp.co.chaos.game.servlet;

import java.sql.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.chaos.game.constant.Constants;
import jp.co.chaos.game.entity.UserEntity;
import jp.co.chaos.game.utility.Utility;

/**
 * サーブレットの共通クラス
 */
public class AbstractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログインユーザ情報取得
	 * @param request
	 * @return
	 */
	protected UserEntity getLoginUserEntity(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (UserEntity) (session.getAttribute(Constants.SESSION_KEY_LOGIN_USER));
	}

	/**
	 * ログインユーザID取得
	 * @param request
	 * @return
	 */
	protected Integer getLoginUserId(HttpServletRequest request) {
		return getLoginUserEntity(request).getId();
	}

	protected String toString(Object object) {
		return Utility.toString(object);
	}

	protected String toString(Date date) {
		return Utility.toString(date);
	}

}
