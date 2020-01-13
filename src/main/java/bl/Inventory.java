package bl;

public class Inventory
{
  private int id;
  private int sn;
  private String genre;

  public Inventory()
  {
  }

  public Inventory(int id, int sn, String genre)
  {
    this.id = id;
    this.sn = sn;
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

  public int getSn()
  {
    return sn;
  }

  public void setSn(int sn)
  {
    this.sn = sn;
  }

  public String getGenre()
  {
    return genre;
  }

  public void setGenre(String genre)
  {
    this.genre = genre;
  }
  
  
}
