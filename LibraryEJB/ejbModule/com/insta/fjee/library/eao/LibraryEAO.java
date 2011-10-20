package com.insta.fjee.library.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.insta.fjee.library.entity.Author;
import com.insta.fjee.library.entity.Book;
import com.insta.fjee.library.exception.BookNotFoundException;
import com.insta.fjee.library.exception.EntityNotFoundException;
import com.insta.fjee.library.util.jpa.Entity;

/**
 * Session Bean implementation class LibraryEAO
 */
@Stateless
@LocalBean
public class LibraryEAO implements ILibraryEAO 
{
	@PersistenceContext
	EntityManager em;

	public LibraryEAO() {
	}

	// for unit test
	public LibraryEAO(EntityManager em) {
		this.em = em;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#countCountries()
	 */
	@Override
	public long countBooks()
	{
		long result;
		Query q = em.createQuery("select count(bo) from Book bo");
		result = (Long) q.getSingleResult();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findAuthorByFirstName()
	 */
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
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findAuthorByLastName()
	 */
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
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findAuthorByBookName()
	 */
	@Override
	public List<Author> findAuthorByBookName(String bookName) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT a FROM Author a INNER JOIN a.books b WHERE b.name LIKE :pattern";
		Query query = em.createQuery(ejbql, Author.class);
		query.setParameter("pattern", "%" + bookName + "%");
		return query.getResultList();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findOrFail()
	 */
	@Override
	public <T extends Entity> T findOrFail(Class<T> clazz, Integer id) throws EntityNotFoundException
	{
		T e = em.find(clazz, id);
		if (e == null) {
			throw new EntityNotFoundException(clazz.getClass(), id);
		}
		return e;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#persist()
	 */
	@Override
	public <T extends Entity> void persist(T entity)
	{
		if (entity.hasId()) {
			em.merge(entity);
		}
		else {
			em.persist(entity);
			if (entity.getId() == null) {
				em.flush();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findBookByISBN()
	 */
	@Override
	public Book findBookByISBN(String isbn) throws BookNotFoundException {
		// run an EJBQL query
		try {
			String ejbql = "SELECT b FROM Book b WHERE b.isbn = :isbn";
			Query query = em.createQuery(ejbql, Book.class);
			query.setParameter("isbn", isbn);
			return (Book) query.getSingleResult();
		} catch (NoResultException e) {
			throw new BookNotFoundException(isbn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findBookByName()
	 */
	@Override
	public List<Book> findBookByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findBookByGenre()
	 */
	@Override
	public List<Book> findBookByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.insta.formation.countries.eao.ILibraryEAO#findBookByAuthor()
	 */
	@Override
	public List<Book> findBookByAuthor(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
}
