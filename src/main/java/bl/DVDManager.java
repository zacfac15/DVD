package bl;

import database.DB_Access;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DVDManager
{
  
  private static DVDManager instance;
  private List<DVD> list = new ArrayList<>();
  private DB_Access acc = DB_Access.getInstance();

    public static synchronized DVDManager getInstance()
  {
    if (instance == null)
    {
      return instance = new DVDManager();
    }
    return instance;
  }

  public void addDVDtoInventory(DVD dvd, int id) throws SQLException
  {
    acc.insertDVD(id, dvd);
  }

  public ArrayList<Inventory> getInventory(int index) throws SQLException
  {
    return acc.getDVDInventory(index);
  }
  
  public ArrayList<DVD> getDVD(int sn, int id) throws SQLException
  {
    return acc.getDVD(sn, id);
  }
  
  public ArrayList<Inventories> getInventories() throws SQLException
  {
    return acc.getInventories();
  }

  public void deleteDVD(int id, int sn) throws SQLException
  {
    acc.deleteDVD(sn, id);
  }
  
  public void deleteInventory(int id) throws SQLException
  {
    acc.deleteInventory(id);
  }
  
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
