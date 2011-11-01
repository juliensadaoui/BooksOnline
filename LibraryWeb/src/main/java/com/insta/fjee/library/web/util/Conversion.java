package com.insta.fjee.library.web.util;

import org.springframework.stereotype.Component;

import com.insta.fjee.library.core.service.UserDTO;
import com.insta.fjee.library.web.bean.UserBean;

@Component("conversion")
public class Conversion 
{
	public UserDTO fromBean(UserBean userBean)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setLogin(userBean.getLogin());
		userDTO.setPassword(userBean.getPassword());
		userDTO.setFirstName(userBean.getFirstName());
		userDTO.setLastName(userBean.getLastName());
		userDTO.setAdmin(false);
		return userDTO;
	}
	
	public UserBean fromDTO(UserDTO userDTO)
	{
		UserBean userBean = new UserBean();
		userBean.setLogin(userDTO.getLogin());
		userBean.setPassword(userDTO.getPassword());
		userBean.setFirstName(userDTO.getFirstName());
		userBean.setLastName(userDTO.getLastName());
		return userBean;
	}
}
