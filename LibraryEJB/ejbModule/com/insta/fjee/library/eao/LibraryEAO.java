package com.insta.fjee.library.eao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.insta.fjee.library.entity.Author;

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
	 * @see com.insta.formation.countries.eao.ICountriesEAO#countCountries()
	 */
	@Override
	public long countBooks()
	{
		long result;
		Query q = em.createQuery("select count(bo) from Book bo");
		result = (Long) q.getSingleResult();
		return result;
	}

	@Override
	public List<Author> findAuthorByFirstName(String firstName) {
		// run an EJBQL query using input parameters
		String ejbql = "SELECT a FROM Author a WHERE a.firstName LIKE :pattern";
		Query query = em.createQuery(ejbql, Author.class);
		query.setParameter("pattern", "%" + firstName + "%");
		return query.getResultList();
	}

}
