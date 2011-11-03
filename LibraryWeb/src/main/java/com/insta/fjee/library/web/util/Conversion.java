package com.insta.fjee.library.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insta.fjee.library.core.service.RentBookDTO;
import com.insta.fjee.library.core.service.UserDTO;
import com.insta.fjee.library.core.service.ws.BookNotFoundExceptionException;
import com.insta.fjee.library.stock.service.BookDTO;
import com.insta.fjee.library.web.bean.RentBookBean;
import com.insta.fjee.library.web.bean.UserBean;

@Component("conversion")
public class Conversion 
{
	@Autowired
	private WebServicesAccess servicesAccess;
	
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
	
	public RentBookBean fromDTO(RentBookDTO rentBookDTO) throws BookNotFoundExceptionException
	{
		RentBookBean rentBookBean = new RentBookBean();
		rentBookBean.setId(rentBookDTO.getID());
		rentBookBean.setIsbn(rentBookDTO.getIsbn());
		
		if (rentBookDTO.getStartDate() != null) {
			rentBookBean.setStartDate(rentBookDTO.getStartDate().toGregorianCalendar().getTime());
		}
		if (rentBookDTO.getEndDate() != null) {
			rentBookBean.setEndDate(rentBookDTO.getStartDate().toGregorianCalendar().getTime());
		}
		BookDTO bookDTO = servicesAccess.getUserService().findBookByISBN(rentBookDTO.getIsbn());	
		rentBookBean.setBookName(bookDTO.getName());
		return rentBookBean;
	}
}
