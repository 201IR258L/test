package jp.co.minato.pmw.controller.login;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.minato.pmw.constant.Constants;
import jp.co.minato.pmw.controller.AbstractController;
import jp.co.minato.pmw.dto.UserDto;
import jp.co.minato.pmw.mapper.entity.UserEntity;
import jp.co.minato.pmw.service.UserService;
import jp.co.minato.pmw.utility.Utility;

@Controller
public class LoginController extends AbstractController {

	@Autowired
	private UserService userService;

	@RequestMapping("/public/loginInit")
	public String initialize(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@RequestMapping(value = "/public/LoginServlet", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm loginForm, Model model) {

		model.addAttribute("loginForm", loginForm);

		List<UserEntity> entities = userService.find(copyLoginFormToUserDto(loginForm));

		if (entities.isEmpty()) {
			model.addAttribute("message", "ログインIDまたはパスワードが間違っています。");
			return "login";
		}

		// ユーザ情報をセッションスコープに保存する
		HttpSession session = getSession();
		session.setAttribute(Constants.SESSION_KEY_LOGIN_USER, entities.get(0));
		return "redirect:/ProductListServlet";
	}

	private UserDto copyLoginFormToUserDto(LoginForm form) {
		UserDto dto = new UserDto();
		dto.setLoginId(form.getLoginId());
		dto.setPassword(Utility.getHashPassword(form.getPassword()));
		return dto;
	}

}
