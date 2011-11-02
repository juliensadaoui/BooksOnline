package com.insta.fjee.library.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.insta.fjee.library.core.service.RentBookDTO;
import com.insta.fjee.library.core.service.UserDTO;
import com.insta.fjee.library.core.service.ws.BookNotFoundExceptionException;
import com.insta.fjee.library.core.service.ws.EntityNotFoundException;
import com.insta.fjee.library.core.service.ws.LoginInvalidException;
import com.insta.fjee.library.core.service.ws.NotEnoughtExemplaryException;
import com.insta.fjee.library.web.util.Conversion;
import com.insta.fjee.library.web.util.WebServicesAccess;

@Controller
@SessionAttributes
public class RentController
{
	@Autowired
	private WebServicesAccess servicesAccess;
	
	@Autowired
	private Conversion conversion;
	
	public void setServicesAccess(WebServicesAccess servicesAccess) {
		this.servicesAccess = servicesAccess;
	}

	public void setConversion(Conversion conversion) {
		this.conversion = conversion;
	}

//	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="/rentbook", method=RequestMethod.POST)
	public String executeRentBook(
			@RequestParam("book_isbn") String isbn,
			@RequestParam("book_title") String name,
			ModelMap model)
	{
		// recupere les informations de l'utilisateur en session
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDTO userDTO = servicesAccess.getSubscriberService().authentificate(
				userDetails.getUsername(), userDetails.getPassword());
		
		// on essayer de louer un livre 
		try {
			servicesAccess.getSubscriberService().rentBook(userDTO, isbn);
			model.addAttribute("book_name", name);
			return "rentbook_ok";
			
		} catch (BookNotFoundExceptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotEnoughtExemplaryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
