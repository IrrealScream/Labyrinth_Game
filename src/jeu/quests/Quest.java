package jeu.quests;

import jeu.entity.*;

public class Quest{

  protected String name;
  protected boolean resolved;
  protected String allQuest;
  protected String clue1;
  protected String clue2;

  public Quest(String pname,String iAllQuest,String iClue1,String iClue2){
    this.name = pname;
    this.resolved=false;
    this.allQuest=iAllQuest;
    this.clue1=iClue1;
    this.clue2=iClue2;
  }

  public String getallQuest(){
    return this.allQuest;
  }

  public String getClue1(){
    return this.clue1;
  }

  public String getClue2(){
    return this.clue2;
  }

  public void askQuest(Hero hero){
    if(hero.getClues().size()==2){
      hero.setQuest(allQuest);
    }
  }

  public boolean isresolved(){
    return this.resolved;
  }

  public void exit() {
    if (isresolved() == true) {
      System.out.println("Félicitation! Vous êtes sorti du labyrinthe.");
    }
  }

}
