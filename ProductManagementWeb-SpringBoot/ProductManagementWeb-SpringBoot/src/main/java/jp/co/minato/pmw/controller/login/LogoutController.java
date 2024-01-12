package jp.co.minato.pmw.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.minato.pmw.controller.AbstractController;

@Controller
public class LogoutController extends AbstractController {

	@RequestMapping("/LogoutServlet")
	public String logout(Model model) {
		getSession().invalidate();
		return "redirect:/LogoutInitServlet";
	}
	
	@RequestMapping("/LogoutInitServlet")
	public String initLogout(Model model) {
		getSession().invalidate();
		return "logout";
	}

}
