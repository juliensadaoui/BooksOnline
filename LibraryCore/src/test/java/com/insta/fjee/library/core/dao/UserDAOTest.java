package com.insta.fjee.library.core.dao;

import static org.junit.Assert.*;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Test;

public class UserDAOTest extends TestCase
{

	private IUserDAO userDAO;

	public UserDAOTest(String testName) {
      super(testName);
//      userDAO = new UserDAO();
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByLoginAndPassword() {
		fail("Not yet implemented");
	}

    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite("Test IUserDAO");
        suite.addTest(new UserDAOTest("testSave"));
        suite.addTest(new UserDAOTest("testUpdate"));
        return suite;
  }


}
