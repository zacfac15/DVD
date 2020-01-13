package database;

import bl.DVD;
import bl.Inventories;
import bl.Inventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabia
 */
public class DB_Access
{

  private static DB_Database database = DB_Database.getInstance();
  private static DB_Access instance;
  private Connection conn = database.getConn();

  public static DB_Access getInstance()
  {
    if (instance == null)
    {
      instance = new DB_Access();
    }
    return instance;
  }

  public ArrayList<Inventory> getDVDInventory(int id) throws SQLException
  {
    ArrayList<Inventory> inventorylist = new ArrayList<>();
    String sql = "SELECT id, genre\n"
            + "FROM inventory\n"
            + "WHERE id = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sql);)
    {
      prepStmt.setInt(1, id);

      ResultSet rs = prepStmt.executeQuery();

      while (rs.next())
      {
        inventorylist.add(new Inventory(rs.getInt("id"), rs.getString("genre"), getDVDsFromInventory(id)));
      }
    }

    return inventorylist;
  }

  public ArrayList<DVD> getDVDsFromInventory(int id) throws SQLException
  {
    ArrayList<DVD> dvdList = new ArrayList<>();

    String sql = "SELECT sn,title,price\n"
            + "FROM dvd\n"
            + "WHERE invid = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sql);)
    {
      prepStmt.setInt(1, id);

      ResultSet rs = prepStmt.executeQuery();

      while (rs.next())
      {
        dvdList.add(new DVD(rs.getString("title"), rs.getInt("sn"), rs.getDouble("price")));
      }

    }
    return dvdList;
  }

  public ArrayList<DVD> getDVD(int sn, int id) throws SQLException
  {
    ArrayList<DVD> dvdList = new ArrayList<>();

    String sql = "SELECT sn,title,price\n"
            + "FROM dvd\n"
            + "WHERE invid = ? AND sn = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sql);)
    {
      prepStmt.setInt(1, id);
      prepStmt.setInt(2, sn);

      ResultSet rs = prepStmt.executeQuery();

      while (rs.next())
      {
        dvdList.add(new DVD(rs.getString("title"), rs.getInt("sn"), rs.getDouble("price")));
      }

    }
    return dvdList;
  }

  public ArrayList<Inventories> getInventories() throws SQLException
  {
    ArrayList<Inventories> inventorylist = new ArrayList<>();
    int i = 1;

    while (i < 4)
    {
      inventorylist.add(new Inventories(getDVDInventory(i)));
      i++;
    }

    System.out.println(inventorylist.toString());

    return inventorylist;
  }

  public void insertDVD(int id, DVD dvd) throws SQLException
  {
    String sqlString = "INSERT INTO DVD (sn,title,price, invid)\n"
            + " VALUES (?,?,?,?)";

    try (PreparedStatement prepStmt = conn.prepareStatement(sqlString);)
    {
      System.out.println(sqlString);
      prepStmt.setInt(1, dvd.getSn());
      prepStmt.setString(2, dvd.getTitle());
      prepStmt.setDouble(3, dvd.getPrice());
      prepStmt.setInt(4, id);

      prepStmt.execute();
    }
  }
  
  public void insertInventory(int id, String genre) throws SQLException
  {
    String sqlString = "INSERT INTO inventory (id, genre)\n"
            + " VALUES (?,?)";

    try (PreparedStatement prepStmt = conn.prepareStatement(sqlString);)
    {
      System.out.println(sqlString);
      prepStmt.setInt(1, id);
      prepStmt.setString(2,genre);
      
      prepStmt.execute();
    }
  }

  public void deleteDVD(int sn, int id) throws SQLException
  {
    String sqlString = "DELETE FROM dvd WHERE sn = ? AND invid = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sqlString);)
    {
      System.out.println(sqlString);
      prepStmt.setInt(1, sn);
      prepStmt.setInt(2, id);

      prepStmt.execute();
    }
  }

  public void deleteInventory(int id) throws SQLException
  {
    String sqlString2 = "DELETE FROM dvd WHERE invid = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sqlString2);)
    {
      System.out.println(sqlString2);
      prepStmt.setInt(1, id);

      prepStmt.execute();
    }

    String sqlString = "DELETE FROM inventory WHERE id = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sqlString);)
    {
      System.out.println(sqlString);
      prepStmt.setInt(1, id);

      prepStmt.execute();
    }
  }

  public static void main(String[] args)
  {
    DB_Access acc = DB_Access.getInstance();

    try
    {
//      ArrayList<Inventory> inventorylist = acc.getDVDInventory(1);
//      for (Inventory dvd : inventorylist)
//      {
//        System.out.println(dvd.toString());
//      }
//      acc.insertDVD(3, new DVD("Hobbit",403,12.99));
      acc.deleteInventory(4);
    } catch (SQLException ex)
    {
      Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
