package jp.co.minato.pmw;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ProductManagementWebHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		// セッションタイムアウト時間を秒で設定
		session.setMaxInactiveInterval(60 * 60 * 1);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	}
}
