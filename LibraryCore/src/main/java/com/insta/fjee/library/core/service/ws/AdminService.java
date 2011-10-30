package com.insta.fjee.library.core.service.ws;

import java.util.List;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.insta.fjee.library.core.bo.IRentBookBO;
import com.insta.fjee.library.core.bo.IUserBO;
import com.insta.fjee.library.core.dto.RentBookDTO;
import com.insta.fjee.library.core.dto.UserDTO;
import com.insta.fjee.library.core.exception.EntityNotFoundException;
import com.insta.fjee.library.core.exception.LoginInvalidException;
import com.insta.fjee.library.core.exception.UserNotAdminException;
import com.insta.fjee.library.core.service.IAdminService;


@WebService
public class AdminService implements IAdminService
{   
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IRentBookBO rentBookBO;
	
	/**
	 *  @See {@link IAdminService}
	 */
	@Override
	public UserDTO authentificate(String login, String password)
	{
		return userBO.authentificateAdmin(login, password);
	}


	/**
	 *  @See {@link IAdminService}
	 */
	@Override
	public void deleteUser(UserDTO userDTO, UserDTO adminDTO) throws UserNotAdminException, EntityNotFoundException, LoginInvalidException
	{
		if (userBO.isAdmin(adminDTO)) {
			userBO.deleteUser(userDTO);
		}
		else {
			throw new UserNotAdminException(adminDTO.getLogin());
		}
	}
	
	/**
	 *  @See {@link IAdminService}
	 */
	@Override
	public void deleteRentBook(RentBookDTO rentBookDTO, UserDTO adminDTO) 
		throws UserNotAdminException, EntityNotFoundException, LoginInvalidException
	{
		if (userBO.isAdmin(adminDTO)) {
			rentBookBO.deleteRentBook(rentBookDTO);
		}
		else {
			throw new UserNotAdminException(adminDTO.getLogin());
		}
		
	}

	/**
	 *  @See {@link IAdminService}
	 */
	@Override
	public List<UserDTO> getAllUsers(UserDTO adminDTO)
			throws UserNotAdminException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  @See {@link IAdminService}
	 */
	@Override	
	public List<RentBookDTO> getAllRents(UserDTO adminDTO)			
		throws UserNotAdminException {
		// TODO Auto-generated method stub
		return null;
	}
}
