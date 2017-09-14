package REST;

import Entity.Address;
import Entity.CityInfo;
import Utility.JSONPersonContactDetails;
import Entity.Person;
import Facade.PersonFacade;
import Utility.JSONCity;
import Utility.JSONPerson;
import Utility.JSONStreet;
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
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        List<JSONPerson> jpl = new ArrayList();

        for (Person p : pf.getPersons()) {
            JSONPerson jp = new JSONPerson(p);
            jpl.add(jp);
        }
        return gson.toJson(jpl);
    }
    
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id") int id) {
        return gson.toJson(new JSONPerson(pf.getPersonByID(id)));
    }
    
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllContactInfo() {
        List<JSONPersonContactDetails> jpcds = new ArrayList<>();

        for (Person p : pf.getPersons()) {
            jpcds.add(new JSONPersonContactDetails(p));
        }

        return gson.toJson(jpcds);
    }

    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfo(@PathParam("id") int id) {
        JSONPersonContactDetails jpcd = new JSONPersonContactDetails(pf.getPersonByID(id));
        return gson.toJson(jpcd);
    }

    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfoByPhone(@PathParam("phone") int number) {
        return gson.toJson(pf.getPersonsByPhone(number));
    }

    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsInCity(@PathParam("city") String city) {
        return gson.toJson(pf.getPersonsByCity("city"));
    }

    @GET
    @Path("zip/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByZipCode(@PathParam("zipcode") String zipCode) {
        return gson.toJson(pf.getPersonsByZipCode(zipCode));
    }

    @GET
    @Path("zip/count/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountPersonsByZipCode(@PathParam("zipcode") String zipCode) {
        return gson.toJson(pf.getCountOfPersonsByCity(zipCode));
    }

    @GET
    @Path("hobby/count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountPersonsByHobby(@PathParam("hobby") String hobby) {
        return gson.toJson(pf.getCountOfPersonsWithHobby(hobby));
    }

    @GET
    @Path("hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByHobby(@PathParam("hobby") String hobby) {
        return gson.toJson(pf.getPersonsByZipCode(hobby));
    }
    
    @GET
    @Path("street")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllStreets() {
        List<JSONStreet> newList = new ArrayList();
        
        for (Address a : pf.getAllStreets()) {
            JSONStreet js = new JSONStreet(a);
            newList.add(js);
        }
        
        return gson.toJson(newList);
    }
    
    @GET
    @Path("zip")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllZipCodes() {
        List<JSONCity> newList = new ArrayList();
        
        for (CityInfo c : pf.getAllZipCodes()) {
            JSONCity js = new JSONCity(c);
            newList.add(js);
        }
        
        return gson.toJson(newList);
    }

}
