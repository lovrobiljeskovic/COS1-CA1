package Facade;

import CustomExceptions.ErrorMessageBuilder;
import CustomExceptions.ExceptionBuilder;
import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Entity.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author mathiasjepsen
 */
public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void addEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Person getPersonByID(String idString) {
        EntityManager em = getEntityManager();

        try {
            int id = Integer.parseInt(idString);
            Person p = em.find(Person.class, id);
            if (p == null)
          {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404 , "Person with "+id+" id not found"));
          }
            return p;
        }
            catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid id"));
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
                
        try {
            List<Person> persons = new ArrayList<>();
            persons = em.createQuery("SELECT p FROM Person p").getResultList();
            if (persons.isEmpty())
              {
                throw new ExceptionBuilder(new ErrorMessageBuilder(204, "There are no persons"));
              }
            return persons;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsByZipCode(String zipCode) {
        EntityManager em = getEntityManager();

        try {
            Integer.parseInt(zipCode);
            List<Person> persons = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.zipCode = :zipCode").setParameter("zipCode", zipCode).getResultList();
       if (persons.isEmpty())
              {
                throw new ExceptionBuilder (new ErrorMessageBuilder(404 , "No one lives in city with zipcode " + zipCode));
              }
            
            return persons;
        }catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid zipcode"));
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

    @Override
    public Person getPersonByPhone(String stringNumber) {
        EntityManager em = getEntityManager();
   
        try {
            int number = Integer.parseInt(stringNumber);
            Person p = (Person) em.createQuery("SELECT p FROM Person p JOIN p.phones "
                    + "f WHERE f.number = :number").setParameter("number", number).getSingleResult();
            return p;
            } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid phone"));
            }catch (NoResultException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404, "There is no person with the following phone number " + stringNumber));
            }
        finally {
            em.close();
        }
   
    }

    @Override
    public List<Person> getPersonsByHobby(String hobby) {
        EntityManager em = getEntityManager();

        try {
            List<Person> persons = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobby").setParameter("hobby", hobby).getResultList();
               
            if(persons.isEmpty())
              {
                throw new ExceptionBuilder (new ErrorMessageBuilder(404 , "No persons that likes " +hobby));
              }
            return persons;
        
        }
        
        finally {
            em.close();
        }
    }

    @Override
    public Long getCountOfPersonsWithHobby(String hobby) {
        EntityManager em = getEntityManager();

        try {
            return (Long) em.createQuery("SELECT COUNT(p) FROM Person p JOIN p.hobbies h WHERE h.name = :hobby").setParameter("hobby", hobby).getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsByCity(String city) {
        EntityManager em = getEntityManager();

        try {
            List<Person> persons = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.city = :city").setParameter("city", city).getResultList();
            
            if (persons.isEmpty())
              {
                throw new ExceptionBuilder (new ErrorMessageBuilder(404 , "No one lives in " +city));
              }
            
            return persons;
            
        } finally {
            em.close();
        }
    }

    @Override
    public Long getCountOfPersonsByCity(String zipCode) {
        EntityManager em = getEntityManager();

        try {
            Integer.parseInt(zipCode);
            return (Long) em.createQuery("SELECT COUNT(p) FROM Person p JOIN p.address a WHERE a.cityInfo.zipCode = :zipCode").setParameter("zipCode", zipCode).getSingleResult();
        } catch (NumberFormatException e) {
            throw new ExceptionBuilder(new ErrorMessageBuilder(400, "Please enter a valid zipcode"));
        }
        
        finally {
            em.close();
        }
    }
    
    @Override
    public List<Address> getAllStreets() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT a FROM Address a").getResultList();
        } finally {
            em.close();
        } 
    }
    
    @Override
    public List<CityInfo> getAllZipCodes() {
        EntityManager em = getEntityManager();

        try {
            return em.createQuery("SELECT c FROM CityInfo c").getResultList();
        } finally {
            em.close();
        } 
    }
    
    @Override
    public Person editPerson(Person person) {
        EntityManager em = getEntityManager();
        
        try {
            Person p = em.find(Person.class, person.getId());
            CityInfo city = em.find(CityInfo.class, person.getAddress().getCityInfo().getZipCode());
            
            if (city != null) {
                Address a = p.getAddress();
                a.setCityInfo(city);
                person.setAddress(a);
            }
//            if (p == null) {
//                throw new PersonNotFoundException("Cannot edit. Person with provided id does not exist");
//            }
            em.getTransaction().begin();
            if (!person.getFirstName().isEmpty()) p.setFirstName(person.getFirstName()); 
            if (!person.getLastName().isEmpty()) p.setLastName(person.getLastName());
            if (!person.getEmail().isEmpty()) p.setEmail(person.getEmail()); 
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
    @Override
    public Person addPerson(Person person) {
        EntityManager em = getEntityManager();
        
        try {
            CityInfo city = em.find(CityInfo.class, person.getAddress().getCityInfo().getZipCode());
            
            if (city != null) {
                Address a = person.getAddress();
                a.setCityInfo(city);
                person.setAddress(a);
            }
            
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

}
