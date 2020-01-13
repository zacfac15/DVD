package rest;

import bl.DVD;
import bl.DVDManager;
import bl.Inventories;
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
   * this method is to insert dvds into a database
   *
   * @param id of the inventory
   * @param content is the content which is given back by postman to insert into
   * the database
   * @return responds if it was successful
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{id}/dvds")
  public Response addDVDsFromInventory(@PathParam("id") int id, DVD content)
  {
    try
    {
      manager.addDVDtoInventory(content, id);
    } catch (SQLException ex)
    {
      Logger.getLogger(DVDResource.class.getName()).log(Level.SEVERE, null, ex);
    }

    return Response.status(200)
            .entity(content)
            .build();
  }

  /**
   * this method is to insert inventories into the database
   *
   * @param content is the content which is given back by postman to insert into
   * the database
   * @return shows if the process was successful
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("")
  public Response addInventory(Inventory content)
  {
    try
    {
      manager.addInventory(content.getId(), content.getGenre());
    } catch (SQLException ex)
    {
      Logger.getLogger(DVDResource.class.getName()).log(Level.SEVERE, null, ex);
    }

    return Response.status(200)
            .entity(content)
            .build();
  }

  /**
   * this method gives back the dvds which are in one inventory
   *
   * @param id is the inventory id to identifie the inventory
   * @return a list of the inventory
   */
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

  /**
   * this method is to display a single dvd
   *
   * @param id id of the inventory the dvd is in
   * @param sn serial number of the dvd
   * @return the dvd
   */
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

  /**
   * this method is to display all inventories
   *
   * @return a list of all inventories which are in the database so far
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("")
  public ArrayList<Inventories> getInventories()
  {
    try
    {
      return manager.getInventories();
    } catch (SQLException ex)
    {
      Logger.getLogger(DVDResource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  /**
   * this method deletes a single dvd in one inventory
   * @param id id of the inventory
   * @param sn serialnumber of the dvd
   * @return the status if it was successful
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}/dvds/{sn}")
  public Response deleteDVD(@PathParam("id") int id, @PathParam("sn") int sn)
  {
    try
    {
      manager.deleteDVD(id, sn);

      return Response.status(Response.Status.OK).entity("ok").build();
    } catch (Exception e)
    {
      e.printStackTrace();
      return Response.status(Response.Status.EXPECTATION_FAILED)
              .entity("not ok").build();
    }
  }

  /**
   * this method deletes one inventory
   * @param id id of the inventory
   * @return a status if it was successful
   */
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response deleteInventory(@PathParam("id") int id)
  {
    try
    {
      manager.deleteInventory(id);

      return Response.status(Response.Status.OK).entity("ok").build();
    } catch (Exception e)
    {
      e.printStackTrace();
      return Response.status(Response.Status.EXPECTATION_FAILED)
              .entity("not ok").build();
    }
  }

  /**
   * replaces a dvd with new values
   * @param content new values
   * @param id id of the inventory
   * @param sn serialnumber of the dvd
   * @return status if it was successful
   */
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
