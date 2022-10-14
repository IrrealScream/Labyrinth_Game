package jeu.entity.pnjs;

import jeu.util.*;
import jeu.entity.*;
import java.util.Random;
import java.util.*;
import jeu.exceptions.*;
import jeu.labyrinth.cells.*;

public class PNJ extends Entity {
    /** Un pnj peut se déplacer de manière aléatoire, d'un case ou plusieurs (stamina)
     *
     */

    protected int stamina; // la stamina du pnj correspond à sopn énergie qui sera décrémenter chaque tour d'une valeur qui dépend de son action.
    protected String informationClue; // l'information du pnj, c'est l'indice qu'il peut donner sur une quête ou un objet
    protected int maxHP; // la vie maximal du PNJ
    protected int healthPoints; // les points de vie du pnj, définie à la construction.
    protected boolean immortal; // true : le pnj est invincible
    protected Map<Action,List<String>> listDialogues; // Les dialogues du pnj disponibles (choix)

    // Constructeurs
    public PNJ(int pStamina,String iName,String iLetter) {
        super(iName,iLetter);

        this.stamina = pStamina;
        this.immortal = true;
        this.listDialogues = new HashMap<Action,List<String>>();
        this.priority=2;
        List<String> salut = new ArrayList<String>();
        List<String> ok = new ArrayList<String>();
        List<String> goodbye = new ArrayList<String>();
        salut.add("Bonjour");
        ok.add("Ok");
        goodbye.add("Au revoir");
        this.listDialogues.put(Action.SALUTATION,salut);
        this.listDialogues.put(Action.OKAY,ok);
        this.listDialogues.put(Action.GOODBYE,goodbye);
    }

    public PNJ(int pStamina,int pHealthPoints, int pMaxHP,String iName,String iLetter) {
        super(iName,iLetter);
        this.stamina = pStamina;
        this.maxHP = pMaxHP;
        this.healthPoints=pHealthPoints;
        this.immortal=true;
        this.listDialogues = new HashMap<Action,List<String>>();
        List<String> salut = new ArrayList<String>();
        List<String> ok = new ArrayList<String>();
        List<String> goodbye = new ArrayList<String>();
        salut.add("Bonjour");
        ok.add("Ok");
        goodbye.add("Au revoir");
        this.listDialogues.put(Action.SALUTATION,salut);
        this.listDialogues.put(Action.OKAY,ok);
        this.listDialogues.put(Action.GOODBYE,goodbye);
    }


    /** Renvoie l'énergie du pnj
     * @return stamina du pnj
     */
    public int getStamina() {
        return this.stamina;
    }

    /** Permet de définir l'énergie du pnj
     * @param pStamina l'énergie du pnj
     */
    public void setStamina(int pStamina) {
        this.stamina = pStamina;
    }

    /** Renvoie la vie du pnj
     * @return health du pnj
     */
    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int iHealthPoints) {
      this.healthPoints=iHealthPoints;
    }

    public int getMaxHP(){
      return this.maxHP;
    }

    /** Permet de définir les points de vie
     * @return healthPoints du pnj
     */
    public void setMaxHP(int pMaxHP){
      if(pMaxHP<this.healthPoints){
        this.setHealthPoints(pMaxHP);
      }
        this.maxHP = pMaxHP;
    }

    public void addHP(int addedHp){
      this.healthPoints+=addedHp;
    }

    public void substractHP(int subsHp){
      this.healthPoints-=subsHp;
    }

    public String getInformation(){
      return this.informationClue;
    }

    public void setInformation(String information){
      this.informationClue=information;
    }

    public boolean isImmortal() {
        return this.immortal;
    }

    public void switchImmortality(){
      if(this.isImmortal()==true){
        this.immortal=false;
      }
      else{
        this.immortal=true;
      }
    }
    /** Donne une réponses aléatoire depuis la liste dialogues dispo
     * @return une reponses aléatoire
     */
    public String getRandomTalk(Action act) {
      int max= this.listDialogues.get(act).size();
      int min= 0;
      int i = (int)(Math.random() * (max - min)) + min;
      return this.listDialogues.get(act).get(i);
    }

    /** Parle au pnj */
    public String talkTo(Action act){
      if(act==Action.SALUTATION){
        return this.getRandomTalk(Action.SALUTATION);
      }
      if(act==Action.OKAY){
        return this.getRandomTalk(Action.OKAY);
      }
      if(act==Action.GOODBYE){
        return this.getRandomTalk(Action.GOODBYE);
      }

      return "";

    }

    /**
    * fait se déplacer le PNJ dans une direction aléatoire
    */
    public void move() throws IllegalMoveDirection {
      double proba = Math.random();
      if (proba <0.1) {
        RandomUtil rd = new RandomUtil();
        int dir = rd.getInt(1,4);
        Cardinals direction;
        if (dir == 1) {
          direction = Cardinals.NORTH;
        }
        else if (dir == 2) {
          direction = Cardinals.SOUTH;
        }
        else if (dir == 3) {
          direction = Cardinals.WEST;
        }
        else {
          direction = Cardinals.EAST;
        }
        if (this.locationCell.getNeighbours().containsKey(direction)) {
          this.locationCell.removeEntity(this);
          this.setLocation(this.locationCell.getNeighbours().get(direction));
          this.locationCell.setToVisible();
        }
      }
    }
}
