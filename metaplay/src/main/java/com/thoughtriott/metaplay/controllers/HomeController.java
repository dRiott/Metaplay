package com.thoughtriott.metaplay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String goMain (@RequestParam(value = "logout", required = false) String logout,
						  HttpSession session, Model model) {
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		return "index";
	}
	
	@RequestMapping("/about")
	public String getAbout () {
		return "about";
	}

}
