package jeu.labyrinth;

import jeu.labyrinth.cells.*;
import java.util.*;
import java.lang.Math;

public class ThinType implements Labyrinth {

  private int length;
  private int width;
  private CellThinType[][] theCells;
  private List<Cell> theAir;
  private Cell startCell;
  private Cell endCell;

  // Constructor
  public ThinType(int x,int y) {
    this.length=x;
    this.width=y;
    this.theCells= new CellThinType[x][y];
    for (int i=0 ; i<x ; i++){
      for(int j=0 ; j<y ; j++){
        this.theCells[i][j]= new CellThinType(i,j);
      }
    }
    this.theAir= new ArrayList<Cell>();
    this.genLab();
  }

  /*
  *@return , return this length (int)
  */
  public int getLength(){
    return this.length;
  }

  /*
  *@return , return this width (int)
  */
  public int getWidth(){
    return this.width;
  }

  /*
  *@param , pos_x  position in x
  *@param , pos_y  position in y
  *@return , return a Cell in the lab(CellThinType)
  */
  public CellThinType getCell(int pos_x,int pos_y){
    return this.theCells[pos_x][pos_y];
  }

  /*
  *@return , return the drawing of the labyrinth
  */
  public String draw(){
    String res="";
    //Première ligne
    for(int i=0 ; i < this.length ; i++){
    res+="+-----";
    }
    res+="+";
    // On boucle sur toute la largueur
    for(int b=0 ; b < this.width ; b++){
      res+="\n";
      res+="|";
      for(int a=0 ; a < this.length ; a++){
        if(this.getCell(a,b).getWalls().get(Cardinals.EAST)){
          if(this.getCell(a,b).getEntities().size()>0 && this.getCell(a,b).isVisible()){
            res+=" "+this.getCell(a,b).getEntities().get(0).getLetter()+"  |"; // \u2595 une barre
          }
          else{
            res+="     |"; // \u2595 une barre
          }
        }
        else{
          if(this.getCell(a,b).getEntities().size()>0 && this.getCell(a,b).isVisible()){
            res+=" "+this.getCell(a,b).getEntities().get(0).getLetter()+"   "; // \u2595 une barre
          }
          else{
            res+="      ";
          }
        }
      }
      res+="\n";
      res+="+";
      for(int a=0 ; a < this.length ; a++){
        if(this.getCell(a,b).getWalls().get(Cardinals.SOUTH)){
          res+="-----+";
        }
        else{
          res+="     +";
            }
          }
        }
        return res;
      }
  

  /*
  *@param min , int about minimum
  *@param max, int about maximum
  *@return , return this width (int)
  */
  public int getRandomNumberInRange(int min, int max) {
      return (int)(Math.random() * (max - min + 1)) + min;
  }

  /*
  *@param CellThinType cell , Cellule a qui on souhaite les voisins
  *@return List<CellThinType> , List de cellules voisines
  */
  public List<CellThinType> getNeighborHood(CellThinType cell){
    List<CellThinType> cut = new ArrayList();
    int X=cell.getX();
    int Y=cell.getY();
    if(Y==0 || Y==this.width-1){
      if(Y==0){
        cut.add(this.getCell(X,Y+1));
      }
      else{
        cut.add(this.getCell(X,Y-1));
      }
      if(X!=0 && X!=this.length-1){
        cut.add(this.getCell(X-1,Y));
        cut.add(this.getCell(X+1,Y));
      }
      else if(X==0){
        cut.add(this.getCell(X+1,Y));
      }
      else{
        cut.add(this.getCell(X-1,Y));
      }
      return cut;
    }
    if(X==0 || X==this.length-1){
      if(X==0){
        cut.add(this.getCell(X+1,Y));
      }
      else{
        cut.add(this.getCell(X-1,Y));
      }
      if(Y!=0 && Y!=this.width-1){
        cut.add(this.getCell(X,Y-1));
        cut.add(this.getCell(X,Y+1));
      }
      else if(Y==0){
        cut.add(this.getCell(X,Y+1));
      }
      else{
        cut.add(this.getCell(X,Y-1));
      }
      return cut;
    }
    else{
      cut.add(this.getCell(X+1,Y));
      cut.add(this.getCell(X,Y+1));
      cut.add(this.getCell(X-1,Y));
      cut.add(this.getCell(X,Y-1));
      return cut;
    }
  }
  
  /*
  *@param CellThinType,  a Cell (probably in the laby) 
  *@param CellThinType[][] tab , an Array of CellThinType
  *@return boolean , return a boolean of if cell is in the Array
  */
  public boolean contains(CellThinType cell,CellThinType[][] tab){
    for(int i=0 ; i<this.length ; i++){
      for(int j=0 ; j<this.width ; j++){
        if(tab[i][j].equals(cell)){
          return true;
        }
      }
    }
    return false;
  }

