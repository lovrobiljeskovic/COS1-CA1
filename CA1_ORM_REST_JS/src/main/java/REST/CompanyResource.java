package REST;

import Entity.Company;
import Facade.CompanyFacade;
import Utility.JSONCompany;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import Utility.JSONcompanyConverter;
import javax.persistence.Persistence;

@Path("company")
public class CompanyResource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    CompanyFacade cf = new CompanyFacade();

    @Context
    private UriInfo context;

    public CompanyResource() {
        cf.addEntityManagerFactory(Persistence.createEntityManagerFactory("cos1_CA1_ORM_REST_JS_war_1.0-SNAPSHOTPU"));
    }

    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCompanies() {
        List<Company> companies = cf.getCompanies();
        List<JSONCompany> newList = new ArrayList();
        for (Company c : companies) {
            JSONCompany jc = new JSONCompany(c);
            newList.add(jc);
        }
        return gson.toJson(newList);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyById(@PathParam("id") int id) {
        Company c = cf.getCompanyByID(id);
        return JSONcompanyConverter.getJSONFromCompany(c);
    }

    @GET
    @Path("{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyByCvr(@PathParam("cvr") String cvr) {
        Company c = cf.getCompanyByCVR(cvr);
        return JSONcompanyConverter.getJSONFromCompany(c);
    }

    @GET
    @Path("city/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCompaniesByZipCode(@PathParam("zipcode") String zipcode) {
        List<Company> companies = cf.getCompanies();

        return gson.toJson(companies);

    }

    @GET
    @Path("count/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        String companyCount = "Count: " + cf.getCompanies().size();
        return gson.toJson(companyCount);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postCompany(String companyAsJson) {
        Company c = JSONcompanyConverter.getCompanyFromJson(companyAsJson);
        cf.addCompany(c);
        return JSONcompanyConverter.getJSONFromCompany(c);

    }
}
