package jeu.entity.items;

import jeu.entity.*;
import jeu.labyrinth.cells.*;

public class Item extends Entity {
    /** La classe Item hérite de Entity. Un item est une entité présente dans le labyrinthe uniquement lorsqu'il n'est
     * pas récupérer et ajouter à l'inventaire du joueur. Un item possède un poid, une description et une valeur marchande.
     */

    protected boolean isIntoInventory = false;
    protected int weight = 0;
    protected int marketValue = 0;

    /** Constructeur de l'item
     * @param String pName le nom de l'item
    */
    public Item(String pName, String pLetter) {
        super(pName, pLetter);
        this.priority = 3;
    }

    /** Constructeur de l'item
     * @param String pName le nom de l'item
     * @param int pWeight le poid de l'item 
    */
    public Item(String pName, String pDescription, String pLetter) {
        super(pName, pLetter);
        setDescription(pDescription);
        this.priority = 3;
    }

    /** Constructeur de l'item
     * @param String pName le nom de l'item
     * @param int pWeight le poid de l'item
     * @param int pMarketValue la valeur marchande de l'item
    */
    public Item(String pName, String pDescription, int pWeight, String pLetter) {
        super(pName, pLetter);
        setDescription(pDescription);
        setWeight(pWeight);
        this.priority = 3;
    }

    /** Constructeur de l'item
     * @param String pName le nom de l'item
     * @param int pWeight le poid de l'item
     * @param int pMarketValue la valeur marchande de l'item
     * @param String pDescription la description de l'item
    */
    public Item(String pName, String pDescription, int pWeight, int pMarketValue, String pLetter) {
        super(pName, pLetter);
        setDescription(pDescription);
        setWeight(pWeight);
        setMarketValue(pMarketValue);
        this.priority = 3;
    }


    /** Definie le poid de l'objet.
     * @param int pWeight le nouveau poid de l'objet.
      */
    public void setWeight(int pWeight) {
        weight = pWeight;
    }

    /** Voir le poid de l'objet.
     * @return int weight la valeur marchande de l'objet.
      */
    public int getWeight() {
        return weight;
    }

    /** Definie la valeur marchande de l'objet.
     * @param int pMarketValue la nouvelle valeur marchande de l'objet.
      */
    public void setMarketValue(int pMarketValue) {
        marketValue = pMarketValue;
    }

    /** Voir la valeur marchande de l'objet.
     * @return int marketValue la valeur marchande de l'objet.
      */
    public int getMarketValue() {
        return marketValue;
    }

    /** Renvoie true si l'objet est dans un inventaire
      * @return boolean est dans un inventaire.
      */
    public boolean isIntoInventory() {
        return isIntoInventory;
    }

    /** Ajoute l'item à un inventaire.
     */
    public void setIntoInventory() {
        isIntoInventory = true;
        locationCell = null;
    }

    /** Lorsque l'item est jeter depuis l'inventaire, il doit trouver une nouvelle location dans le labyrinthe 
     * @param pNewLocation (Cell) la nouvelle cellule de l'item.
    */
    public void dropFromInventory(Cell pNewLocation) {
        isIntoInventory = false;
        setLocation(pNewLocation);
    }

    /** Fonction update de l'item, appelé a chaque tour nrmlt. */
    public void update() {
        // Item dans l'inventaire implique pas de location dans le labyrinthe.
        if(isIntoInventory) { locationCell = null; }
    }

    public static void main(String[] args) {
        Item i = new Item(args[0], args[1]);
        i.dropFromInventory(new CellChessType(2, 6));
        i.update();
        System.out.println(i.isIntoInventory());
        i.setIntoInventory();
        i.update();
        System.out.println(i.isIntoInventory());

    }

    /** Methode equals
     * @param Object o l'objet à tester.
     * @return boolean true si o est le même objet que cette item.
    */
    public boolean equals(Object o) {
        if(o instanceof Item) {
            Item other = (Item)o;
            if(this.isIntoInventory()==other.isIntoInventory() && this.getWeight()==other.getWeight() 
            && this.getMarketValue()==other.getMarketValue() && this.getDescription()==other.getDescription()
            && super.equals(other)) {
                return true;
            }
        }
        return false;
    }
}
