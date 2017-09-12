package Facade;

import Entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */

public class PersonFacade implements IPersonFacade {
    
    private EntityManagerFactory emf;
    
    private EntityManager getEntityManager(){ 
        return emf.createEntityManager();
    }
    
    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person getPerson(int id) {
        EntityManager em = getEntityManager();
        
        try {
            
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons() {
                EntityManager em = getEntityManager();
        
        try {
            
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons(int zipCode) {
                EntityManager em = getEntityManager();
        
        try {
            
        } finally {
            em.close();
        }
    }
    
}
