package com.insta.fjee.library.core.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.insta.fjee.library.core.dao.impl.UserDAO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.model.User;

public class Conversion 
{
	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
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
	
	public User fromDTO(UserDTO userDTO) throws EntityNotFoundException
	{
		User user = new User();
		Integer id = userDTO.getID();
		if (id>0) 
		{
			user = userDAO.findOrFail(id);
		}
		else 
		{
			user = new User();
		}
		
		user.setFirstName(userDTO.getFirstName());
		user.setID(userDTO.getID());
		user.setLastName(userDTO.getLastName());
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		return user;

	}
}
