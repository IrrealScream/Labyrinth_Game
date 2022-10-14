package jeu.labyrinth.cells;

import jeu.labyrinth.*;
import jeu.entity.*;
import java.util.*;

public interface Cell{

  public int getX();

  public int getY();

  public String toString();

  public List<Entity> getEntities();

  public void addEntity(Entity pEntity);

  public void removeEntity(Entity pEntity);

  public boolean equals(Object o);

  public Map<Cardinals, Cell> getNeighbours();

  public void setToVisible();

  public boolean isVisible();
}
