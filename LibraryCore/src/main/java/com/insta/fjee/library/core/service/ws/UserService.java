package com.insta.fjee.library.core.service.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.service.IUserService;
import com.insta.fjee.library.stock.service.AuthorDTO;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.LibraryBeanService;

//@WebService(endpointInterface = "com.insta.fjee.library.service.UserServiceWS")
//You're placing those attributes in the SEI (service endpoint interface, which is autogenerated 
//by wsdl2java), which is incorrect--they are supposed to go with the SEI *implementation* 
//(the class that implements it). 
@WebService
public class UserService implements IUserService 
{
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private LibraryBeanService libraryBeanService;
	
	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public List<AuthorDTO> searchAuthorByLastName(String lastName) 
	{
		return libraryBeanService.getLibraryBeanPort().searchAuthorByLastName(lastName);
	}

	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public List<AuthorDTO> searchAuthorByFirstName(String firstName) 
	{
		return libraryBeanService.getLibraryBeanPort().searchAuthorByFirstName(firstName);
	}

	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public List<AuthorDTO> searchAuthorByBookName(String bookName) 
	{
		return libraryBeanService.getLibraryBeanPort().searchAuthorByBookName(bookName);
	}

	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public List<BookDTO> searchBookByName(String name) 
	{
		return libraryBeanService.getLibraryBeanPort().searchBookByName(name);
	}

	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public List<BookDTO> searchBookByGenre(String genre) 
	{
		return libraryBeanService.getLibraryBeanPort().searchBookByGenre(genre);
	}

	/**
	 *  @See {@link IUserService}
	 */
	@Override
	public List<BookDTO> searchBookByAuthor(String lastName, String firstName) 
	{
		return libraryBeanService.getLibraryBeanPort().searchBookByAuthor(lastName, firstName);
	}
}
