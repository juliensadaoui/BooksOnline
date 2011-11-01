package com.insta.fjee.library.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.web.util.WebServicesAccess;

@Controller
@SessionAttributes
public class BookController 
{
	@Autowired
	private WebServicesAccess servicesAccess;
	
	public void setServicesAccess(WebServicesAccess servicesAccess) {
		this.servicesAccess = servicesAccess;
	}
	
	@RequestMapping("/searchbookbyname")
	public String executeSearchByName(
			@RequestParam("name") String name,
			ModelMap model)
	{
		List<BookDTO> books = servicesAccess.getUserService().searchBookByName(name);
		model.addAttribute("books", books);
		return "books";
	}
	
	@RequestMapping("/searchbookbygenre")
	public String executeSearchByGenre(
			@RequestParam("genre") String genre,
			ModelMap model)
	{
		List<BookDTO> books = servicesAccess.getUserService().searchBookByGenre(genre);
		model.addAttribute("books", books);
		return "books";
	}
}
