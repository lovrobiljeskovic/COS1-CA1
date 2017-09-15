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
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
<<<<<<< HEAD
    }
    
=======
    }    
>>>>>>> master
    
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id") String id) {
        
        Person p = pf.getPersonByID(id);
        JSONPerson jp = new JSONPerson(p);
        return gson.toJson(jp);
        
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
<<<<<<< HEAD
    
  
=======
        
>>>>>>> master
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfo(@PathParam("id") String id) {
        JSONPersonContactDetails jpcd = new JSONPersonContactDetails(pf.getPersonByID(id));
        return gson.toJson(jpcd);
    }
    
    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD


    public String getContactInfoByPhone(@PathParam("phone") String phone) {
       
=======
    public String getContactInfoByPhone(@PathParam("phone") String phone) {
>>>>>>> master
        Person p = pf.getPersonByPhone(phone);

            JSONPersonContactDetails jp = new JSONPersonContactDetails(p);
          
        
        return (gson.toJson(jp));
    }

    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsInCity(@PathParam("city") String city) {
<<<<<<< HEAD

=======
>>>>>>> master
        List<JSONPerson> jpl = new ArrayList();
        for (Person p : pf.getPersonsByCity(city)) {
            JSONPerson jp = new JSONPerson(p);
            jpl.add(jp);
        }
        return gson.toJson(jpl);
    }

    @GET
    @Path("zip/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsByZipCode(@PathParam("zipcode") String zipCode) {
        List<JSONPerson> jpl = new ArrayList();

        for (Person p : pf.getPersonsByZipCode(zipCode)) {
            JSONPerson jp = new JSONPerson(p);
            jpl.add(jp);
        }
        return gson.toJson(jpl);
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
        List<JSONPerson> jpl = new ArrayList();

        for (Person p : pf.getPersonsByHobby(hobby)) {
            JSONPerson jp = new JSONPerson(p);
            jpl.add(jp);
        }
        return gson.toJson(jpl);
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
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson(String personAsJson) {
        return gson.toJson(pf.editPerson(gson.fromJson(personAsJson, Person.class)));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postCompany(String personAsJson) {        
        return gson.toJson(pf.addPerson(gson.fromJson(personAsJson, Person.class)));
    }

}
