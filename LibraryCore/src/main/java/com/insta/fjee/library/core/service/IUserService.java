package com.insta.fjee.library.core.service;

import javax.jws.WebService;

@WebService
public interface IUserService {

   public String hello(String name);

   public String getBookName(String isbn);
}
