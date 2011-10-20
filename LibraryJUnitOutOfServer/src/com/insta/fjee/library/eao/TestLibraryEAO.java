package com.insta.fjee.library.eao;

import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;

import com.insta.fjee.library.junit.Util;

public class TestLibraryEAO {

	private EntityManager _em;

	private LibraryEAO eao;
 
    @Before
    public void setUp() throws Exception {
        _em = Util.getEntityManager();
        eao = new LibraryEAO(_em);
    }
    
    @Test
    public void countryCountTest() {
        long n = eao.countBooks();
        assertEquals(4, n);
    }
}
