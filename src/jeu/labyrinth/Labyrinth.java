package jeu.labyrinth;

import jeu.labyrinth.cells.*;
import java.util.*;

public interface Labyrinth{

  public void genLab();

  public String draw();

  public List<Cell> getAirCells();

  public Cell getStartCell();

  public Cell getEndCell();
}
