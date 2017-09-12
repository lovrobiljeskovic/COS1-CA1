package IntegrationTest;

import Utility.CompanyGenerator;
import Utility.PersonGenerator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;


/**
 *
 * @author mathiasjepsen
 */
public class IntegrationTest {
    
    PersonGenerator pg = new PersonGenerator();
    CompanyGenerator cg = new CompanyGenerator();
    
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
    
    @Test
    public void addCompaniesTest() {
        given()
                .contentType("application/json") 
                .body(cg.generateJSON(10, 100000, 3430))
                .when()
                .post("/api/company/")
                .then()
                .statusCode(200);
    }
}
