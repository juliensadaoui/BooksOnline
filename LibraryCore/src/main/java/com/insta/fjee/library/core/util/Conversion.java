package com.insta.fjee.library.core.util;

import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.model.User;

public class Conversion 
{
	/**
	 * 	Convertit un objet utilisateur persistant (hibernate) en
	 * 	un objet DTO (transmis dans les services web)
	 * 
	 * @param user - objet persistant
	 * @return objet DTO
	 */
	public UserDTO fromEntity(User user)
	{
		UserDTO userDTO = new UserDTO();
		userDTO.setID(user.getID());
		userDTO.setLogin(user.getLogin());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		return userDTO;
	}
}
