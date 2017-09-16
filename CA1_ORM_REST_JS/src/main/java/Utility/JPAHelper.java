/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mathiasjepsen
 */
public class JPAHelper {

    private static JPAHelper myHelper = new JPAHelper();
    private static EntityManagerFactory myFactory = null;

    /**
     * Private constructor. Implementing synchronization with double-lock check
     */
    private JPAHelper() {

        if (myFactory == null) {
            synchronized (JPAHelper.class) {

                // This second check will be true only for the first thread entering the block incase 
                // of thread race
                if (myFactory == null) {
                    myFactory = Persistence.createEntityManagerFactory("master");
                }
            }
        }
    }

    /**
     * Static Accessor Method
     *
     * @return
     */
    public static JPAHelper getInstance() {
        if (myHelper == null) {
            myHelper = new JPAHelper();
        }
        return myHelper;
    }

    public EntityManagerFactory getJPAFactory() {
        return myFactory;
    }

}
