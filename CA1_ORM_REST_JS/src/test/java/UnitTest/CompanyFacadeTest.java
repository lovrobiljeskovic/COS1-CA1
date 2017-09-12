package UnitTest;

import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Entity.Person;
import Facade.CompanyFacade;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
/**
 *
 * @author mathiasjepsen
 */
public class CompanyFacadeTest {
    
    static CompanyFacade cf = new CompanyFacade();

    public CompanyFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        cf.addEntityManagerFactory(Persistence.createEntityManagerFactory("CompanyFacadeTest"));
        Company p1 = new Company("Apple");
        Company p2 = new Company("Microsoft");
        Company p3 = new Company("Lenovo");
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
        cf.createCompany(p1);
        cf.createCompany(p2);
        cf.createCompany(p3);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        cf.addEntityManagerFactory(Persistence.createEntityManagerFactory("CompanyFacadeTest"));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void getCompany() {
        Company p1 = cf.getCompany(1);
        Company p2 = cf.getCompany(2);
        Company p3 = cf.getCompany(3);
        assertNotNull(p1);
        assertNotNull(p2);
        assertNotNull(p3);
    }
    
    @Test
    public void getCompanies() {
        List<Company> persons = cf.getCompanies();
        assertNotNull(persons);
    }
    
    @Test
    public void getCompaniesByZipcode() {
        List<Company> companies = cf.getCompanies("2840");
        assertEquals("Apple", companies.get(0).getName());
    }
    
}
