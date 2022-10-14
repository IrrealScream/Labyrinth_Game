package jeu.entity.pnjs;

public class Boss extends PNJ{

  private int combatStamina; // The number of actions in combat

  /*
  *Build a boss for a CombatQuest
  * @param iHealthPoints The numbre of healthpoints actually
  * @param iStamina Stamina / Number of actions 
  * @param iName Name of the boss
  * @parma iMaxHP Max Health Points
  */
  public Boss(int iHealthPoints,int iStamina, String iName,int iMaxHP){
    super(iStamina,iHealthPoints,iMaxHP,iName,"\uD83D\uDC7E");
    this.immortal=true;
  }
  
  /*
  *Get the talk when he's still immortal or not
  * @return String Return the talking
  */
  public String getImmunityTalk(){
    if(this.isImmortal()==true){
      return ("Je suis " + this.name +"Vous n'êtes pas prêt");
    }
    return("Je suis" + this.name + "Qui êtes vous?");
  }

  /*
  *Return the speech before Combat
  * @return String Return the the speech
  */
  public String getCombatTalk(){
    return "Gouté à 10000 ans de haine";
  }

}
