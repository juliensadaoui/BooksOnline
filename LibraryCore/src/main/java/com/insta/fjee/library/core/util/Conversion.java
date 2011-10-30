package com.insta.fjee.library.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insta.fjee.library.core.dao.impl.UserDAO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.model.User;

@Component("conv")
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
		userDTO.setAdmin(user.isAdmin());
		return userDTO;
	}
	
	/**
	 * 	Transforme un objet DTO (objet transmis par les services web)
	 * 		en objet persistant (hibernante)
	 * 
	 * @param userDTO - objet DTO
	 * @return
	 * @throws EntityNotFoundException
	 */
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
			/**
			 * 	L'identifiant et le login ne peut pas 
			 * 		etre modifié
			 */
			user.setID(userDTO.getID());
			user.setLogin(userDTO.getLogin());
		}
		
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(userDTO.getPassword());
		user.setAdmin(userDTO.isAdmin());
		return user;

	}
}
