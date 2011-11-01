package com.insta.fjee.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
public class HomeController 
{
	@RequestMapping("/home")
	public ModelAndView showHome() {
		return new ModelAndView("home");
	}
	
	@RequestMapping("/error")
	public ModelAndView showError() {
		return new ModelAndView("error");
	}
}
