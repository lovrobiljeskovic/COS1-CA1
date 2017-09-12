package RESTcompany;

import Facade.CompanyFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import utility.JSONcompanyConverter;


@Path("company")
public class CompanyResource {
JSONcompanyConverter js = new JSONcompanyConverter();
private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
CompanyFacade cf = new CompanyFacade();

    @Context
    private UriInfo context;

 
    public CompanyResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
