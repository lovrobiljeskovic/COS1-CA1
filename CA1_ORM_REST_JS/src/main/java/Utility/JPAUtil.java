package Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mathiasjepsen
 */
public class JPAUtil {
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("master");

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void close() {
        EMF.close();
    }
    
}
