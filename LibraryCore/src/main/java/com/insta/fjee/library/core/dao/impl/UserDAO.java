package com.insta.fjee.library.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insta.fjee.library.core.dao.IUserDAO;
import com.insta.fjee.library.core.model.User;
import com.insta.fjee.library.core.exception.EntityNotFoundException;

/***
 * Data Access Object
 * 
 * A Stock DAO interface and implementation. In last tutorial, you DAO classes are directly extends the â€œHibernateDaoSupportâ€œ, but itâ€™s not possible to do it in annotation mode, because you have no way to auto wire the session Factory bean from your DAO class. The workaround is create a custom class (CustomHibernateDaoSupport) and extends the â€œHibernateDaoSupportâ€� and auto wire the session factory, and your DAO classes extends this class.
 * 
 * @author julien
 *
 */
@Repository("userDAO")
public class UserDAO implements IUserDAO
{
	// avec spring 3.1+

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @See {@link IUserDAO}
	 */
	// @Transactional
	@Override
	public User save(User user) {
		Transaction tx= null;
		try 
		{
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(user);
			tx.commit();
			return user;
		} 
		catch (RuntimeException e) 
		{
			if(tx!=null)
			{
				tx.rollback();
			}
			throw e;
		}
	}

	/**
	 * @See {@link IUserDAO}
	 */
	@Override
	public User update(User user) {
		Transaction tx= null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			return user;
		} catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
	}

	/**
	 * @See {@link IUserDAO}
	 */
	@Override
	public void delete(User user) {
		Transaction tx= null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
		
	}

	/**
	 * @See {@link IUserDAO}
	 */
	@Override
	public User find(Integer id) 
	{
		User user = null;
		Transaction tx= null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			tx.commit();
		} 
		catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
		
		return user;		
	}
	
	/**
	 * @See {@link IUserDAO}
	 */
	@Override
	public User findOrFail(Integer id) throws EntityNotFoundException
	{
		User u = find(id);
		if (u == null) {
			throw new EntityNotFoundException(User.class, id);
		}
		return u;
	}

	/**
	 * @See {@link IUserDAO}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findByLoginAndPassword(String login, String password) 
	{
		User user = null;
		Transaction tx= null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from User u where u.login=? and u.password=?");
			query.setString(0, login);
			query.setString(1, password);
			List<User> list = query.list();
			if (! list.isEmpty()) { // fix
				user = list.get(0);
			}
			tx.commit();
			
		} catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
		
		return user;
	}

	/**
	 * @See {@link IUserDAO}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findByLogin(String login) 
	{
		User user = null;
		Transaction tx = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from User u where u.login=? ");
			query.setString(0, login);
			List<User> list = query.list();
			if (! list.isEmpty()) { // fix
				user = list.get(0);
			}
			tx.commit();

		} 
		catch (RuntimeException e) {
			if(tx!=null){
				tx.rollback();
			}
			throw e;
		}
		
		return user;
	}

}
