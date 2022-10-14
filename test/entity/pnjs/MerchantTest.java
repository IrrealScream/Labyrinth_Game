package entity.pnjs;
import entity.pnjs.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;
import entity.items.*;

public class MerchantTest{

    private List<Item> market;
    private Merchant jackTheRipper;

  @Before
  public void before(){
    Weapon epee = new Weapon("epee","L'épée est émoussé",20,150,99999999,3,"E");
    market = new ArrayList();
    market.add(epee);
    jackTheRipper = new Merchant(2,"JackTheRipper",market,"L'epee est moussé");
  }

  @Test
  public void TestMerchantSell(){
  }

  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(entity.pnjs.MerchantTest.class);
  }

}
