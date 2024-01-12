package jp.co.minato.pmw.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.minato.pmw.constant.Constants;
import jp.co.minato.pmw.mapper.entity.UserEntity;
import jp.co.minato.pmw.utility.Utility;

public abstract class AbstractController {

	@Autowired
	private HttpSession session;

	protected HttpSession getSession() {
		return session;
	}

	/**
	 * ログインユーザ情報取得
	 * 
	 * @param request
	 * @return
	 */
	protected UserEntity getLoginUserEntity() {
		return (UserEntity) (session.getAttribute(Constants.SESSION_KEY_LOGIN_USER));
	}

	/**
	 * ログインユーザID取得
	 * 
	 * @param request
	 * @return
	 */
	protected Integer getLoginUserId() {
		return getLoginUserEntity().getId();
	}

	protected String toString(Object object) {
		return Utility.toString(object);
	}

	protected String toString(Date date) {
		return Utility.toString(date);
	}

}
