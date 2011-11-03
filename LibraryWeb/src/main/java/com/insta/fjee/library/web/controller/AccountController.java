package com.insta.fjee.library.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.insta.fjee.library.core.service.UserDTO;
import com.insta.fjee.library.core.service.ws.EntityNotFoundException;
import com.insta.fjee.library.core.service.ws.LoginAlreadyExistException;
import com.insta.fjee.library.core.service.ws.LoginInvalidException;
import com.insta.fjee.library.web.bean.UserBean;
import com.insta.fjee.library.web.util.Conversion;
import com.insta.fjee.library.web.util.WebServicesAccess;
import com.insta.fjee.library.web.validator.RegisterValidator;

@Controller
@SessionAttributes
public class AccountController 
{
	private Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
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

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	/**
	* Execute l'action 'new' du controller 'Account'. Affiche le formulaire
	* d'inscription pour un utilisateur.
	*
	* @param sfWebRequest $request
	*/
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView executeNew(ModelMap model)
	{
		model.addAttribute("userBean", new UserBean());
		return new ModelAndView("register");
	}
	
	/**
	 * 
	 * 
	 * @param userBean
	 * @param result
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String exectueCreate(
		@ModelAttribute("userBean") UserBean userBean,
		BindingResult result, SessionStatus status, ModelMap model) {
		

		(new RegisterValidator()).validate(userBean, result);
		
		// on verifie si il a des erreurs dans la saisie
		if (!result.hasErrors()) {
			model.addAttribute("login", userBean.getLogin());
			UserDTO userDTO = conversion.fromBean(userBean);
			try {
				servicesAccess.getUserService().createUser(userDTO);
				status.setComplete();
				return "register_ok";
			} catch (LoginAlreadyExistException e) {
				result.rejectValue("login", "error.loginalreadyexist",
						new Object[] {userBean.getLogin()}, "");
			}
			
		}
		return "register";
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showaccount")
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
		model.addAttribute("account", "info");
		model.addAttribute("user", userDTO);
		return "showaccount";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editaccount", method = RequestMethod.GET)
	public String executeEdit(ModelMap model)
	{
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDTO userDTO = servicesAccess.getSubscriberService().authentificate(
				userDetails.getUsername(), userDetails.getPassword());

		if (userDTO == null) {
			return "redirect:j_spring_security_logout";
		}
		
		UserBean userBean = conversion.fromDTO(userDTO);
		model.addAttribute("account", "info");
		model.addAttribute("user_id", userDTO.getID());
		model.addAttribute("userBean", userBean);
		return "editaccount";
	}
	
	/**
	 * 
	 * @param userBean
	 * @param result
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editaccount", method = RequestMethod.POST)
	public String executeUpdate(@RequestParam("user_id") String userID,
			@ModelAttribute("userBean") UserBean userBean,
			BindingResult result, SessionStatus status, ModelMap model) 
	{
		(new RegisterValidator()).validate(userBean, result);

		// on verifie si il a des erreurs dans la saisie
		if (!result.hasErrors()) {
			
			try {
				UserDTO userDTO = conversion.fromBean(userBean);
				userDTO.setID(Integer.parseInt(userID));

				servicesAccess.getSubscriberService().updateUser(userDTO);
				status.setComplete();
				return "redirect:showaccount.html";
				
			} catch (NumberFormatException e) {
				LOGGER.error("Modification de l'attribut user_id !");
			} catch (EntityNotFoundException e) {
				LOGGER.error("Modification de l'attribut user_id !");
			} catch (LoginAlreadyExistException e) {
				LOGGER.error("Modification de l'attribut login !");
			} catch (LoginInvalidException e) {
				LOGGER.error("Modification de l'attribut login !");
			}
			
		}

		model.addAttribute("account", "info");
		return "editaccount";
	}
	
}
