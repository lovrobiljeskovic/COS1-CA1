package REST;

import Entity.Address;
import Entity.CityInfo;
import CustomExceptions.ErrorMessageBuilder;
import CustomExceptions.ExceptionBuilder;
import Utility.JSONPersonContactDetails;
import Entity.Person;
import Facade.PersonFacade;
import Utility.JSONCity;
import static Utility.JSONCompanyConverter.getCompanyFromJson;
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
import javax.ws.rs.core.Response;

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
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactInfo(@PathParam("id")int id)
    {
        Person p = pf.getPersonByID(id);
        if (p == null)
          {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404 , "Person with id "+id+" not found"));
          }
       JSONPersonContactDetails jpcd = new JSONPersonContactDetails(p);

        return Response.ok().entity(gson.toJson(jpcd)).type(MediaType.APPLICATION_JSON).build();
        
        
    }
    
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonFromId(@PathParam("id") int id) {
        
          Person p = pf.getPersonByID(id);
        if (p == null)
          {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404 , "Person with "+id+" id not found"));
          }

        return Response.ok().entity(gson.toJson(p)).type(MediaType.APPLICATION_JSON).build();
        
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
    
    
    

    /*
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfo(@PathParam("id") int id) {
        JSONPersonContactDetails jpcd = new JSONPersonContactDetails(pf.getPersonByID(id));
        return gson.toJson(jpcd);
    }
    */
    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactInfoByPhone(@PathParam("phone") int number) {
        
        Person p = pf.getPersonByPhone(number);
        if (p == null)
          {
            throw new ExceptionBuilder(new ErrorMessageBuilder(404 , "Person with "+number+" phone number not found"));
          }

        List<JSONPerson> jpl = new ArrayList();

        for (Person pe : pf.getPersons()) {
            JSONPerson jp = new JSONPerson(pe);
            jpl.add(jp);
        }
        return Response.ok().entity(gson.toJson(jpl)).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsInCity(@PathParam("city") String city) {

        
        

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
