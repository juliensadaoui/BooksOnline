package com.insta.fjee.library.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.insta.fjee.library.web.bean.UserBean;

public class RegisterValidator implements Validator {

	public boolean supports(Class<?> obj) {
		return UserBean.class.equals(obj);
	}

	public void validate(Object obj, Errors errors) 
	{
		UserBean user = (UserBean) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "errors.required",
				new Object[] {"nom utilisateur"}, "");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required",
				new Object[] {"mot de passe"}, "");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verifyPassword", "errors.required",
				new Object[] {"mot de passe"}, "");

		if (! user.getPassword().equals(user.getVerifyPassword()))
		{
			errors.rejectValue("verifyPassword", "error.passwordnotsame",
					null, "");
					
		}
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.required",
				new Object[] {"prénom"}, "");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.required",
				new Object[] {"nom"}, "");
		
		if (errors.hasErrors()) {
			
		}

	}

}