  public void accessibleCells(CellThinType cell){
    List<CellThinType> part =this.getNeighborHood(cell);
    for(CellThinType iCell : part){
      if(cell.getX()+1==iCell.getX() && cell.getWalls().get(Cardinals.EAST)==false){
        cell.addNeighbour(iCell,Cardinals.EAST);
      }
      else if(cell.getX()-1==iCell.getX() && cell.getWalls().get(Cardinals.WEST)==false){
        cell.addNeighbour(iCell,Cardinals.WEST);
      }
      else if(cell.getY()+1==iCell.getY() && cell.getWalls().get(Cardinals.SOUTH)==false){
        cell.addNeighbour(iCell,Cardinals.SOUTH);
      }
      else if(cell.getY()-1==iCell.getY() && cell.getWalls().get(Cardinals.NORTH)==false){
        cell.addNeighbour(iCell,Cardinals.NORTH);
      }

    }
  }

  public void setAirCells(){
    for(CellThinType[] cells : theCells){
    for(CellThinType cell : cells ){
      Cell c= (Cell) cell;
      this.theAir.add(c);
      }
    }
  }

  public List<Cell> getAirCells(){
    return this.theAir;
  }

    public Cell getStartCell(){
      return this.startCell;
    }

    public Cell getEndCell() {
      return this.endCell;
    }


  public void genLab(){
    // On crée un tableau visité vide pour les cellules déjà visité
    boolean[][] visited= new boolean[this.length][this.width];
    // On crée une liste pile qui nous permet de savoir les cell à traités
    List<CellThinType> pile=new ArrayList<CellThinType>();
    // On récupère une cellule dans le tableau
    int firstOneL= this.getRandomNumberInRange(0,this.length-1);
    int firstOneW= this.getRandomNumberInRange(0,this.width-1);
    CellThinType firstOne = this.getCell(firstOneL,firstOneW);

    // On ajoute à la pile la toute première CellThinType et à visited pour tenir compte du fait qu'elle soit visité
    pile.add(firstOne);
    visited[firstOneL][firstOneW]= true;

    // Tant que pile n'est pas vide, on continue de chercher
    while(!pile.isEmpty()){
      // On choisis le sommet de la pile
      CellThinType choseCell=pile.get(pile.size()-1);
      // On prends une liste de Cellule voisine
      List<CellThinType> neighbor=this.getNeighborHood(choseCell);
      // On crée une liste de cellule vide qui prendra les cellules non visité
      List<CellThinType> neighborUpdate= new ArrayList();
      // On regarde que chaque cellule soit visité ou non dans la liste de Cellule voisine
      for(CellThinType cell : neighbor){
        if(!visited[cell.getX()][cell.getY()]==true){
          neighborUpdate.add(cell);
        }
      }
      // Tant que la liste des voisins non visité n'est pas vide
      if(!neighborUpdate.isEmpty()){
        // La cellule choisis dans la liste de voisins non visité
        int choseNeighborNB=this.getRandomNumberInRange(0,neighborUpdate.size()-1);
        CellThinType choseNeighbor=neighborUpdate.get(choseNeighborNB);
        // On décide de retirer les mur si la cellule choisi en fonction d'ou se trouve la cellule voisine
        if(choseCell.getX()==choseNeighbor.getX()+1){
          choseCell.removeWall(Cardinals.WEST);
          choseNeighbor.removeWall(Cardinals.EAST);
        }
        else if(choseCell.getX()==choseNeighbor.getX()-1){
          choseCell.removeWall(Cardinals.EAST);
          choseNeighbor.removeWall(Cardinals.WEST);
        }
        else if(choseCell.getY()==choseNeighbor.getY()+1){
          choseCell.removeWall(Cardinals.NORTH);
          choseNeighbor.removeWall(Cardinals.SOUTH);
        }
        else{
          choseCell.removeWall(Cardinals.SOUTH);
          choseNeighbor.removeWall(Cardinals.NORTH);
        }
        // On ajoute le voisin au sommet de la pile
        pile.add(choseNeighbor);
        // On initialise le voisin à true dans la liste visité
        visited[choseNeighbor.getX()][choseNeighbor.getY()]=true;
      }
      // Sinon on remove le sommet de la cellule
      else{
        pile.remove(choseCell);
      }
    }
    for(CellThinType[] cellsForNeighbors: this.theCells){
      for(CellThinType cellForNeighbor : cellsForNeighbors){
      this.accessibleCells(cellForNeighbor);
      }
    }
    this.setAirCells();
    int random1 = this.getRandomNumberInRange(0,this.getAirCells().size()-1);
    int random2 = this.getRandomNumberInRange(0,this.getAirCells().size()-1);
    while(random2 == random1) {
        random2 = this.getRandomNumberInRange(0,this.getAirCells().size()-1);
      }
    this.startCell = this.theAir.get(random1);
    this.startCell.setToVisible();
    this.endCell = this.theAir.get(random2);

  }

}
