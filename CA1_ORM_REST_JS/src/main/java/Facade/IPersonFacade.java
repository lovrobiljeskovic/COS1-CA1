package Facade;

import Entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */
public interface IPersonFacade {
    
    Person getPerson(int id);
    List<Person> getPersons();
    List<Person> getPersons(String zipCode);
    void addEntityManagerFactory(EntityManagerFactory emf);

    
}