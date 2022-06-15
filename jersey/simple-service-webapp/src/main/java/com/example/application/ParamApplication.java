package com.example.application;

import com.example.param.Person;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.Objects;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("param")
@Consumes({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ParamApplication {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("atest")
    public String getIt(@DefaultValue("aaa") @QueryParam("a") String a,
                        @DefaultValue("bbb") @QueryParam("b") String b) {
        return "Got it! ".concat(" a=").concat(a).concat(" , b= ").concat(b);
    }

//    @GET
//    @Path("btest")
//    public String btest(@DefaultValue("aaa") @QueryParam("a") String a,
//                        @DefaultValue("lifuchun:31") @QueryParam("b") Person person) {
//        return "Got it! ".concat(" a=").concat(a).concat(" , b= ").concat(Objects.toString(person));
//    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("ctest")
    public String ctest(@FormParam("a") String a , @FormParam("b") Integer b) {
        return "Got it! ".concat(" a=").concat(a).concat(" , b= ").concat(Objects.toString(b));
    }


    @GET
    @Path("dtest")
    public String dtest(@Context UriInfo uriInfo) {
        return "Got it! ".concat(" queryParams=").concat(Objects.toString(uriInfo.getQueryParameters()))
                .concat(" , pathParams= ").concat(Objects.toString(uriInfo.getPathParameters()));
    }

    @GET
    @Path("etest")
    public String etest(@Context HttpHeaders httpHeaders) {
        return "Got it! ".concat(" headers=").concat(Objects.toString(httpHeaders.getRequestHeaders()))
                .concat(" , cookieParams= ").concat(Objects.toString(httpHeaders.getCookies()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("ftest")
    public String ftest(MultivaluedMap<String,String> formParams) {
        return "Got it! ".concat(" formParams=").concat(Objects.toString(formParams));
    }

}
