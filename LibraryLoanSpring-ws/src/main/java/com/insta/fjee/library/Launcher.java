package com.insta.fjee.library;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.insta.fjee.library.service.IUserService;

/**
 * Client permettant de saisir une commande et d'envoyer la demande
 * 
 * @author julien
 * 
 */
public class Launcher
{

	/**
	 * Point d'entrée de l'application
	 * 
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException
	{
		// call WS
		URL url = new URL("http://achille:8080/LibraryLoanSpring-ws/userService?wsdl");
		QName qname = new QName("http://service.library.fjee.insta.com/", "UserServiceWSService");

		// Création d'une fabrique pour le WS
		Service service = Service.create(url, qname);

		// Récupération Proxy pour accéder aux méthodes
		IUserService server = service.getPort(IUserService.class);
		
		System.out.println(server.getUser("pp"));

	}

}