package database;

import static database.DB_Config.DB_DATABASENAME;
import static database.DB_Config.DB_DRIVER;
import static database.DB_Config.DB_PASSWD;
import static database.DB_Config.DB_URL;
import static database.DB_Config.DB_USER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fabia
 */
public class DB_Database
{
  
 //implement this class as singleton 1.constructor private 2. statische variable vom Klassentyp 3.statische getter

  private static DB_Database theInstance = null;

  public static DB_Database getInstance()
  {
    if (theInstance == null)
    {
      theInstance = new DB_Database();
    }
    return theInstance;
  }
  private Connection conn;
  private DB_CachedConnection cc; //= new DB_CachedConnection(conn);

  private DB_Database()
  {
    try
    {
      Class.forName(DB_DRIVER);
      connect();
    }
    catch (ClassNotFoundException | SQLException ex)
    {
      throw new RuntimeException(ex.getMessage());
    }
  }

  public void connect() throws SQLException
  {
    conn = DriverManager.getConnection(DB_URL + DB_DATABASENAME, DB_USER, DB_PASSWD);
    cc = new DB_CachedConnection(conn);
  }

  public void disconnect() throws SQLException
  {
    conn.close();
    cc = null;
  }

  public Statement getStatement() throws SQLException
  {
    return cc.getStatement();
  }

  public void releaseStatement(Statement stat)
  {
    cc.releaseStatement(stat);
  }

  public Connection getConn()
  {
    return conn;
  }

  public void setConn(Connection conn)
  {
    this.conn = conn;
  }

  public static void main(String[] args)
  {
    DB_Database.getInstance();
  }
}

