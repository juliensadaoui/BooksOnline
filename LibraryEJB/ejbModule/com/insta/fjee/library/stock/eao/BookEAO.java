package com.insta.fjee.library.stock.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.insta.fjee.library.stock.entity.Author;
import com.insta.fjee.library.stock.entity.Book;
import com.insta.fjee.library.stock.exception.BookNotFoundException;
import com.insta.fjee.library.stock.exception.EntityNotFoundException;

@LocalBean
@Stateless
public class BookEAO implements IBookEAO
{
	@PersistenceContext
	EntityManager em;

	public BookEAO() {
	}

	// for unit test
	public BookEAO(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public long countBooks() {
		long result;
		Query q = em.createQuery("select count(bo) from Book bo");
		result = (Long) q.getSingleResult();
		return result;
	}
	
	@Override
	public long countBooks(String isbn) {
		Integer result;
		Query q = em.createQuery("select book.exemplary from Book book where book.isbn= :isbn");
		q.setParameter("isbn", isbn);
		result = (Integer) q.getSingleResult();
		return result.longValue();
	}

	@Override
	public void saveOrUpdate(Book book) {
		if (find(book.getId()) != null) {
			em.merge(book);
		}
		else {
			em.persist(book);
			if (book.getId() == null) {
				em.flush();
			}
		}
		
	}

	@Override
	public void delete(Book book)
	{
		em.remove(book);	
	}

	@Override
	public Book findOrFail(Integer id) throws EntityNotFoundException {
		Book b = find(id);
		if (b == null) {
			throw new EntityNotFoundException(Author.class, id);
		}
		return b;
	}

	@Override
	public Book find(Integer id) 
	{
		if (id == null) return null;
		return em.find(Book.class, id);
	}

	@Override
	public Book findBookByISBN(String isbn) throws BookNotFoundException {
		// run an EJBQL query
		try {
			String ejbql = "SELECT b FROM Book b WHERE lower(b.isbn) = :isbn";
			Query query = em.createQuery(ejbql, Book.class);
			query.setParameter("isbn", isbn.toLowerCase());
			return (Book) query.getSingleResult();
		} catch (NoResultException e) {
			throw new BookNotFoundException(isbn);
		}
	}

	@Override
	public List<Book> findBookByName(String name) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT b FROM Book b WHERE lower(b.name) LIKE :pattern";
		Query query = em.createQuery(ejbql, Book.class);
		query.setParameter("pattern", "%" + name.toLowerCase() + "%");
		return query.getResultList();
	}

	@Override
	public List<Book> findBookByGenre(String genre) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT b FROM Book b WHERE lower(b.genre) LIKE :pattern";
		Query query = em.createQuery(ejbql, Book.class);
		query.setParameter("pattern", "%" + genre.toLowerCase() + "%");
		return query.getResultList();
	}

	@Override
	public List<Book> findBookByAuthor(String firstName, String lastName) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT b FROM Book b INNER JOIN b.author a WHERE lower(a.firstName) LIKE :pattern1 AND lower(a.lastName) LIKE :pattern2 ";
		Query query = em.createQuery(ejbql, Book.class);
		query.setParameter("pattern1",  "%" + firstName.toLowerCase() + "%");
		query.setParameter("pattern2",  "%" + lastName.toLowerCase() + "%");
		return query.getResultList();
	}

}
