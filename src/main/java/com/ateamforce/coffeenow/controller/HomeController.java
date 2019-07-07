package com.ateamforce.coffeenow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	// RedirectAttributes are needed in order to pass attributes to the redirect
	// view. not needed when forward is used instead
	@RequestMapping
	public String home(Model model) {
		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");
		return "front/home";
	}

//  SAME AS ABOVE (not good cause of tight coupling with InternalResourceView)
//	@RequestMapping
//	public ModelAndView welcome(Map<String, Object> model) {
//		model.put("greeting", "Welcome to Web Store!");
//		model.put("tagline", "The one and only amazing web store");
//		View view = new InternalResourceView("/WEB-INF/views/welcome.jsp");
//		return new ModelAndView(view, model);
//	}

	@RequestMapping("/admin")
	public String admin_login() {
		return "back_admin/login";
	}

}