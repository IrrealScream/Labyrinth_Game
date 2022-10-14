package jeu.entity;

import jeu.entity.items.*;
import jeu.labyrinth.*;
import jeu.labyrinth.cells.*;
import jeu.util.*;
import jeu.exceptions.*;
import java.util.*;



public class Hero extends Entity {

  // Points de vie
  private int healthPoint;
  // Correspond à l'endurance/le nombre d'actions pendant le tour
  private int stamina;
  // Inventaire = liste d'objets
  private List<Item> inventory;
  // Argent du Hero
  private int money;
  // Listes d'indices
  private List<String> clues;
  // Quête complète
  private String quest;

  /**
  * Constuit un Héro avec un nom, ses points de vie et sa stamina
  * @param pName nom du Héro
  * @param pHealthPoint les points de vie du Héro
  * @param pStamina la stamina du Héro
  */
  public Hero(String pName, int pHealthPoint, int pStamina) {
    super(pName, "\u2694\uFE0F ");
    this.healthPoint = pHealthPoint;
    this.stamina = pStamina;
    this.inventory = new ArrayList<Item>();
    this.money = 0;
    this.priority = 1;
    this.clues = new ArrayList<String>();
  }

  /**
   * renvoie les points de vie du héro
   * @return les points de vie du héro
   */
  public int getHealthPoint() {
    return this.healthPoint;
  }

  /**
   * change les points de vie du héro
   * @param hp les nouveaux points de vie du héro
   */
  public void setHealthPoint(int hp) {
    this.healthPoint = hp;
  }

  /**
   * renvoie la stamina du héro
   * @return la stamina du héro
   */
  public int getStamina() {
    return this.stamina;
  }

  /**
   * change la stamina du héro
   * @param stamina la nouvelle stamina du héro
   */
  public void setStamina(int stamina) {
    this.stamina = stamina;
  }

  /**
   * renvoie la liste des indices qu'a le héro
   * @return la liste des indices qu'a le héro
   */
  public List<String> getClues(){
    return this.clues;
  }

  /**
   * donne un indice au héro
   * @param clue indice donné au héro
   */
  public void addClue(String clue){
    this.clues.add(clue);
  }
  
  /**
   * donne la quête complète au hérp
   * @param quest la quête complète
   */
  public void setQuest(String quest){
    this.quest=quest;
  }
  
  /**
   * fait se déplacer le héro dans une direction
   * @param direction la direction dans laquelle se déplace le héro
   * @throws IllegalMoveDirection le héro ne peut pas se déplacer dans cette direction
   */
  public void move(Cardinals direction) throws IllegalMoveDirection {
    if (this.locationCell.getNeighbours().containsKey(direction)) {
      this.locationCell.removeEntity(this);
      this.setLocation(this.locationCell.getNeighbours().get(direction));
      this.locationCell.setToVisible();
    }
    else {
      throw new IllegalMoveDirection("You cannot move in this direction.");
    }
  }

  /**
   * renvoie l'argent du héro
   * @return l'argent du héro
   */
  public int getMoney(){
    return this.money;
  }

  /**
   * ajoute de l'argent au héro
   * @param gain le gain du héro
   */
  public void addMoney(int gain){
    this.money += gain;
  }

  /**
   * enlève de l'argent au héro
   * @param loss la perte du héro
   */
  public void substractMoney(int loss){
    this.money -= loss;
  }

  /**
   * le héro récupère un objet
   * @param item l'objet que le héro récupère
   */
  public void collectAnItem(Item item){
    addItem(item);
    this.locationCell.removeEntity(item);
  }

  /**
   * ajoute un objet dans l'inventaire du héro
   * @param item objet à ajouter
   */
  public void addItem(Item item) {
    this.inventory.add(item);
    item.setIntoInventory();
  }

  /**
   * enlève un objet de l'inventaire du héro
   * @param item objet à enlever
   * @throws UnknownItemException l'objet n'est pas dans l'inventaire du héro
   */
  public void removeItem(Item item) throws UnknownItemException {
    if (!(this.inventory.contains(item))) {
      throw new UnknownItemException("The item is not in Hero's inventory or doesn't exist.");
    }
    else {
      this.inventory.remove(item);
    }
  }

  /**
   * le héro lâche un objet au sol
   * @param item l'objet à lâcher
   */
  public void dropAnItem(Item item){
    if (this.inventory.contains(item)){
      this.inventory.remove(item);
      item.dropFromInventory(this.locationCell);
      this.locationCell.addEntity(item);
    }
  }

  /**
   * renvoie l'inventaire du héro
   * @return l'inventaire du héro
   */
  public List<Item> getInventory() {
    return this.inventory;
  }

  /**
   * renvoie vrai si l'objet est dans l'inventaire du héro, faux sinon
   * @param item l'objet dont on souhaite savoir s'il est dans l'inventaire du héro ou non
   * @return vrai si l'objet est dans l'inventaire du héro, faux sinon
   */
  public boolean isInInventory(Item item) {
    return this.inventory.contains(item);
  }

  public static void main(String[] args) {
    Hero h = new Hero(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    LabyrinthChessType maze = new LabyrinthChessType(15,15);
    h.setLocation(maze.getStartCell());
    maze.getStartCell().addEntity(h);
    String dessin = maze.draw();
    System.out.println(dessin);
  }

}
