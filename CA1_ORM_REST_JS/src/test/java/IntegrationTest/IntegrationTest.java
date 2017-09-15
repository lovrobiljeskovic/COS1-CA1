package IntegrationTest;

import Entity.Address;
import Entity.CityInfo;
import Entity.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.equalTo;

/**
 *
 * @author mathiasjepsen
 */
public class IntegrationTest {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public IntegrationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/CA1_ORM_REST_JS";
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void serverIsRunning() {
        given().
                when().get().
                then().
                statusCode(200);
    }
    
//    @Test
//    public void getCompletePerson() {
//        given().
//                when().get("api/person/complete").then().body("")
//    }

    @Test
    public void addPersonTest() {
        Person p = new Person();
        Address a = new Address();
        CityInfo c = new CityInfo();
        c.setCity("City");
        c.setZipCode("1337");
        a.setStreet("RestAssuredCity");
        a.setAddSitionalInfo("RestAssuredAdditionalInfo");
        a.setCityInfo(c);
        p.setFirstName("RestAssuredFirstName");
        p.setLastName("RestAssuredLastName");
        p.setAddress(a);
        
        given()
                .contentType("application/json")
                .body(gson.toJson(p))
                .when()
                .post("/api/person/")
                .then()
                .statusCode(200);
    }
    
    @Test
    public void editPersonTest() {
        Person p = new Person();
        Address a = new Address();
        CityInfo c = new CityInfo();
        c.setCity("City");
        c.setZipCode("1337");
        a.setStreet("RestAssuredCity");
        a.setAddSitionalInfo("RestAssuredAdditionalInfo");
        a.setCityInfo(c);
        p.setFirstName("RestAssuredFirstName");
        p.setLastName("RestAssuredLastName");
        p.setEmail("RestAssuredEmail");
        p.setAddress(a);
        p.setId(105);
        
        given()
                .contentType("application/json")
                .body(gson.toJson(p))
                .when()
                .put("/api/person/")
                .then()
                .statusCode(200);
        
 
    }

}
