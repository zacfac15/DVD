package rest;

import bl.DVD;
import bl.DVDManager;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author fabia
 */
@Path("DVD")
public class DVDResource
{

  @Context
  private UriInfo context;
  private DVDManager manager;

  /**
   * Creates a new instance of DVDResource
   */
  public DVDResource()
  {
  }

  /**
   * Retrieves representation of an instance of rest.DVDResource
   * @return an instance of java.lang.String
   */
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addCar(DVD content)
  {
    manager.add(content);
    return Response.status(200)
                   .entity(content)
                   .build();
  }

  @GET
  @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
  @Path("{id}")
  public DVD getXMLFromID(@PathParam("id") int id)
  {
    return manager.get(id);
  }

  @DELETE
  @Produces(MediaType.APPLICATION_XML)
  @Path("{id}")
  public Response deleteCar(@PathParam("id") int id)
  {
    try
    {
      manager.delete(id);
      return Response.status(Response.Status.OK).entity("ok").build();
    } catch (Exception e)
    {
      e.printStackTrace();
      return Response.status(Response.Status.EXPECTATION_FAILED)
                     .entity("not ok, very not ok").build();
    }
  }
}
