package com.insta.fjee.library.core.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.insta.fjee.library.core.dao.IRentBookDAO;
import com.insta.fjee.library.core.model.RentBook;

public class RentBookDAO implements IRentBookDAO
{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public RentBook rentBook(RentBook rentBook) 
	{
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(rentBook);
		return rentBook;
	}

	@Override
	public RentBook returnBook(RentBook rentBook) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(rentBook);
		return rentBook;
	}

}
