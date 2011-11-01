package com.insta.fjee.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
public class AccountController 
{
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
}
