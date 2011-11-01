package com.insta.fjee.library.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.insta.fjee.library.core.service.ws.ISubscriberService;
import com.insta.fjee.library.core.service.ws.IUserService;
import com.insta.fjee.library.core.service.ws.SubscriberServiceService;
import com.insta.fjee.library.core.service.ws.UserServiceService;

@Component("servicesAccess")
public class WebServicesAccess 
{
	@Autowired
	private SubscriberServiceService subscriberService;
	
	@Autowired
	private UserServiceService userService;

	public void setSubscriberService(SubscriberServiceService subscriberService) {
		this.subscriberService = subscriberService;
	}

	public void setUserService(UserServiceService userService) {
		this.userService = userService;
	}
	
	/**
	 * 	Retourne le point d'entré du service web des visiteurs
	 * 
	 * @return point d'entrée du services web des visiteurs
	 */
	public IUserService getUserService() {
		return userService.getUserServicePort();
	}
	
	/**
	 * 	Retourne le point d'entré du service web des abonné
	 * 
	 * @return
	 */
	public ISubscriberService getSubscriberService() {
		return subscriberService.getSubscriberServicePort();
	}
}
