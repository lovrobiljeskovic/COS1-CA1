//package UnitTest;
//
//import Entity.Address;
//import Entity.CityInfo;
//import Entity.Hobby;
//import Entity.Person;
//import Entity.Phone;
//import Facade.PersonFacade;
//import java.util.List;
//import javax.persistence.Persistence;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author mathiasjepsen
// */
//
//public class PersonFacadeTest {
//    
//    static PersonFacade pf = new PersonFacade();
//
//    public PersonFacadeTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        Person p1 = new Person("Mathias", "Jepsen");
//        Person p2 = new Person("Thomas", "Thimothee");
//        Person p3 = new Person("Lovro", "Biljeskovic");
//        CityInfo c1 = new CityInfo();
//        CityInfo c2 = new CityInfo();
//        CityInfo c3 = new CityInfo();
//        Address a1 = new Address();
//        Address a2 = new Address();
//        Address a3 = new Address();
//        Phone phone = new Phone();
//        phone.setNumber(12345678);
//        phone.setDescription("This is a phone number");
//        Hobby hobby = new Hobby();      
//        hobby.setName("handball"); 
//        hobby.setDescription("This is a cool sport");
//        c1.setCity("Holte");
//        c1.setZipCode("2840");
//        c2.setCity("Lyngby");
//        c2.setZipCode("2800");  
//        c3.setCity("Frederiksberg");
//        c3.setZipCode("2000");
//        a1.setStreet("Kongevejen 438");
//        a1.setAddSitionalInfo("This is some additional info");
//        a1.setCityInfo(c1);
//        a2.setStreet("Lyngbyvej 23");
//        a2.setAddSitionalInfo("This is also some additional info");
//        a2.setCityInfo(c2);
//        a3.setStreet("Frederiksbergvej 254");
//        a3.setAddSitionalInfo("This is more cool additional info");
//        a3.setCityInfo(c3);
//        p1.setAddress(a1);
//        p2.setAddress(a2);
//        p3.setAddress(a3);
//        p1.addHobby(hobby);
//        hobby.addPerson(p1);
//        p1.addPhone(phone);
//        pf.createPerson(p1);
//        pf.createPerson(p2);
//        pf.createPerson(p3);
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//        
//    }
//
//    @Test
//    public void getPersonByID() {
//        Person p1 = pf.getPersonByID("1");
//        Person p2 = pf.getPersonByID("2");
//        Person p3 = pf.getPersonByID("3");
//        assertEquals("Mathias", p1.getFirstName());
//        assertEquals("Thomas", p2.getFirstName());
//        assertEquals("Lovro", p3.getFirstName());
//    }
//    
//    @Test
//    public void getPersons() {
//        List<Person> persons = pf.getPersons();
//        assertFalse(persons.isEmpty());
//    }
//    
//    @Test
//    public void getPersonsByZipcode() {
//        List<Person> persons = pf.getPersonsByZipCode("2840");
//        assertEquals("Mathias", persons.get(0).getFirstName());
//    }
//    
//    @Test
//    public void getPersonsByHobby() {
//        List<Person> persons = pf.getPersonsByHobby("handball");
//        assertEquals(1, persons.size());
//    }
//    @Test
//    public void getPersonsByPhone() {
//        Person person = pf.getPersonByPhone("12345678");
//        assertEquals("Mathias", person.getFirstName());
//    }
//    @Test
//    public void getPersonsByCity() {
//        List<Person> persons = pf.getPersonsByZipCode("2840");
//        assertEquals("Mathias", persons.get(0).getFirstName());
//    }
//
//    @Test
//    public void getCountOfPersonsByCity() {
//        Long count = pf.getCountOfPersonsByCity("2840");
//        assertEquals(Long.valueOf(1l), count);
//    }
//    
//    @Test
//    public void getCountOfPersonsWithHobby() {
//        Long count = pf.getCountOfPersonsWithHobby("handball");
//        assertEquals(Long.valueOf(1l), count);
//    }
//    
//    @Test
//    public void addPerson() {
//        Person p = new Person();
//        Address a = new Address();
//        CityInfo c = new CityInfo();
//        Phone phone = new Phone();
//        phone.setDescription("descriptphone");
//        phone.setNumber(23423522);
//        c.setCity("City");
//        c.setZipCode("1337");
//        a.setStreet("Street");
//        a.setAddSitionalInfo("Additional info");
//        a.setCityInfo(c);
//        p.setFirstName("NewPersonFirstName");
//        p.setLastName("NewPerosnLastName");
//        p.setEmail("email");
//        p.setAddress(a);
//        p.addPhone(phone);
//        pf.addPerson(p);
//        assertEquals("NewPersonFirstName", pf.getPersonByID("4").getFirstName());
//    }
//    
//    @Test
//    public void editPerson() {
//        Person p = new Person();
//        Address a = new Address();
//        CityInfo c = new CityInfo();
//        c.setCity("City");
//        c.setZipCode("1337");
//        a.setStreet("Street");
//        a.setAddSitionalInfo("Additional info");
//        a.setCityInfo(c);
//        p.setId(1);
//        p.setAddress(a);
//        p.setFirstName("NotMathias");
//        p.setLastName("Lastname");
//        p.setEmail("email@mail.com");
//        pf.editPerson(p); 
//        assertEquals("NotMathias", pf.getPersonByID("1").getFirstName());
//    }
//    
//    @Test
//    public void getAllStreets() {
//        List<Address> addresses = pf.getAllStreets();
//        assertEquals("Kongevejen 438", addresses.get(0).getStreet());
//        assertEquals(3, addresses.size());
//    }
//    
//    @Test
//    public void getAllZipCodes() {
//        List<CityInfo> cities = pf.getAllZipCodes();
//        assertEquals("2840", cities.get(0).getZipCode());
//        assertEquals(3, cities.size());
//    }
//    
//    
//}   
//
//
