/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTperson;

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
import utility.JSONcompanyConverter;

/**
 * REST Web Service
 *
 * @author Dell
 */
@Path("person")
public class PersonResource
{

    @Context
    private UriInfo context;

    JSONcompanyConverter js = new JSONcompanyConverter();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PersonFacade pf = new PersonFacade();
    public PersonResource()
    {
    }

    /**
     * Retrieves representation of an instance of RESTperson.PersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonFromId(@PathParam("id")int id)
    {
        return gson.toJson(pf.getPerson(id));
    }

    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons()
    {
        List<Person> persons = pf.getPersons();
        
        return gson.toJson(persons);
    }
    
    @GET
    @Path("contactInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllContactInfo()
    {
         List<Person> persons = pf.getPersons();
         List<JsonPersonContactDetails> jpcds = new ArrayList<>();
         
         for (Person p : persons)
           {
             jpcds.add(new JsonPersonContactDetails(p));
           }
         
         return gson.toJson(jpcds);
    }
    
    @GET
    @Path("contactInfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfo(@PathParam("id")int id)      
    {
       Person p = pf.getPerson(id);
       JsonPersonContactDetails jpcd = new JsonPersonContactDetails(p);
       
       return gson.toJson(jpcd);
    }
    
    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContactInfoByPhone(@PathParam("phone")int number) 
    {
         List<Person> persons = pf.getPersonsFromPhone(number);
         
         return gson.toJson(persons);
    }
            
            
            
            
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content)
    {
    }
}
