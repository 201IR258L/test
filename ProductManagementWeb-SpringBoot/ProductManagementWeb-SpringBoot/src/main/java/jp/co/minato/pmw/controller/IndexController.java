package jp.co.minato.pmw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping(value = { "/", "/index" })
	public String home() {
		return "redirect:/public/loginInit";
	}
}
