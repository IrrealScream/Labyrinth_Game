package quests.types;
import quests.*;
import quests.types.*;
import entity.pnjs.*;
import entity.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class ExitQuestTest{

  private ExitQuest quest;
  private Hero hero;


    @Before
    public void before(){
      quest = new ExitQuest("La trouvaille d'Eldorado",500);
      hero = new Hero("Donovan",200,1);
    }


    @Test
    public void TestExitQuest(){
      assertFalse(quest.isresolved());
      hero.addMoney(900);
      quest.solvingQuest();
      assertTrue(quest.isresolved());
      }

      // ---Pour permettre l'exécution des test----------------------
      public static junit.framework.Test suite() {
          return new junit.framework.JUnit4TestAdapter(quests.ExitQuestTest.class);
      }
}
