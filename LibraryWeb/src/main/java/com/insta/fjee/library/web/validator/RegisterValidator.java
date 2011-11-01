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
				new Object[] {"login"}, "");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "errors.required",
				new Object[] {"password"}, "");
		
	System.out.println(errors.hasErrors());
	System.out.println("Firstname : " +user.getFirstName());
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errors.required",
				new Object[] {"firstName"}, "");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errors.required",
				new Object[] {"lastName"}, "");
		
		if (errors.hasErrors()) {
			
		}

	}

}
