package com.insta.fjee.library.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.insta.fjee.library.core.service.RentBookDTO;
import com.insta.fjee.library.core.service.UserDTO;
import com.insta.fjee.library.core.service.ws.BookNotFoundExceptionException;
import com.insta.fjee.library.core.service.ws.EntityNotFoundException;
import com.insta.fjee.library.core.service.ws.LoginInvalidException;
import com.insta.fjee.library.core.service.ws.NotEnoughtExemplaryException;
import com.insta.fjee.library.web.bean.RentBookBean;
import com.insta.fjee.library.web.util.Conversion;
import com.insta.fjee.library.web.util.WebServicesAccess;

@Controller
@SessionAttributes
public class RentController
{
	private Logger LOGGER = LoggerFactory.getLogger(RentController.class);
	
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
		
		if (userDTO == null) {
			return "redirect:j_spring_security_logout";
		}
		model.addAttribute("book_name", name);
		
		// on essayer de louer un livre 
		try {
			servicesAccess.getSubscriberService().rentBook(userDTO, isbn);
			model.addAttribute("account", "rent");
			return "rentbook_ok";
			
		} catch (BookNotFoundExceptionException e) {
			LOGGER.error(e.getMessage());
		} catch (EntityNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (LoginInvalidException e) {
			LOGGER.error(e.getMessage());
		} catch (NotEnoughtExemplaryException e) {
			LOGGER.error(e.getMessage());
		}
		
		return "rentbook_fail";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showrentbooks")
	public String executeShow(ModelMap model)
	{
		// recupere les informations de l'utilisateur en session
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDTO userDTO = servicesAccess.getSubscriberService().authentificate(
				userDetails.getUsername(), userDetails.getPassword());
		
		if (userDTO == null) {
			return "redirect:j_spring_security_logout";
		}
		
		try {
			List<RentBookDTO> rentsDTO = servicesAccess.getSubscriberService().getAllRents(userDTO);
			List<RentBookBean> rentsBean = new ArrayList<RentBookBean>();
			
			for (RentBookDTO rentBookDTO : rentsDTO)
			{
				try {
					rentsBean.add(conversion.fromDTO(rentBookDTO));
				} catch (BookNotFoundExceptionException e) {
					LOGGER.error(e.getMessage());
				}
			}
			
			model.addAttribute("account", "rent");
			model.addAttribute("rents", rentsBean);
			return "rentbooks";
			
		} catch (EntityNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (LoginInvalidException e) {
			LOGGER.error(e.getMessage());
		}
		
		return "error";
	}

}
