package UnitTest;

import Entity.Address;
import Entity.CityInfo;
import Entity.Person;
import Facade.PersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 *
 * @author mathiasjepsen
 */
public class PersonFacadeTest {
    
    static PersonFacade pf = new PersonFacade();

    public PersonFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        pf.addEntityManagerFactory(Persistence.createEntityManagerFactory("PersonFacadeTest"));
        Person p1 = new Person("Mathias", "Jepsen");
        Person p2 = new Person("Thomas", "Thimothee");
        Person p3 = new Person("Lovro", "Biljeskovic");
        CityInfo c1 = new CityInfo();
        CityInfo c2 = new CityInfo();
        CityInfo c3 = new CityInfo();
        Address a1 = new Address();
        Address a2 = new Address();
        Address a3 = new Address();
        c1.setCity("Holte");
        c1.setZipCode("2840");
        c2.setCity("Lyngby");
        c2.setZipCode("2800");  
        c3.setCity("Frederiksberg");
        c3.setZipCode("2000");
        a1.setStreet("Kongevejen 438");
        a1.setAddSitionalInfo("This is some additional info");
        a1.setCityInfo(c1);
        a2.setStreet("Lyngbyvej 23");
        a2.setAddSitionalInfo("This is also some additional info");
        a2.setCityInfo(c2);
        a3.setStreet("Frederiksbergvej 254");
        a3.setAddSitionalInfo("This is more cool additional info");
        a3.setCityInfo(c3);
        p1.setAddress(a1);
        p2.setAddress(a2);
        p3.setAddress(a3);
        pf.createPerson(p1);
        pf.createPerson(p2);
        pf.createPerson(p3);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pf.addEntityManagerFactory(Persistence.createEntityManagerFactory("PersonFacadeTest"));
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void getPerson() {
        Person p1 = pf.getPerson(1);
        Person p2 = pf.getPerson(2);
        Person p3 = pf.getPerson(3);
        assertEquals("Mathias", p1.getFirstName());
        assertEquals("Thomas", p2.getFirstName());
        assertEquals("Lovro", p3.getFirstName());
    }
    
    @Test
    public void getPersons() {
        List<Person> persons = pf.getPersons();
        assertEquals(persons.size(), 3);
    }
    
    @Test
    public void getPersonsByZipcode() {
        List<Person> persons = pf.getPersons("2840");
        assertEquals("Mathias", persons.get(0).getFirstName());
    }
    
}