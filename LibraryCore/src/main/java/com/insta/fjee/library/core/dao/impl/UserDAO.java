package com.insta.fjee.library.core.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.insta.fjee.library.core.dao.IUserDAO;
import com.insta.fjee.library.core.model.User;

/***
 * Data Access Object
 * 
 * A Stock DAO interface and implementation. In last tutorial, you DAO classes are directly extends the “HibernateDaoSupport“, but it’s not possible to do it in annotation mode, because you have no way to auto wire the session Factory bean from your DAO class. The workaround is create a custom class (CustomHibernateDaoSupport) and extends the “HibernateDaoSupport” and auto wire the session factory, and your DAO classes extends this class.
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
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();	
	}

	@Override
	public void update(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
	}

	@Override
	public void delete(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		
	}

	@Override
	public User find(Integer id) {
		// TODO Auto-generated method stub
		return null;
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

}
