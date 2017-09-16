//package IntegrationTest;
//
//import Entity.Address;
//import Entity.CityInfo;
//import Entity.Company;
//import Entity.Person;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.*;
//import io.restassured.parsing.Parser;
//
///**
// *
// * @author mathiasjepsen
// */
//public class IntegrationTest {
//    
//    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    
//    public IntegrationTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//        RestAssured.basePath = "/CA1_ORM_REST_JS";
//        RestAssured.defaultParser = Parser.JSON;
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
//    }
//
//    @Test
//    public void serverIsRunning() {
//        given().
//                when().get().
//                then().
//                statusCode(200);
//    }
//    
//    @Test
//    public void getAllPersonsComplete() {
//        given().
//                when().
//                get("api/person/complete").
//                then().
//                statusCode(200);
//    }
//    
//    @Test
//    public void getPersonCompleteByWrongID() {
//        given().
//                when().
//                get("api/person/complete/1").
//                then().
//                statusCode(404);
//    }
//    
//    @Test
//    public void getPersonCompleteByID() {
//        given().
//                when().
//                get("api/person/complete/55").
//                then().
//                statusCode(200);
//    }
//    
//    @Test
//    public void getPersonContactInfo() {
//        given().
//                when().
//                get("api/person/contactinfo").
//                then().
//                statusCode(200);
//    }
//    
//    @Test
//    public void getPersonByPhone() {
//        given().
//                when().
//                get("api/person/phone/21453632").
//                then().
//                statusCode(200);
//    }
//
//    @Test
//    public void addPersonTest() {
//        Person p = new Person();
//        Address a = new Address();
//        CityInfo c = new CityInfo();
//        c.setCity("City");
//        c.setZipCode("1337");
//        a.setStreet("RestAssuredCity");
//        a.setAddSitionalInfo("RestAssuredAdditionalInfo");
//        a.setCityInfo(c);
//        p.setFirstName("RestAssuredFirstName");
//        p.setLastName("RestAssuredLastName");
//        p.setEmail("mail@restperson.com");
//        p.setAddress(a);
//        
//        given()
//                .contentType("application/json")
//                .body(gson.toJson(p))
//                .when()
//                .post("/api/person/")
//                .then()
//                .statusCode(200);
//    }
//    
////    @Test
////    public void editPersonTest() {
////        Person p = new Person();
////        Address a = new Address();
////        CityInfo c = new CityInfo();
////        c.setCity("Holte");
////        c.setZipCode("24232");
////        a.setStreet("RestAssuredCity");
////        a.setAddSitionalInfo("RestAssuredAdditionalInfo");
////        a.setCityInfo(c);
////        p.setFirstName("RestAssuredFirst");
////        p.setLastName("RestAssuredLastName");
////        p.setEmail("RestAssuredEmail");
////        p.setAddress(a);
////        p.setId(99);
////        
////        given()
////                .contentType("application/json")
////                .body(gson.toJson(p))
////                .when()
////                .put("/api/person/")
////                .then()
////                .statusCode(200);
////    }
//    
//    @Test
//    public void editCompanyTest() {
//        Company c = new Company();
//        Address a = new Address();
//        CityInfo ci = new CityInfo();
//        ci.setCity("City");
//        ci.setZipCode("1337");
//        a.setStreet("RestAssuredCity");
//        a.setAddSitionalInfo("RestAssuredAdditionalInfo");
//        a.setCityInfo(ci);
//        c.setName("RestAssuredName");
//        c.setCvr("32432");
//        c.setDescription("RestAssuredDescription");
//        c.setEmail("mail@rest.com");
//        c.setMarketValue(12312);
//        c.setNumEmployees(2342);
//        c.setAddress(a);
//        c.setId(10);
//        
//        given()
//                .contentType("application/json")
//                .body(gson.toJson(c))
//                .when()
//                .put("/api/company/")
//                .then()
//                .statusCode(200);
//    }
//    
//    @Test
//    public void addCompanyTest() {
//        Company c = new Company();
//        Address a = new Address();
//        CityInfo ci = new CityInfo();
//        ci.setCity("City");
//        ci.setZipCode("1337");
//        a.setStreet("RestAssuredCity");
//        a.setAddSitionalInfo("RestAssuredAdditionalInfo");
//        a.setCityInfo(ci);
//        c.setName("RestAssuredName");
//        c.setCvr("32432");
//        c.setDescription("RestAssuredDescription");
//        c.setEmail("mail@rest.com");
//        c.setMarketValue(12312);
//        c.setNumEmployees(2342);
//        c.setAddress(a);
//        
//        given()
//                .contentType("application/json")
//                .body(gson.toJson(c))
//                .when()
//                .post("/api/company/")
//                .then()
//                .statusCode(200);
//    }
//    
//}
