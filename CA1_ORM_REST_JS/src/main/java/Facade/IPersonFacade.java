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
    
    void addEntityManagerFactory(EntityManagerFactory emf);
    void createPerson(Person p);
    Person getPersonByID(String Stringid);
    Person addPerson(Person p);
    Person editPerson(Person p);
<<<<<<< HEAD
     Person getPersonByPhone(String phone);
=======
>>>>>>> master
    List<Address> getAllStreets();
    List<CityInfo> getAllZipCodes();
    List<Person> getPersons();
    List<Person> getPersonsByZipCode(String zipCode);
    List<Person> getPersonsByHobby(String hobby);
<<<<<<< HEAD
=======
    Person getPersonByPhone(String phone);
>>>>>>> master
    List<Person> getPersonsByCity(String city);
    Long getCountOfPersonsByCity(String zipCode);
    Long getCountOfPersonsWithHobby(String hobby);

    
}
