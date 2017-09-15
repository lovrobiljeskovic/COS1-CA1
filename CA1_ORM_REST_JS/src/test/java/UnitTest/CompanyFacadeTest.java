package UnitTest;

import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Entity.Phone;
import Facade.CompanyFacade;
import static UnitTest.PersonFacadeTest.pf;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
        Phone phone = new Phone();
        phone.setNumber(12345678);
        phone.setDescription("This is a cool phone");
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
        p1.setNumEmployees(200);
        p2.setNumEmployees(120);
        p3.setNumEmployees(50);
        p1.setCvr("270593");
        p2.setAddress(a2);
        p3.setAddress(a3);
        p1.addPhone(phone);
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
    public void getCompanyByID() {
        Company p1 = cf.getCompanyByID("1");
        Company p2 = cf.getCompanyByID("2");
        Company p3 = cf.getCompanyByID("3");
        assertNotNull(p1);
        assertNotNull(p2);
        assertNotNull(p3);
    }
    
    @Test
    public void getCompanies() {
        List<Company> companies = cf.getCompanies();
        assertEquals("Apple", companies.get(0).getName());
    }
    
    @Test
    public void getCompaniesByZipCode() {
        List<Company> companies = cf.getCompaniesByZipCode("2840");
        assertEquals("Apple", companies.get(0).getName());
    }
    
    @Test
    public void getCompanyByCVR() {
        Company c = cf.getCompanyByCVR("270593");
        assertEquals("Apple", c.getName());
    }
    
    @Test
    public void getCompaniesByPhone() {
        Company company = cf.getCompanyByPhone("12345678");
        assertEquals("Apple", company.getName());
    }
    
    @Test
    public void getCompaniesWithMoreEmployees() {
        List<Company> companies = cf.getCompaniesWithMoreEmployees("100");
        assertEquals("Apple", companies.get(0).getName());
        assertEquals("Microsoft", companies.get(1).getName());
    }
    
    @Test
    public void getCompaniesWithLessEmployees() {
        List<Company> companies = cf.getCompaniesWithLessEmployees("150");
        assertEquals("Microsoft", companies.get(0).getName());
        assertEquals("Lenovo", companies.get(1).getName());
    }
    
    @Test
    public void editCompany() {
        Company company = new Company();
        Address address = new Address();
        CityInfo city = new CityInfo();
        company.setCvr("270593");
        company.setDescription("newDescription");
        company.setEmail("newEmail");
        company.setMarketValue(123122);
        company.setName("Apple");
        company.setNumEmployees(230);
        company.setId(1);
        address.setStreet("Street");
        address.setAddSitionalInfo("Additional info");
        city.setCity("City");
        city.setZipCode("2840");
        address.setCityInfo(city);
        company.setAddress(address);
        cf.editCompany(company);
        assertEquals("newDescription", cf.getCompanyByID("1").getDescription());
    }
    
    @Test
    public void addCompany() {
        Company company = new Company();
        Address address = new Address();
        CityInfo city = new CityInfo();
        company.setCvr("23423");
        company.setDescription("Description");
        company.setEmail("email");
        company.setMarketValue(123122);
        company.setName("Company name");
        company.setNumEmployees(230);
        address.setStreet("Street");
        address.setAddSitionalInfo("Additional info");
        city.setCity("City");
        city.setZipCode("2840");
        address.setCityInfo(city);
        company.setAddress(address);
        cf.addCompany(company);
        assertEquals("Company name", cf.getCompanyByID("4").getName());
    }
    
    @Test
    public void getAllZipCodes() {
        List<CityInfo> cities = cf.getAllZipCodes();
        assertEquals("2840", cities.get(0).getZipCode());
        assertEquals(3, cities.size());
    }
    
    @Test
    public void getAllStreets() {
        List<CityInfo> cities = cf.getAllZipCodes();
        assertEquals("2840", cities.get(0).getZipCode());
        assertEquals(3, cities.size());
    }

    
}
