package com.insta.fjee.library.junit;

import javax.transaction.TransactionManager;

public class JTATransactionController extends
    org.eclipse.persistence.transaction.JTATransactionController {
 
    public static final String JNDI_TRANSACTION_MANAGER_NAME = "java:comp/TransactionManager";
 
    public JTATransactionController() { super(); }
 
    @Override
    protected TransactionManager acquireTransactionManager() throws Exception {
        return (TransactionManager) jndiLookup(JNDI_TRANSACTION_MANAGER_NAME);
    }
}