package com.insta.fjee.library.service;


import javax.jws.WebService;

@WebService
public interface IUserService {

   public String getUser(String name);

}
