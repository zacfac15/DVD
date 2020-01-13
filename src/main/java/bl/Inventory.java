package bl;

import java.util.ArrayList;

public class Inventory
{
  private int id;
  private String genre;
  private ArrayList<DVD> dvdlist;

  public Inventory()
  {
  }

  public Inventory(int id, String genre, ArrayList<DVD> dvdlist)
  {
    this.id = id;
    this.genre = genre;
    this.dvdlist = dvdlist;
  }

  public Inventory(int id, String genre)
  {
    this.id = id;
    this.genre = genre;
  }
  

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getGenre()
  {
    return genre;
  }

  public void setGenre(String genre)
  {
    this.genre = genre;
  }

  public ArrayList<DVD> getDvdlist()
  {
    return dvdlist;
  }

  public void setDvdlist(ArrayList<DVD> dvdlist)
  {
    this.dvdlist = dvdlist;
  }

  @Override
  public String toString()
  {
    return "Inventory{" + "id=" + id + ", genre=" + genre + ", dvdlist=" + dvdlist + '}';
  }

  
}
