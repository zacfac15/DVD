package database;

import bl.DVD;
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
        dvdList.add(new DVD(rs.getString("title"),rs.getInt("sn"),rs.getDouble("price")));
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
        dvdList.add(new DVD(rs.getString("title"),rs.getInt("sn"),rs.getDouble("price")));
      }

    }
    return dvdList;
  }

  public void insertDVD(int index, DVD dvd) throws SQLException
  {
    String sqlString = "INSERT INTO DVD (sn,title,price)\n"
            + " VALUES (?,?,?)";

    try (PreparedStatement prepStmt = conn.prepareStatement(sqlString);)
    {
      System.out.println(sqlString);
      prepStmt.setInt(1, dvd.getSn());
      prepStmt.setString(2, dvd.getTitle());
      prepStmt.setDouble(3, dvd.getPrice());

      prepStmt.execute(sqlString);
    }
  }

  public static void main(String[] args)
  {
    DB_Access acc = DB_Access.getInstance();

    try
    {
      ArrayList<Inventory> inventorylist = acc.getDVDInventory(1);
      for (Inventory dvd : inventorylist)
      {
        System.out.println(dvd.toString());
      }
    } catch (SQLException ex)
    {
      Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
