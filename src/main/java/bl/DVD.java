package bl;

public class DVD
{
  private String title;
  private int sn;
  private double price;

  public DVD()
  {
  }
  
  public DVD(String title, int sn, double price)
  {
    this.title = title;
    this.sn = sn;
    this.price = price;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public int getSn()
  {
    return sn;
  }

  public void setSn(int sn)
  {
    this.sn = sn;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  @Override
  public String toString()
  {
    return "DVD{" + "name=" + title + ", sn=" + sn + ", price=" + price + '}';
  }
  
  
}
