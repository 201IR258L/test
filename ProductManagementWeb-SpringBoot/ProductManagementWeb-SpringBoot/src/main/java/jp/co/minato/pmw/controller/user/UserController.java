package jp.co.minato.pmw.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.minato.pmw.constant.MessageEnum;
import jp.co.minato.pmw.controller.AbstractController;
import jp.co.minato.pmw.dto.UserDto;
import jp.co.minato.pmw.mapper.entity.UserEntity;
import jp.co.minato.pmw.service.UserService;
import jp.co.minato.pmw.utility.Utility;
import jp.co.minato.pmw.utility.Validator;

//import com.shujihidaka.gtd.web.constants.EditingCategory;
//import com.shujihidaka.gtd.web.constants.UserStatus;
//import com.shujihidaka.gtd.web.controller.AbstractController;
//import com.shujihidaka.gtd.web.mapper.condition.UserCondition;
//import com.shujihidaka.gtd.web.mapper.entity.UserEntity;
//import com.shujihidaka.gtd.web.mapper.entity.ext.UserEntityExt;
//import com.shujihidaka.gtd.web.service.UserService;
//import com.shujihidaka.gtd.web.service.dto.UserDto;
//import com.shujihidaka.gtd.web.utility.Converter;
//import com.shujihidaka.gtd.web.utility.Utils;
//import com.shujihidaka.gtd.web.utility.Validator;

