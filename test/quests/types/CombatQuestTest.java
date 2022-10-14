package quests.types;
import quests.*;
import quests.types.*;
import entity.pnjs.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class CombatQuestTest{

  private CombatQuestTest Quest1;
  private Boss Illidan;

    @Before
    public void before(){
      Quest1 = new CombatQuest("Quest1",Illidian,false);
      Illidan = new Boss(1000,3,"Illidan",1500);
    }

    @Test
    public void TestresolveQuest(){
      assertFalse(Quest1.solvingQuest());
      assertEquals(Illidan.getHealthPoints(),1000);
      Illidan.substractHP(1000);
      assertEquals(Illidan.getHealthPoints(),0);
      assertTrue(Quest1.solvingQuest());

    }

    // ---Pour permettre l'exécution des test----------------------
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(quests.types.CombatQuestTest.class);
    }

}
