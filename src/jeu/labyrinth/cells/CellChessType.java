package jeu.labyrinth.cells;

import java.util.*;
import jeu.entity.*;
import jeu.labyrinth.*;
import jeu.entity.items.*;

public class CellChessType implements Cell{

  /* la coordonnée x de la cellule */
  private int pos_x;
  /* la coordonnée y de la cellule */
  private int pos_y;
  /* le status de la cellule : empty, air ou wall */
  private Status Status;
  /* un numéro attitré à la cellule qui sert uniquement à la génération du labyrinthe ChessType */
  private int nb;
  /* la liste des entités présentes sur la cellule */
  private List<Entity> entities;
  /* la table des cellules accessibles depuis cette cellule */
  private Map<Cardinals, Cell> accessibleNeighbours;

  private boolean isVisible;

  /**
   * Créer une cellule de coordonnées (x,y) de type ChessType avec le status par défaut empty
   * @param ipos_x la coordonnée x de la cellule
   * @param ipos_y la coordonnée y de la cellule
   */
  public CellChessType(int ipos_x ,int ipos_y){
    this.Status = Status.EMPTY;
    this.pos_x = ipos_x;
    this.pos_y = ipos_y;
    this.isVisible = false;
    this.nb = 0;
    this.entities = new ArrayList<Entity>();
    this.accessibleNeighbours = new HashMap<Cardinals, Cell>();
  }

  /**
   * Crée une cellule de coordonnées (x,y) de type ChessType
   * @param ipos_x la coordonnée x de la cellule
   * @param ipos_y la coordonnée y de la cellule
   * @param pStatus le status de la
   */
  public CellChessType(int ipos_x ,int ipos_y, Status pStatus){
    this.Status = pStatus;
    this.pos_x = ipos_x;
    this.pos_y = ipos_y;
    this.isVisible = false;
    this.nb = 0;
    this.entities = new ArrayList<Entity>();
    this.accessibleNeighbours = new HashMap<Cardinals, Cell>();
  }

  /**
   * Renvoie la coordonnée x de la cellule
   * @return la coordonnée x de la cellule
   */
  public int getX(){
    return this.pos_x;
  }

  /**
   * Renvoie la coordonnée y de la cellule
   * @return la coordonnée y de la cellule
   */
  public int getY(){
    return this.pos_y;
  }

  public Status getStatus(){
    return this.Status;
  }

  public void setStatus(Status iStatus){
    this.Status=iStatus;
  }

  public int getNb() {
    return this.nb;
  }

  public void setNb(int newNb) {
    this.nb = newNb;
  }

  public boolean isVisible() {
    return isVisible;
  }

  public void setToVisible() {
    this.isVisible = true;
  }

  public String toString(){
    return "This cell has coordinate (" + this.getX() + "," + this.getY() + ") and is " + this.getStatus();
  }

  public List<Entity> getEntities(){
    return this.entities;
  }

  public Map<Cardinals, Cell> getNeighbours() {
    return this.accessibleNeighbours;
  }

  public void addNeighbour(CellChessType neighbour, Cardinals direction) {
    this.accessibleNeighbours.put(direction, neighbour);
  }

  public void addEntity(Entity newEntity){
    this.entities.add(newEntity);
    this.entities.sort((A, B) -> cmpPriority(A,B));
  }

  public void removeEntity(Entity pEntity) {
    this.entities.remove(pEntity);
    this.entities.sort((A, B) -> cmpPriority(A,B));
  }
  
  public int cmpPriority(Entity A, Entity B){
    int prioA = A.getPriority();
    int prioB = B.getPriority();
    if (prioA>prioB){
      return 1;
    }
    else if (prioA == prioB){
      return 0;
    }
    else {
      return -1;
    }
  }

  public boolean equals(Object o){
    if(o instanceof CellChessType){
      CellChessType other=(CellChessType)o;
      if(this.getX()==other.getX() && this.getY()==other.getY() && this.getStatus()==other.getStatus()){
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Hero h = new Hero("Pol", 100, 10);
    LabyrinthChessType maze = new LabyrinthChessType(15,15);
    Weapon arcEbene = new Weapon("arc d\'ébène", "Un arc qui vient de loin, les traces d'usure sur le manche laisse entnedre qu'il a vécu.", 15, 40, 35, 10, "{|");
    h.setLocation(maze.getStartCell());
    arcEbene.setLocation(maze.getStartCell());
    maze.getStartCell().addEntity(arcEbene);
    maze.getStartCell().addEntity(h);
    System.out.println(maze.getStartCell().getEntities());
  }
}
