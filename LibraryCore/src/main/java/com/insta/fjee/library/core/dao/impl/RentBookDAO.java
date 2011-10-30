package com.insta.fjee.library.core.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insta.fjee.library.core.dao.IRentBookDAO;
import com.insta.fjee.library.core.model.RentBook;
import com.insta.fjee.library.core.service.IUserService;

/***
 * Data Access Object
 * 
 * A Stock DAO interface and implementation. In last tutorial, you DAO classes are directly extends the â€œHibernateDaoSupportâ€œ, but itâ€™s not possible to do it in annotation mode, because you have no way to auto wire the session Factory bean from your DAO class. The workaround is create a custom class (CustomHibernateDaoSupport) and extends the â€œHibernateDaoSupportâ€� and auto wire the session factory, and your DAO classes extends this class.
 * 
 * @author julien
 *
 */
@Repository("rentBookDAO")
public class RentBookDAO implements IRentBookDAO
{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 *  @See {@link IRentBookDAO}
	 */
	@Override
	public RentBook save(RentBook rentBook) 
	{
		Transaction tx = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.save(rentBook);
			tx.commit();
			return rentBook;
		} catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
	}

	/**
	 *  @See {@link IRentBookDAO}
	 */
	@Override
	public RentBook update(RentBook rentBook)
	{
		Transaction tx = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(rentBook);
			tx.commit();
			return rentBook;
		} catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
	}

	/**
	 *  @See {@link IRentBookDAO}
	 */
	@Override
	public void delete(RentBook rentBook) 
	{
		Transaction tx = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.delete(rentBook);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
	}

}
