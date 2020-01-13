package database;

import bl.DVD;
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

  public ArrayList<DVD> getDVDInventory(int id) throws SQLException
  {
    ArrayList<DVD> inventorylist = new ArrayList<>();
    String sql = "SELECT d.sn, d.title, d.price "
            + "FROM dvd d INNER JOIN dvdinventory di ON d.sn = di.sn "
            + "           INNER JOIN inventory i ON di.inventoryid = i.id "
            + "WHERE i.id = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sql);)
    {
      System.out.println(sql);
      prepStmt.setInt(1, id);

      ResultSet rs = prepStmt.executeQuery();

      while (rs.next())
      {
        inventorylist.add(new DVD(rs.getString("title"), rs.getInt("sn"), rs.getDouble("price")));
      }
    }

    return inventorylist;
  }

  public ArrayList<DVD> getDVD(int sn) throws SQLException
  {
    ArrayList<DVD> dvdList = new ArrayList<>();
    String sql = "SELECT sn, title, price\n"
            + "FROM dvd\n"
            + "WHERE sn = ?";

    try (PreparedStatement prepStmt = conn.prepareStatement(sql);)
    {
      System.out.println(sql);
      prepStmt.setInt(1, sn);

      ResultSet rs = prepStmt.executeQuery();

      while (rs.next())
      {
        dvdList.add(new DVD(rs.getString("title"), rs.getInt("sn"), rs.getDouble("price")));
      }
    }

    return dvdList;
  }

  public static void main(String[] args)
  {
    DB_Access acc = DB_Access.getInstance();

    try
    {
      ArrayList<DVD> inventorylist = acc.getDVDInventory(1);
      ArrayList<DVD> dvdList = acc.getDVD(300);
      for (DVD dvd : inventorylist)
      {
//        System.out.println(dvd.toString());
      }
      for (DVD dvd : dvdList)
      {
        System.out.println(dvd.toString());
      }
    } catch (SQLException ex)
    {
      Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