@Controller
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/public/UserInputInitServlet" })
	public String init(Model model) {
		UserForm userForm = new UserForm();
		initialize(userForm, model);
		return "user/userInput";
	}

	@RequestMapping(value = { "/public/UserInputServlet" })
	public String confirme(UserForm userForm, Model model) {

		model.addAttribute("userForm", userForm);

		// 入力チェック
		List<String> messages = validate(userForm);

		if (!messages.isEmpty()) {
			// エラーメッセージ表示
			model.addAttribute("messageList", messages);
			return "user/userInput";
		}

		return "user/userConfirmation";
	}

	@RequestMapping(value = "/public/UserConfirmationServlet", params = "action=back")
	public String back(UserForm userForm, Model model) {
		initialize(userForm, model);
		return "user/userInput";
	}
	
	@RequestMapping(value = "/public/UserConfirmationServlet", params = "action=issue")
	public String issue(UserForm userForm, Model model) {
		initialize(userForm, model);
		
		model.addAttribute("userForm", userForm);

		// 入力チェック
		List<String> messages = validate(userForm);

		if (!messages.isEmpty()) {
			// エラーメッセージ表示
			model.addAttribute("messageList", messages);
			return "user/userConfirmation";
		}
		
		//データベースにユーザ情報を登録する
		userService.issue(copyUserFormToUserDto(userForm));

		return "user/userComplete";
	}

	public void initialize(UserForm userForm, Model model) {
		model.addAttribute("userForm", userForm);
	}

	private List<String> validate(UserForm form) {
		List<String> messageList = new ArrayList<String>();

		// ログインID入力チェック
		String loginId = form.getLoginId();
		String loginIdLabel = "ログインID";
		if (!Validator.required(loginId)) {
			// 必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(loginIdLabel));
		} else if (!Validator.maxLength(loginId, 100)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(loginIdLabel, 100));
		} else if (!Validator.alphanumeric(loginId)) {
			// 英数字チェック
			messageList.add(MessageEnum.MESSAGE_ALPHANUMERIC.getMessage(loginIdLabel));
		} else {
			// 存在チェック
			UserDto dto = new UserDto();
			dto.setLoginId(loginId);
			List<UserEntity> list = this.userService.find(dto);
			if (!list.isEmpty()) {
				messageList.add(MessageEnum.MESSAGE_EXISTS.getMessage(loginIdLabel));
			}
		}

		// パスワード入力チェック
		String password = form.getPassword();
		String passwordLabel = "パスワード";
		if (!Validator.required(password)) {
			// 必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(passwordLabel));
		} else if (!Validator.maxLength(password, 100)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(passwordLabel, 100));
		} else if (!Validator.alphanumeric(password)) {
			// 英数字チェック
			messageList.add(MessageEnum.MESSAGE_ALPHANUMERIC.getMessage(passwordLabel));
		}

		String name = form.getName();
		String nameLabel = "ユーザ名";
		if (!Validator.required(name)) {
			// 必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(nameLabel));
		} else if (!Validator.maxLength(name, 50)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(nameLabel, 50));
		}
		return messageList;
	}

	private UserDto copyUserFormToUserDto(UserForm form) {
		UserDto dto = new UserDto();
		dto.setName(form.getName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(Utility.getHashPassword(form.getPassword()));
		return dto;
	}
	
//	@RequestMapping(value = { "/user/init/{publicKey}" })
//	public String initWithUser(@PathVariable(name = "publicKey") String publicKey, Model model) {
//		// 存在チェック
//		List<String> messages = new ArrayList<>();
//		if (!exists(publicKey)) {
//			messages.add(MessageEnum.MESSAGE_NOT_EXISTS_OR_DELETED.getMessage("データ", publicKey));
//			model.addAttribute("messages", messages);
//			model.addAttribute("userForm", new UserForm());
//			return "user/user";
//		}
//
//		UserEntity user = getUser(publicKey);
//		UserForm userForm = getUserForm(user);
//
//		initialize(userForm, model);
//		return "user/user";
//	}
//
//	@RequestMapping(value = { "/user/issue" })
//	public String issue(UserForm userForm, Model model) {
//
//		Integer accountId = super.getAccountId();
//
//		// 入力チェック
//		List<String> messages = validate(userForm);
//
//		if (!messages.isEmpty()) {
//			// エラーメッセージ表示
//			model.addAttribute("messages", messages);
//			userForm.setEditingCategory(EditingCategory.UPDATE_ONLY);
//			initialize(userForm, model);
//			return "user/user";
//		}
//
//		UserDto userDto = new UserDto();
//		copyFormToDto(accountId, userForm, userDto);
//		userDto.setEditingCategory(EditingCategory.EDITABLE);
//		UserEntity entity = userService.insert(userDto);
//
//		return "redirect:/user/init/" + entity.getPublicKey();
//	}
//
//	@RequestMapping(value = { "/user/update/{publicKey}" })
//	public String update(@PathVariable(name = "publicKey") String publicKey, @ModelAttribute UserForm userForm,
//			Model model) {
//
//		// 存在チェック
//		List<String> messages = new ArrayList<>();
//		if (!exists(publicKey)) {
//			messages.add(MessageEnum.MESSAGE_NOT_EXISTS_OR_DELETED.getMessage("データ", publicKey));
//			model.addAttribute("messages", messages);
//			model.addAttribute("userForm", new UserForm());
//			return "user/user";
//		}
//
//		Integer accountId = super.getAccountId();
//
//		// 入力チェック
//		messages.addAll(validate(userForm));
//
//		if (!messages.isEmpty()) {
//			// エラーメッセージ表示
//			model.addAttribute("messages", messages);
//			initialize(userForm, model);
//			return "user/user";
//		}
//
//		UserDto userDto = new UserDto();
//		copyFormToDto(accountId, userForm, userDto);
//		UserEntity entity = userService.update(userDto);
//
//		return "redirect:/user/init/" + entity.getPublicKey();
//	}
//
//	@RequestMapping(value = { "/user/remove/{publicKey}/{versionNo}" })
//	public String remove(@PathVariable(name = "publicKey") String publicKey,
//			@PathVariable(name = "versionNo") String versionNo, Model model) {
//
//		Integer userId = super.getUserId();
//		Integer accountId = super.getAccountId();
//
//		// 入力チェック
//		List<String> messages = validateForRemove(userId, accountId, publicKey, versionNo);
//
//		if (!messages.isEmpty()) {
//			// エラーメッセージ表示
//			model.addAttribute("messages", messages);
//			UserEntity user = getUser(publicKey);
//			UserForm userForm = new UserForm();
//			copyEntityToForm(user, userForm);
//			initialize(userForm, model);
//			return "gtd/gtd";
//		}
//
//		UserDto userDto = new UserDto();
//		userDto.setAccountId(accountId);
//		userDto.setPublicKey(publicKey);
//		userDto.setVersionNo(Converter.stringToInteger(versionNo));
//		userService.remove(userDto);
//
//		return "redirect:/user";
//	}
//
//	public void initialize(UserForm userForm, Model model) {
//		model.addAttribute("userForm", userForm);
//		List<UserEntityExt> users = getUsers();
//		model.addAttribute("users", users);
//	}
//
//	private List<String> validate(UserForm userForm) {
//		List<String> messages = new ArrayList<>();
//
//		// ユーザコード入力チェック
//		String code = userForm.getCode();
//		String codeLabel = "ユーザコード";
//		if (Validator.isEmpty(code)) {
//			messages.add(MessageEnum.MESSAGE_REQUIRED_INPUT.getMessage(codeLabel));
//		} else if (!Validator.maxLength(code, 50)) {
//			messages.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(codeLabel, 50));
//		}
//
//		// ユーザ名入力チェック
//		String name = userForm.getName();
//		String nameLabel = "ユーザ名";
//		if (Validator.isEmpty(name)) {
//			messages.add(MessageEnum.MESSAGE_REQUIRED_INPUT.getMessage(nameLabel));
//		} else if (!Validator.maxLength(name, 50)) {
//			messages.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(nameLabel, 50));
//		}
//
//		// 有効開始日時入力チェック
//		LocalDateTime availableStart = null;
//		String availableStartDatetime = userForm.getAvailableStartDatetime();
//		String availableStartDatetimeLabel = "有効開始日時";
//		if (Validator.isEmpty(availableStartDatetime)) {
//			messages.add(MessageEnum.MESSAGE_REQUIRED_INPUT.getMessage(availableStartDatetimeLabel));
//		} else if (!Validator.dateTime(availableStartDatetime)) {
//			messages.add(MessageEnum.MESSAGE_DATE_TIME.getMessage(availableStartDatetimeLabel));
//		} else if (!Validator.dateTimeRange(availableStartDatetime)) {
//			messages.add(MessageEnum.MESSAGE_DATE_TIME_RANGE.getMessage(availableStartDatetimeLabel));
//			availableStart = Converter.stringToDateTime(availableStartDatetime);
//		}
//
//		// 有効終了日時入力チェック
//		LocalDateTime availableEnd = null;
//		String availableEndDatetime = userForm.getAvailableEndDatetime();
//		String availableEndDatetimeLabel = "有効終了日時";
//		if (Validator.isEmpty(availableEndDatetime)) {
//			messages.add(MessageEnum.MESSAGE_REQUIRED_INPUT.getMessage(availableEndDatetimeLabel));
//		} else if (!Validator.dateTime(availableEndDatetime)) {
//			messages.add(MessageEnum.MESSAGE_DATE_TIME.getMessage(availableEndDatetimeLabel));
//		} else if (!Validator.dateTimeRange(availableEndDatetime)) {
//			messages.add(MessageEnum.MESSAGE_DATE_TIME_RANGE.getMessage(availableEndDatetimeLabel));
//			availableEnd = Converter.stringToDateTime(availableEndDatetime);
//		}
//
//		if (Validator.isNotNull(availableStart) && Validator.isNotNull(availableEnd)) {
//			if (availableStart.isAfter(availableEnd)) {
//				messages.add(MessageEnum.MESSAGE_DATE_RELATIONSHIP.getMessage(availableStartDatetimeLabel,
//						availableEndDatetimeLabel));
//			}
//		}
//
//		// 状態入力チェック
//		String status = userForm.getStatus();
//		String statusLabel = "状態";
//		if (Validator.isEmpty(status)) {
//			messages.add(MessageEnum.MESSAGE_REQUIRED_SELECT.getMessage(statusLabel));
//		} else if (UserStatus.codeOf(status) == null) {
//			messages.add(MessageEnum.MESSAGE_INVALID_VALUE.getMessage(statusLabel));
//		}
//
//		// 備考入力チェック
//		String note = userForm.getName();
//		String noteLabel = "備考";
//		if (Validator.isNotEmpty(note) && !Validator.maxLength(note, 100)) {
//			messages.add(MessageEnum.MESSAGE_MAX_LENGTH.getMessage(noteLabel, 100));
//		}
//
//		return messages;
//	}
//
//	private List<String> validateForRemove(Integer userId, Integer accountId, String publicKey, String versionNo) {
//		List<String> messages = new ArrayList<>();
//
//		// 入力チェック
//		// 存在チェック
//		if (!exists(publicKey)) {
//			messages.add(MessageEnum.MESSAGE_NOT_EXISTS_OR_DELETED.getMessage("データ", publicKey));
//			return messages;
//		}
//
//		UserEntity user = getUser(publicKey);
//
//		// バージョン番号チェック
//		String formVersionNo = versionNo;
//		String entityVersionNo = String.valueOf(user.getVersionNo());
//		if (Validator.notEquals(formVersionNo, entityVersionNo)) {
//			messages.add(MessageEnum.MESSAGE_DATA_UPDATED.getMessage());
//			return messages;
//		}
//		return messages;
//	}
//
//	private boolean exists(String publicKey) {
//		if (Validator.isEmpty(publicKey)) {
//			return false;
//		}
//		UserEntity user = getUser(publicKey);
//		return user != null;
//	}
//
//	private UserEntity getUser(String publicKey) {
//		UserCondition condition = new UserCondition();
//		condition.setPublicKey(publicKey);
//		condition.setDeleted(Boolean.FALSE);
//		condition.setEnabled(Boolean.TRUE);
//		UserEntity memo = userService.findByPublicKey(condition);
//		return memo;
//	}
//
//	private List<UserEntityExt> getUsers() {
//		UserCondition condition = new UserCondition();
//		condition.setDeleted(Boolean.FALSE);
//		condition.setEnabled(Boolean.TRUE);
//		List<UserEntityExt> users = userService.findByCondition(condition);
//		return users;
//	}
//
//	private UserForm getUserForm(UserEntity user) {
//		UserForm userForm = new UserForm();
//		copyEntityToForm(user, userForm);
//		return userForm;
//	}
//
//	private void copyFormToDto(Integer accountId, UserForm userForm, UserDto userDto) {
//		userDto.setPublicKey(userForm.getPublicKey());
//		userDto.setCode(userForm.getCode());
//		userDto.setName(userForm.getName());
//		userDto.setAvailableStartDatetime(Converter.stringToDateTime(userForm.getAvailableStartDatetime()));
//		userDto.setAvailableEndDatetime(Converter.stringToDateTime(userForm.getAvailableEndDatetime()));
//		// TODO 未実装
//		userDto.setStatus(UserStatus.codeOf(userForm.getStatus()));
//		// userDto.setStatus(UserStatus.codeOf(userForm.getStatus()));
//		userDto.setNote(userForm.getNote());
//		userDto.setAccountId(accountId);
//		if (Validator.isNotEmpty(userForm.getVersionNo())) {
//			userDto.setVersionNo(Integer.parseInt(userForm.getVersionNo()));
//		}
//	}
//
//	private void copyEntityToForm(UserEntity user, UserForm userForm) {
//		userForm.setPublicKey(user.getPublicKey());
//		userForm.setCode(user.getCode());
//		userForm.setName(user.getName());
//		userForm.setAvailableStartDatetime(Converter.dateTimeToString(user.getAvailableStartDatetime()));
//		userForm.setAvailableEndDatetime(Converter.dateTimeToString(user.getAvailableEndDatetime()));
//		userForm.setStatus(user.getStatus().getCode());
//		userForm.setNote(user.getNote());
//		userForm.setEditingCategory(user.getEditingCategory());
//		userForm.setVersionNo(String.valueOf(user.getVersionNo()));
//	}

}
