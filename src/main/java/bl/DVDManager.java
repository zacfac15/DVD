package bl;

import database.DB_Access;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is a interface between web service and database
 * @author fabia
 */
public class DVDManager
{
  
  private static DVDManager instance;
  private List<DVD> list = new ArrayList<>();
  private DB_Access acc = DB_Access.getInstance();

  /**
   * singelton, to get only one instance
   * @return instance of the class
   */
  public static synchronized DVDManager getInstance()
  {
    if (instance == null)
    {
      return instance = new DVDManager();
    }
    return instance;
  }

  /**
   * to add a dvd to the inventory
   * @param dvd object to add
   * @param id of the inventory
   * @throws SQLException exception which is thrown by the database
   */
  public void addDVDtoInventory(DVD dvd, int id) throws SQLException
  {
    acc.insertDVD(id, dvd);
  }
  
  /**
   * method to add an inventory
   * @param id of the inventory
   * @param genre of the inventory
   * @throws SQLException exception which is thrown by the database
   */
  public void addInventory(int id, String genre) throws SQLException
  {
    acc.insertInventory(id, genre);
  }

  /**
   * method to get a single inventory
   * @param id of the inventory
   * @return a list of the dvds in the invnetory
   * @throws SQLException exception which is thrown by the database
   */
  public ArrayList<Inventory> getInventory(int id) throws SQLException
  {
    return acc.getDVDInventory(id);
  }
  
  /**
   * method to get one single dvd from a inventory
   * @param sn of the dvd
   * @param id of the inventory
   * @return a list of dvds
   * @throws SQLException exception which is thrown by the database
   */
  public ArrayList<DVD> getDVD(int sn, int id) throws SQLException
  {
    return acc.getDVD(sn, id);
  }
  
  /**
   * method to get all inventories from the database
   * @return a list of inventories
   * @throws SQLException exception which is thrown by the database
   */
  public ArrayList<Inventories> getInventories() throws SQLException
  {
    return acc.getInventories();
  }

  /**
   * mehtod to delete a single dvd
   * @param id of the inventory
   * @param sn of the dvd
   * @throws SQLException exception which is thrown by the database
   */
  public void deleteDVD(int id, int sn) throws SQLException
  {
    acc.deleteDVD(sn, id);
  }
  
  /**
   * method to delete a inventory
   * @param id of the inventory
   * @throws SQLException exception which is thrown by the database
   */
  public void deleteInventory(int id) throws SQLException
  {
    acc.deleteInventory(id);
  }
  
  /**
   * 
   * @param d
   * @param inventoryID
   * @param sn 
   */
  public void changeDVD(DVD d,int inventoryID, int sn)
  {
    for (DVD dvd : list)
    {
      if(dvd.getSn() == sn)
      {
        
      }
    }
  }
}
