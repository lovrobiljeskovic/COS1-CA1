package REST;

import Entity.Address;
import Entity.CityInfo;
import Entity.Company;
import Facade.CompanyFacade;
import Utility.JSONCity;
import Utility.JSONCompanyContactDetails;
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
import Utility.JSONCompanyConverter;
import static Utility.JSONCompanyConverter.getCompanyFromJson;
import static Utility.JSONCompanyConverter.getJSONFromCompany;
import Utility.JSONStreet;
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
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCompaniesComplete() {    
        return gson.toJson(cf.getCompanies());
    }
    
    @GET
    @Path("complete/phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyCompleteByPhone(@PathParam("number") String number) {    
        return gson.toJson(cf.getCompanyByPhone(number));
    }
    
    @GET
    @Path("complete/cvr/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCompaniesCompleteByCVR(@PathParam("cvr") String cvr) {    
        return gson.toJson(cf.getCompanyByCVR(cvr));
    }
    
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCompaniesContactInfo() {
        List<JSONCompanyContactDetails> newList = new ArrayList();
        
        for (Company c : cf.getCompanies()) {
            JSONCompanyContactDetails jc = new JSONCompanyContactDetails(c);
            newList.add(jc);
        }
        
        return gson.toJson(newList);
    }

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyById(@PathParam("id") String id) {
        return JSONCompanyConverter.getJSONFromCompany(cf.getCompanyByID(id));
    }

    @GET
    @Path("cvr/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompanyByCvr(@PathParam("cvr") String cvr) {
        return JSONCompanyConverter.getJSONFromCompany(cf.getCompanyByCVR(cvr));
    }

    @GET
    @Path("city/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCompaniesByZipCode(@PathParam("zipcode") String zipcode) {
        return gson.toJson(cf.getCompaniesByZipCode(zipcode));
    }

    @GET
    @Path("count/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountByZipCode(@PathParam("zipcode") String zipCode) {
        String companyCount = "Count: " + cf.getCompaniesByZipCode(zipCode).size();
        return gson.toJson(companyCount);
    }
    
    @GET
    @Path("street")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllStreets() {
        List<JSONStreet> newList = new ArrayList();
        
        for (Address a : cf.getAllStreets()) {
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
        
        for (CityInfo c : cf.getAllZipCodes()) {
            JSONCity js = new JSONCity(c);
            newList.add(js);
        }
        
        return gson.toJson(newList);
    }
    
    @GET
    @Path("employees/more/{min}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompaniesWithMoreEmployees(@PathParam("min") String minimumNumber) {
        return gson.toJson(cf.getCompaniesWithMoreEmployees(minimumNumber));
    }
    
    @GET
    @Path("employees/less/{max}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompaniesWithLessEmployees(@PathParam("max") String maximumNumber) {
        return gson.toJson(cf.getCompaniesWithLessEmployees(maximumNumber));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putJson(String content) {
        return getJSONFromCompany(cf.editCompany(getCompanyFromJson(content)));
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postCompany(String companyAsJson) {        
        return getJSONFromCompany(cf.addCompany(getCompanyFromJson(companyAsJson)));
    }
}
