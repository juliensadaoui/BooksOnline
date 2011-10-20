package com.insta.fjee.library.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.exception.EntityNotFoundException;

public class AuthorEAO implements IAuthorEAO
{
	@PersistenceContext
	EntityManager em;

	public AuthorEAO() {
	}

	// for unit test
	public AuthorEAO(EntityManager em) {
		this.em = em;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#find()
	 */
	@Override
	public Author find(Integer id) {
		return em.find(Author.class, id);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#findOrFail()
	 */
	@Override
	public Author findOrFail(Integer id) throws EntityNotFoundException
	{
		Author a = find(id);
		if (a == null) {
			throw new EntityNotFoundException(Author.class, id);
		}
		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#saveOrUpdate()
	 */
	@Override
	public void saveOrUpdate(Author author) {
		em.getTransaction().begin();
		if (find(author.getId()) != null) {
			em.merge(author);
		}
		else {
			em.persist(author);
			if (author.getId() == null) {
				em.flush();
			}
		}
		em.getTransaction().commit();
	}

	@Override
	public void delete(Author author) {
		em.getTransaction().begin();
		em.remove(author);
		em.getTransaction().commit();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#findAuthorByFirstName()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Author> findAuthorByFirstName(String firstName) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT a FROM Author a WHERE a.firstName LIKE :pattern";
		Query query = em.createQuery(ejbql, Author.class);
		query.setParameter("pattern", "%" + firstName + "%");
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#findAuthorByLastName()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Author> findAuthorByLastName(String lastName) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT a FROM Author a WHERE a.lastName LIKE :pattern";
		Query query = em.createQuery(ejbql, Author.class);
		query.setParameter("pattern", "%" + lastName + "%");
		return query.getResultList();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.IAuthorEAO#findAuthorByBookName()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Author> findAuthorByBookName(String bookName) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT a FROM Author a INNER JOIN a.books b WHERE b.name LIKE :pattern";
		Query query = em.createQuery(ejbql, Author.class);
		query.setParameter("pattern", "%" + bookName + "%");
		return query.getResultList();
	}
}
