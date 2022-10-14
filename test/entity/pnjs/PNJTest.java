package entity.pnjs;
import entity.pnjs.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class PNJTest{

  private PNJ finnAndJake;

  @Before
  public void before(){
    finnAndJake = new PNJ(2,200,300,"Finn&Jake","F");
  }

  @Test
  public void TestSwitchImmortalAndSetters(){
    finnAndJake.switchImmortality();
    assertFalse(finnAndJake.immortal);
    finnAndJake.switchImmortality();
    assertTrue(finnAndJake.isImmortal());
    finnAndJake.setMaxHP(150);
    assertEquals(finnAndJake.getMaxHP(),150);
    assertEquals(finnAndJake.getHealthPoints(),150);
    finnAndJake.addHP(10);
    assertEquals(finnAndJake.getHealthPoints(),160);
    finnAndJake.substractHP(20);
    assertEquals(finnAndJake.getHealthPoints(),140);
  }

  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(entity.pnjs.PNJTest.class);
  }

}
