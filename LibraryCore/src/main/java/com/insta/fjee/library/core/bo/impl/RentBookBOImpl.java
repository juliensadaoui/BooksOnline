package com.insta.fjee.library.core.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.fjee.library.core.bo.IRentBookBO;
import com.insta.fjee.library.core.dao.IRentBookDAO;
import com.insta.fjee.library.core.dto.RentBookDTO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginInvalidException;
import com.insta.fjee.library.core.exception.NotEnoughtExemplaryException;
import com.insta.fjee.library.core.model.RentBook;
import com.insta.fjee.library.core.model.User;
import com.insta.fjee.library.core.util.Conversion;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.stock.service.BookNotFoundException_Exception;
import com.insta.fjee.library.stock.service.ExemplaryDTO;
import com.insta.fjee.library.stock.service.LibraryBeanService;

@Service("rentBookBO")
public class RentBookBOImpl implements IRentBookBO {
	
	@Autowired
	private IRentBookDAO rentBookDAO;
	
	@Autowired
	private LibraryBeanService libraryBeanService;
	
	@Autowired
	private Conversion conv;

	/**
	 * @See {@link IRentBookBO}
	 */
	@Override
	public RentBookDTO rentBook(UserDTO userDTO, String isbn)
		throws EntityNotFoundException, NotEnoughtExemplaryException, BookNotFoundException_Exception, LoginInvalidException
	{
		// recupere le nombre total d'exemplaires d'un livre
		ExemplaryDTO exemplaryDTO = libraryBeanService.getLibraryBeanPort().getExemplary(isbn);
		
		// recupere le nombre actuelle d'exemplaires loués
		long countRentBooks = rentBookDAO.countRentBooks(isbn);
		
		// verifie si un exemplaire du livre est disponible
		if (countRentBooks == exemplaryDTO.getNb())
		{
			BookDTO bookDTO = libraryBeanService.getLibraryBeanPort().findBookByISBN(isbn);
			throw new NotEnoughtExemplaryException("aucun exemplaire disponible pour le livre " + bookDTO.getName());
		}
		
		RentBook rentBook = new RentBook();
		rentBook.setIsbn(isbn);
		rentBook.setStartDate(new Date());
		rentBook.setUser(conv.fromDTO(userDTO));
		rentBook = rentBookDAO.save(rentBook);
		return conv.fromEntity(rentBook);
	}
	
	/**
	 * @See {@link IRentBookBO}
	 */
	@Override
	public void deleteRentBook(RentBookDTO rentBookDTO) throws EntityNotFoundException, LoginInvalidException
	{
		// fix lorsque l'identifiant est inferieur à 1
		if (rentBookDTO.getID() < 1) {
			throw new EntityNotFoundException(RentBook.class, rentBookDTO.getID());
		}
		RentBook rentBook = conv.fromDTO(rentBookDTO);
		rentBookDAO.delete(rentBook);
	}

	/**
	 * @See {@link IRentBookBO}
	 */
	@Override
	public List<RentBookDTO> getAllRents(UserDTO userDTO) throws EntityNotFoundException, LoginInvalidException 
	{
		User user = conv.fromDTO(userDTO);
		List<RentBook> rentBooks = rentBookDAO.getAllRents(user.getLogin());
		return conv.fromEntity(rentBooks);	
	}

	@Override
	public RentBookDTO returnBook(RentBookDTO rentBookDTO) throws EntityNotFoundException
	{
		RentBook rentBook = conv.fromDTO(rentBookDTO);
		// si le livre à deja était rendu, on ne prend pas en compte la demande
		if (rentBook.getEndDate() == null) {
			rentBook.setEndDate(new Date());
			rentBookDAO.update(rentBook);
		}
		return conv.fromEntity(rentBook);
	}
}
