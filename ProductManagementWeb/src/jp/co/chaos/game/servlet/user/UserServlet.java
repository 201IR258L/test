package jp.co.chaos.game.servlet.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.chaos.game.constant.MessageEnum;
import jp.co.chaos.game.dto.UserDto;
import jp.co.chaos.game.entity.UserEntity;
import jp.co.chaos.game.form.UserForm;
import jp.co.chaos.game.service.UserService;
import jp.co.chaos.game.servlet.AbstractServlet;
import jp.co.chaos.game.utility.Validator;

/**
 * ユーザ登録処理共通クラス
 */
public class UserServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected UserService service = new UserService();

	protected List<String> validate(UserForm form) {
		List<String> messageList = new ArrayList<String>();

		//ログインID入力チェック
		String loginId = form.getLoginId();
		String loginIdLabel = "ログインID";
		if (!Validator.required(loginId)) {
			//必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(loginIdLabel));
		} else if (!Validator.maxLength(loginId, 100)) {
			//文字数チェック
			messageList.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(loginIdLabel, 100));
		} else if (!Validator.alphanumeric(loginId)) {
			//英数字チェック
			messageList.add(MessageEnum.MESSAGE_ALPHANUMERIC.getMessage(loginIdLabel));
		} else {
			//存在チェック
			UserDto dto = new UserDto();
			dto.setLoginId(loginId);
			List<UserEntity> list = this.service.find(dto);
			if(!list.isEmpty()) {
				messageList.add(MessageEnum.MESSAGE_EXISTS.getMessage(loginIdLabel));
			}
		}

		//パスワード入力チェック
		String password = form.getPassword();
		String passwordLabel = "パスワード";
		if (!Validator.required(password)) {
			//必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(passwordLabel));
		} else if (!Validator.maxLength(password, 100)) {
			//文字数チェック
			messageList.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(passwordLabel, 100));
		} else if (!Validator.alphanumeric(password)) {
			//英数字チェック
			messageList.add(MessageEnum.MESSAGE_ALPHANUMERIC.getMessage(passwordLabel));
		}

		String name = form.getName();
		String nameLabel = "ユーザ名";
		if (!Validator.required(name)) {
			//必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(nameLabel));
		} else if (!Validator.maxLength(name, 50)) {
			//文字数チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(nameLabel, 50));
		}
		return messageList;
	}

	protected UserForm getForm(HttpServletRequest request) {
		//リクエストパラメータ取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		//リクエストパラメータをUserFormに詰め替える
		UserForm form = new UserForm();
		form.setLoginId(loginId);
		form.setPassword(password);
		form.setName(name);
		return form;
	}
}
