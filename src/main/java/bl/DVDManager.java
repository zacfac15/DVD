package bl;

import java.util.ArrayList;
import java.util.List;

public class DVDManager
{
  private List<DVD> list = new ArrayList<>();
  
  public void add(DVD dvd)
  {
    list.add(dvd);
  }
  
  public DVD get(int index)
  {
    return list.get(index);
  }
  
  public void delete(int index)
  {
    list.remove(index);
  }
  
}
