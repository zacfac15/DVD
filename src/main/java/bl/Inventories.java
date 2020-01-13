package bl;

import java.util.ArrayList;

public class Inventories
{

  private ArrayList<Inventory> inventories = new ArrayList<>();

  public Inventories()
  {
  }

  public Inventories(ArrayList<Inventory> inventories)
  {
    this.inventories = inventories;
  }

  public ArrayList<Inventory> getInventories()
  {
    return inventories;
  }

  public void setInventories(ArrayList<Inventory> inventories)
  {
    this.inventories = inventories;
  }

  @Override
  public String toString()
  {
    return "Inventories{" + "inventories=" + inventories + '}';
  }
  
  
}
