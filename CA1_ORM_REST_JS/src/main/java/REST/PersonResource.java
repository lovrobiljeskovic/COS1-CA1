package REST;

import Utility.JSONPersonContactDetails;
import Entity.Person;
import Facade.PersonFacade;
import Utility.JSONPerson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dell
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PersonFacade pf = new PersonFacade();

    public PersonResource() {
        pf.addEntityManagerFactory(Persistence.createEntityManagerFactory("cos1_CA1_ORM_REST_JS_war_1.0-SNAPSHOTPU"));
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id") int id) {

        return gson.toJson(new JSONPerson(pf.getPersonByID(id)));
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

    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsInCity(@PathParam("city") String city) {
        List<Person> persons = pf.getPersonsByCity("city");

        return gson.toJson(persons);
    }

    @GET
    @Path("zip/{postalcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByZipCode(@PathParam("postalcode") String postalcode) {
        List<Person> persons = pf.getPersonsByZipCode(postalcode);

        return gson.toJson(persons);
    }

    @GET
    @Path("zip/count/{postalcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountPersonsByZipCode(@PathParam("postalcode")String postalcode) 
    {
         Long count = pf.getCountOfPersonsByCity(postalcode);
         
         return gson.toJson(count);
    }

    @GET
    @Path("person/count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountPersonsByHobby(@PathParam("hobby")String hobby) 
    {
         Long count = pf.getCountOfPersonsWithHobby(hobby);
         
         return gson.toJson(count);
    }

    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByHobby(@PathParam("hobby") String hobby) {
        List<Person> persons = pf.getPersonsByZipCode(hobby);

        return gson.toJson(persons);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
