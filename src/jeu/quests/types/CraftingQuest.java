package jeu.quests.types;
import java.util.*;
import jeu.entity.*;
import jeu.entity.items.*;
import jeu.quests.*;

// Feature not implemented
public class CraftingQuest extends Quest{
  private Item item1;
  private Item item2;
  private Hero hero;


  public CraftingQuest(String pname,Item item1, Item item2,Hero hero){
    super(pname);
    this.item1 = item1;
    this.item2= item2;
    this.hero=hero;
  }



  public void solvingQuest(Item item1, Item item2,Hero hero) {
      if(hero.isInInventory(item1) && hero.isInInventory(item2)){
      System.out.println("Vous avez fabriqué la potion de téléportation!");
      this.resolved = true;
      this.exit();
      }
  }



}
