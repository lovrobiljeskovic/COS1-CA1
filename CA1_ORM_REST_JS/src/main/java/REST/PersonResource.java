package REST;

import Utility.JSONPersonContactDetails;
import Entity.Person;
import Facade.PersonFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import Utility.JSONcompanyConverter;

/**
 * REST Web Service
 *
 * @author Dell
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    JSONcompanyConverter js = new JSONcompanyConverter();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PersonFacade pf = new PersonFacade();

    public PersonResource() {
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id") int id) {
        return gson.toJson(pf.getPersonByID(id));
    }

    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        List<Person> persons = pf.getPersons();

        return gson.toJson(persons);
    }

    @GET
    @Path("contactInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllContactInfo() {
        List<Person> persons = pf.getPersons();
        List<JSONPersonContactDetails> jpcds = new ArrayList<>();

        for (Person p : persons) {
            jpcds.add(new JSONPersonContactDetails(p));
        }

        return gson.toJson(jpcds);
    }

    @GET
    @Path("contactInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfo(@PathParam("id") int id) {
        Person p = pf.getPersonByID(id);
        JSONPersonContactDetails jpcd = new JSONPersonContactDetails(p);

        return gson.toJson(jpcd);
    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfoByPhone(@PathParam("phone") int number) {
        List<Person> persons = pf.getPersonsByPhone(number);

        return gson.toJson(persons);
    }
<<<<<<< Updated upstream:CA1_ORM_REST_JS/src/main/java/REST/PersonResource.java

=======
    
    
    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsInCity(@PathParam("city")String city) 
    {
         List<Person> persons = pf.getPersonFromCity("city");
         
         return gson.toJson(persons);
    }
    
    @GET
    @Path("zip/{postalcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons(@PathParam("postalcode")String postalcode) 
    {
         List<Person> persons = pf.getPersonFromCity("city");
         
         return gson.toJson(persons);
    }
    
    
    
            
            
            
            
>>>>>>> Stashed changes:CA1_ORM_REST_JS/src/main/java/RESTperson/PersonResource.java
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
