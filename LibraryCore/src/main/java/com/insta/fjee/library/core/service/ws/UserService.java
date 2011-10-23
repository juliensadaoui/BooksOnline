package com.insta.fjee.library.core.service.ws;

import javax.jws.WebService;

import com.insta.fjee.library.core.bo.impl.UserBOImpl;
import com.insta.fjee.library.core.service.IUserService;

//@WebService(endpointInterface = "com.insta.fjee.library.service.UserServiceWS")
//You're placing those attributes in the SEI (service endpoint interface, which is autogenerated 
//by wsdl2java), which is incorrect--they are supposed to go with the SEI *implementation* 
//(the class that implements it). 
@WebService
public class UserService implements IUserService {

	@Override
	public String hello(String name) {
		
		return "Hello " + name;
	}

	@Override
	public String getBookName(String isbn) {
		return (new UserBOImpl()).getName(isbn);
	}
	
	

}
