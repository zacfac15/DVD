package bl;

public class DVD
{
  private String name;
  private int sn;
  private double price;

  public DVD(String name, int sn, double price)
  {
    this.name = name;
    this.sn = sn;
    this.price = price;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
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
    return "DVD{" + "name=" + name + ", sn=" + sn + ", price=" + price + '}';
  }
  
  
}
