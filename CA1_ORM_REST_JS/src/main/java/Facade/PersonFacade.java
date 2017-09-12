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
            return em.find(Person.class, 1);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        
        try {
            return em.createQuery("SELECT p FROM Person p").getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons(String zipCode) {
        EntityManager em = getEntityManager();
        
        try {
            return em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.zipCode = :zipCode").setParameter("zipCode", zipCode).getResultList();
        } finally {
            em.close();
        }
    }
    
}
