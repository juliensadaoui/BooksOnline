package com.insta.fjee.library.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import com.insta.fjee.library.web.bean.Contact;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes
public class ContactController {

	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
							Contact contact, BindingResult result,
							HttpSession session, NativeWebRequest request) {
		
//		request.getNativeRequest();
		System.out.println("First Name:" + contact.getFirstname() + 
					"Last Name:" + contact.getLastname());
		
		
		return "redirect:contacts.html";
	}
	
	@RequestMapping("/contacts")
	public ModelAndView showContacts() {
		
		return new ModelAndView("contact", "command", new Contact());
	}
}
