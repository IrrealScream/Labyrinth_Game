package jeu.quests.types;
import java.util.*;
import jeu.entity.*;
import jeu.entity.pnjs.*;
import jeu.quests.*;

// FEATURE but not implemented

public class CombatQuest extends Quest{
  private Boss boss ;

  public CombatQuest(String pname,Boss b){
    super(pname);
    this.boss = b;
  }


  public void solvingQuest() {
    if (isresolved() == false) {
      if (boss.getHealthPoints() <= 0) {
        this.resolved = true;
        System.out.println("Félicitation! Vous avez vaincu le boss.");
      }
    }
  }



}
