package com.insta.fjee.library.core.bo.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dao.IUserDAO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginAlreadyExistException;
import com.insta.fjee.library.core.model.User;
import com.insta.fjee.library.core.util.Conversion;

/**
 * 
 * Stock Business Object (BO))
 * 
 * Stock business object (BO) interface and implementation, itâ€™s used to store
 * the projectâ€™s business function, the real database operations (CRUD) works 
 * should not involved in this class, instead it has a DAO (StockDao) class to do it.
 * 
 * @author julien
 *
 */
@Service("userBO")
public class UserBOImpl implements IUserBO
{
	/**
	 * 	Make this class as a bean â€œstockBoâ€� in Spring Ioc container, 
	 * 	and autowire the stock dao class.
	 */
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private Conversion conv;
	
	public void setUserDAO(IUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	/**
	 * @See {@link IUserBO}
	 */
	@Override
	public UserDTO authentificate(String login, String password) 
	{
		User user = userDAO.findByLoginAndPassword(login, password);
		return (user != null) ? conv.fromEntity(user) : null;
	}
	
	/**
	 * @See {@link IUserBO}
	 */
	@Override
	public UserDTO authentificateAdmin(String login, String password) 
	{
		User user = userDAO.findByLoginAndPassword(login, password);
		if ((user != null) && (user.isAdmin())) {
			return conv.fromEntity(user);
		}
		return null;
	}

	/**
	 * @See {@link IUserBO}
	 */
	@Override
	public UserDTO createUser(UserDTO userDTO) throws LoginAlreadyExistException 
	{
		/*
		 *	Verifie que le login n'est pas deja utilise par un
		 *		autre utilisateur de la librairie 
		 */
		String login = userDTO.getLogin();
		if (userDAO.findByLogin(login) != null) 
		{
			throw new LoginAlreadyExistException(login);
		}
		
		/*
		 * 	Ajoute l'utilisateur dans la base de données 
		 */
		try {
			userDTO.setID(0); 
			User user = conv.fromDTO(userDTO);
			user = userDAO.save(user);
			return conv.fromEntity(user);
		} catch (EntityNotFoundException e) {
			/*
			 * 	Cas particulier 
			 * 
			 * 	Il ne doit pas avoir d'identifiant de créer avant la création
			 * 	de l'utilisateur dans la base de données.
			 * 	Pour des raisons de sécurité, l'identifiant est toujours mis à zero.
			 * 	Si une exception se produit lors de la création de l'utilisateur, il y a
			 * 	un probleme quelque part ...
			 */
			 throw new RuntimeException();
		}
	}

	/**
	 * @See {@link IUserBO}
	 */
	@Override
	public UserDTO updateUser(UserDTO userDTO) throws EntityNotFoundException, LoginAlreadyExistException 
	{
		User user = conv.fromDTO(userDTO);
		/*
		 *	On verifie que le login n'a pas ete modifier par l'utilisateur 
		 */
		if (! user.getLogin().equals(userDTO.getLogin()))
		{
			throw new LoginAlreadyExistException(userDTO.getLogin());
		}
		
		/*
		 *	Mise à jour de l'utilisateur 
		 */
		user = userDAO.update(user);
		return conv.fromEntity(user);
	}
	
	/**
	 * @See {@link IUserBO}
	 */
	@Override
	public void deleteUser(UserDTO userDTO) throws EntityNotFoundException
	{
		User user = conv.fromDTO(userDTO);
		userDAO.delete(user);
	}
	
	/**
	 * @See {@link IUserBO}
	 */
	@Override
	public boolean isAdmin(UserDTO userDTO) throws EntityNotFoundException
	{
		User user = conv.fromDTO(userDTO);
		if (user.isAdmin()) {
			return true;
		}		
		return false;
	}
}
