package com.insta.fjee.library.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insta.fjee.library.core.dao.impl.RentBookDAO;
import com.insta.fjee.library.core.dao.impl.UserDAO;
import com.insta.fjee.library.core.dto.RentBookDTO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginInvalidException;
import com.insta.fjee.library.core.model.RentBook;
import com.insta.fjee.library.core.model.User;

@Component("conv")
public class Conversion 
{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RentBookDAO rentBookDAO;
	
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	public void setRentBookDAO(RentBookDAO rentBookDAO)
	{
		this.rentBookDAO = rentBookDAO;
	}
	
	/**
	 * 	Transforme un objet utilisateur persistant (hibernate) en
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
	 * @throws LoginInvalidException 
	 */
	public User fromDTO(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException
	{
		User user = new User();
		Integer id = userDTO.getID();
		if (id>0) 
		{
			user = userDAO.findOrFail(id);
			/*
			 *	On verifie que le login n'a pas ete modifier par l'utilisateur 
			 */
			if (! user.getLogin().equals(userDTO.getLogin()))
			{
				throw new LoginInvalidException(userDTO.getLogin());
			}
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
	
	/**
	 * 	Transforme un objet location persistant (hibernate) en
	 * 	un objet DTO (transmis dans les services web)
	 * 
	 * @param rentBook
	 * @return
	 */
	public RentBookDTO fromEntity(RentBook rentBook)
	{
		RentBookDTO result = new RentBookDTO();
		result.setID(rentBook.getID());
		result.setIsbn(rentBook.getIsbn());
		result.setStartDate(rentBook.getStartDate());
		result.setEndDate(rentBook.getEndDate());		
		result.setLogin(rentBook.getUser().getLogin());
		return result;
	}
	
	/**
	 * 	Transforme un objet DTO (objet transmis par les services web)
	 * 		en objet persistant (hibernante)
	 * 
	 * @param rentBookDTO
	 * @return
	 * @throws EntityNotFoundException
	 */
	public RentBook fromDTO(RentBookDTO rentBookDTO) throws EntityNotFoundException
	{
		RentBook result = new RentBook();
		Integer id = rentBookDTO.getID();
		if (id>0) {
			result = rentBookDAO.findOrFail(id);
		}
		else {
			result = new RentBook();
			/** Ces informations ne peuvent pas etre modifié **/
			result.setIsbn(rentBookDTO.getIsbn());
			result.setStartDate(rentBookDTO.getStartDate());
			result.setUser(userDAO.findByLogin(rentBookDTO.getLogin()));
		}
		result.setEndDate(rentBookDTO.getEndDate());
		
		return result;
	}
	
	public List<RentBookDTO> fromEntity(List<RentBook> rentBooks)
	{
		List<RentBookDTO> result = new ArrayList<RentBookDTO>();
		for (RentBook rent : rentBooks) {
			result.add(fromEntity(rent));
		}
		return result;
	}
}
