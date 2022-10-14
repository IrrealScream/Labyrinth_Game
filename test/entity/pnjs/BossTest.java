package entity.pnjs;
import entity.pnjs.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class BossTest{

  private Boss Illidan;

  @Before
  public void before(){
    Illidan = new Boss(1000,3,"Illidan",1500);
  }

// ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(entity.pnjs.PNJTest.class);
  }

}
