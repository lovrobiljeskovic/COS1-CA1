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

    private EntityManager getEntityManager() {
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
            return em.find(Person.class, id);
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

    @Override
    public void createPerson(Person p) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Person> getPersonsFromPhone(int number) {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT p FROM Person p join p.phones f WHERE f.number = :number").setParameter("number", number).getResultList();
        } finally {
            em.close();
        }
    }

}
