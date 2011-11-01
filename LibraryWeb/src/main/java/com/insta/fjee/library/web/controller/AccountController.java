package com.insta.fjee.library.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.insta.fjee.library.core.service.UserDTO;
import com.insta.fjee.library.core.service.ws.LoginAlreadyExistException;
import com.insta.fjee.library.web.bean.UserBean;
import com.insta.fjee.library.web.util.Conversion;
import com.insta.fjee.library.web.util.WebServicesAccess;
import com.insta.fjee.library.web.validator.RegisterValidator;

@Controller
@SessionAttributes
public class AccountController 
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
				return "register_ok";
			} catch (LoginAlreadyExistException e) {
				result.rejectValue("login", "error.loginalreadyexist",
						new Object[] {userBean.getLogin()}, "");
			}
			
		}
		return "register";
	}
	
}
