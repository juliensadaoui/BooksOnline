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

	@Override
	public User find(Integer id) 
	{
		Session session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#findOrFail()
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


	@Override
	public User findByLoginAndPassword(String login, String password) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where login=? and password=?");
		query.setString(0, login);
		query.setString(1, password);
		List<User> list = query.list();
		tx.commit();
		return list.get(0);
	}

	@Override
	public User findByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where login=? ");
		query.setString(0, login);
		List<User> list = query.list();
		tx.commit();
		return list.get(0);
	}

}
