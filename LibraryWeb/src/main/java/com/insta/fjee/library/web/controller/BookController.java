package com.insta.fjee.library.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("/searchbooks")
	public String executeShow(ModelMap model)
	{
		model.addAttribute("search", "book");
		return "searchbooks";
	}
	
	@RequestMapping(value="/searchbookbyname", method=RequestMethod.POST)
	public String executeSearchByName(
			@RequestParam("book_title") String name,
			ModelMap model)
	{
		List<BookDTO> books = servicesAccess.getUserService().searchBookByName(name);
		model.addAttribute("search", "book");
		model.addAttribute("criteria", name);
		model.addAttribute("books", books);
		return "books";
	}
	
	@RequestMapping(value="/searchbookbygenre", method=RequestMethod.POST)
	public String executeSearchByGenre(
			@RequestParam("book_genre") String genre,
			ModelMap model)
	{
		List<BookDTO> books = servicesAccess.getUserService().searchBookByGenre(genre);
		model.addAttribute("search", "book");
		model.addAttribute("criteria", genre);
		model.addAttribute("books", books);
		return "books";
	}
	// Liste des livres correspondant à votre recherche
	
	@RequestMapping(value="/searchbookbyauthor", method=RequestMethod.POST)
	public String executeSearchByAuthor(
			@RequestParam("author_firstname") String firstName,
			@RequestParam("author_lastname") String lastName,
			ModelMap model)
	{		
		List<BookDTO> books = servicesAccess.getUserService().searchBookByAuthor(lastName, firstName);
		model.addAttribute("criteria", firstName + " " + lastName);
		model.addAttribute("search", "book");
		model.addAttribute("books", books);
		return "books";
	}
}
