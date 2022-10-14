package quests.types;
import quests.*;
import quests.types.*;
import entity.pnjs.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.util.*;

public class CraftingQuestTest{

  private CraftingQuestTest Quest1;
  private Item item1;
  private Item item2;
  private Hero hero;

  @Before
  public void before(){
    Quest1 = new CraftingQuest("Quest1",item1,item2,hero,false);
    hero = new Hero("hero",10,1);
    item1 = new Item("fiole","p");
    item2 = new Item("échantillon","p");
  }

  @Test
  public void TestSolvingQuest(){
    assertFalse(Quest1.solvingQuest());
    assertFalse(hero.inventory.contains(item1));
    assertFalse(hero.inventory.contains(item2));
    hero.inventory.add(item1));
    hero.inventory.add(item2));
    assertTrue(hero.inventory.contains(item1));
    assertTrue(hero.inventory.contains(item2));
    assertTrue(Quest1.solvingQuest());
  }

  // ---Pour permettre l'exécution des test----------------------
  public static junit.framework.Test suite() {
      return new junit.framework.JUnit4TestAdapter(quests.types.CraftingQuestTest.class);
  }
}
