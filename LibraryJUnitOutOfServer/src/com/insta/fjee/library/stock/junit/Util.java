package com.insta.fjee.library.stock.junit;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
 
public class Util {
 
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("LibraryEJB_junit")
                .createEntityManager();
    }
}