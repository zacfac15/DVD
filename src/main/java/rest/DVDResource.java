package rest;

import bl.DVD;
import bl.DVDManager;
import bl.Inventory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("inventory")
public class DVDResource
{

  private DVDManager manager = DVDManager.getInstance();

  @Context
  private UriInfo context;

  /**
   * Creates a new instance of DVDResource
   */
  public DVDResource()
  {
  }

  /**
   * Retrieves representation of an instance of rest.DVDResource
   *
   * @return an instance of java.lang.String
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}/dvds")
  public void addDVDsFromInventory(@PathParam("id") int id, DVD content)
  {
    manager.addDVDtoInventory(content, id);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}/dvds")
  public ArrayList<Inventory> getDVDsFromInventory(@PathParam("id") int id)
  {
    try
    {
      return manager.getInventory(id);
    } catch (SQLException ex)
    {
      Logger.getLogger(DVDResource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}/dvds/{sn}")
  public ArrayList<DVD> getDVD(@PathParam("id") int id, @PathParam("sn") int sn)
  {
    try
    {
      return manager.getDVD(sn, id);
    } catch (SQLException ex)
    {
      Logger.getLogger(DVDResource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @DELETE
  @Produces(MediaType.APPLICATION_XML)
  @Path("{id}/dvds/{sn}")
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

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}/dvds/{sn}")
  public Response addDVD(DVD content, @PathParam("id") int id, @PathParam("sn") int sn)
  {
    manager.changeDVD(content, id, sn);
    return Response.status(200)
            .entity(content)
            .build();
  }
}
