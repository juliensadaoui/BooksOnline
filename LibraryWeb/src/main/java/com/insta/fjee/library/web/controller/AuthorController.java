package com.insta.fjee.library.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.web.util.WebServicesAccess;

@Controller
@SessionAttributes
public class AuthorController
{
	@Autowired
	private WebServicesAccess servicesAccess;
	
	public void setServicesAccess(WebServicesAccess servicesAccess) {
		this.servicesAccess = servicesAccess;
	}
	
	@RequestMapping("/searchauthors")
	public String executeShow(ModelMap model)
	{
		model.addAttribute("search", "author");
		return "searchauthors";
	}
	
	@RequestMapping(value="/searchauthorbyfirstname", method=RequestMethod.POST)
	public String executeSearchByFirstName(
			@RequestParam("author_firstname") String firstName,
			ModelMap model)
	{
		List<AuthorDTO> authors = servicesAccess.getUserService().searchAuthorByFirstName(firstName);
		model.addAttribute("search", "author");
		model.addAttribute("criteria", firstName);
		model.addAttribute("authors", authors);
		return "authors";
	}
	
	@RequestMapping(value="/searchauthorbylastname", method=RequestMethod.POST)
	public String executeSearchByLastName(
			@RequestParam("author_lastname") String lastName,
			ModelMap model)
	{
		List<AuthorDTO> authors = servicesAccess.getUserService().searchAuthorByLastName(lastName);
		model.addAttribute("search", "author");
		model.addAttribute("criteria", lastName);
		model.addAttribute("authors", authors);
		return "authors";
	}
	
	@RequestMapping(value="/searchauthorbybook", method=RequestMethod.POST)
	public String executeSearchByBookName(
			@RequestParam("book_title") String bookName,
			ModelMap model)
	{
		List<AuthorDTO> authors = servicesAccess.getUserService().searchAuthorByBookName(bookName);
		model.addAttribute("search", "author");
		model.addAttribute("criteria", bookName);
		model.addAttribute("authors", authors);
		return "authors";
	}
}
