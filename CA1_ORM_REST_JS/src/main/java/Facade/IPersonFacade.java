package Facade;

import Entity.Address;
import Entity.CityInfo;
import Entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mathiasjepsen
 */
public interface IPersonFacade {
    
    void createPerson(Person p);
    Person getPersonByID(int id);
    Person addPerson(Person p);
    Person editPerson(Person p);
    List<Address> getAllStreets();
    List<CityInfo> getAllZipCodes();
    List<Person> getPersons();
    List<Person> getPersonsByZipCode(String zipCode);
    List<Person> getPersonsByHobby(String hobby); 
    List<Person> getPersonsByPhone(int phone);
    List<Person> getPersonsByCity(String city);
    Long getCountOfPersonsByCity(String zipCode);
    Long getCountOfPersonsWithHobby(String hobby);
    void addEntityManagerFactory(EntityManagerFactory emf);

    
}
