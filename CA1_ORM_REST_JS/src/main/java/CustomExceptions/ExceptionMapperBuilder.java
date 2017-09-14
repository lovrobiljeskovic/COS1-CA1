/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomExceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Dell
 */
@Provider
public class ExceptionMapperBuilder implements ExceptionMapper<ExceptionBuilder>
{
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
   
    @Override
    public Response toResponse(ExceptionBuilder ex)
    {
        
        return Response.status(ex.getQ().getCode()).entity(gson.toJson(ex.q)).type(MediaType.APPLICATION_JSON).build();
    }
    
}
