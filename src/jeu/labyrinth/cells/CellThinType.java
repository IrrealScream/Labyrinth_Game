package jeu.labyrinth.cells;

import jeu.entity.*;
import jeu.entity.pnjs.*;
import jeu.labyrinth.cells.*;
import java.util.*;

public class CellThinType implements Cell{

  private int pos_x;
  private int pos_y;
  private boolean isVisible;
  private Map<Cardinals,Boolean> walls;
  private List<Entity> entities;
  private Map<Cardinals,Cell> accessibleNeighbours;

  public CellThinType(int ipos_x ,int ipos_y){
    this.pos_x=ipos_x;
    this.pos_y=ipos_y;
    this.isVisible = false;
    this.walls= new HashMap <Cardinals,Boolean>();
    this.walls.put(Cardinals.NORTH,true);
    this.walls.put(Cardinals.SOUTH,true);
    this.walls.put(Cardinals.WEST,true);
    this.walls.put(Cardinals.EAST,true);
    // { NORTH : True , SOUTH : FALSE}
    this.entities = new ArrayList<Entity>();
    this.accessibleNeighbours = new HashMap<Cardinals,Cell>();
  }

  /*
  *@return the x pos of the Cell
  */
  public int getX(){
    return this.pos_x;
  }

  /*
  *@return the y pos of the Cell
  */
  public int getY(){
    return this.pos_y;
  }

  public boolean isVisible() {
    return isVisible;
  }

  public void setToVisible() {
    this.isVisible = true;
  }

  /*
  *@param Entity A , An entity  A
  *@param Entity B , An entity B
  *@return an int which is priority of A above or under B
  */
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

  /*
  *@param Entity , Added entity to a list
  */
  public void addEntity(Entity pEntity) {
    this.entities.add(pEntity);
    this.entities.sort((A, B) -> cmpPriority(A,B));
  }

  /*
  *@param Entity , removed entity from the list
  */
  public void removeEntity(Entity pEntity){
    this.entities.remove(pEntity);
    this.entities.sort((A, B) -> cmpPriority(A,B));
  }

  /*
  *@return return the list of Entities
  */
  public List<Entity> getEntities(){
    return this.entities;
  }

  /*
  *@return return map of Cardinal and Boolean
  */
  public Map<Cardinals,Boolean> getWalls(){
    return this.walls;
  }

  /*
  *@param Card , remove the wall in the direction of Cardinals 
  */
  public void removeWall(Cardinals card){
    this.walls.remove(card);
    this.walls.put(card,false);
  }

  public String toString(){
    return "This cell has coordinate (" + this.getX() + "," + this.getY() + ") and is " + this.getWalls();
  }

  public boolean equals(Object o){
    if(o instanceof CellThinType){
      CellThinType other=(CellThinType)o;
      if(this.getX()==other.getX() && this.getY()==other.getY() && this.getWalls().equals(other.getWalls())){
        return true;
      }
    }
    return false;
  }

  public Map<Cardinals, Cell> getNeighbours() {
    return this.accessibleNeighbours;
  }

    public void addNeighbour(CellThinType neighbour, Cardinals direction) {
    this.accessibleNeighbours.put(direction, neighbour);
  }
}
