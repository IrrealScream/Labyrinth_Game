package jeu.entity;

import jeu.labyrinth.cells.*;

public abstract class Entity {

    protected Cell locationCell;
    protected String name;
    protected String letter;
    protected String description = "";
    protected int priority;

    public Entity(String pName,String iLetter) {
        name = pName;
        letter=iLetter;
    }

    /** Change la location de l'entité
     * @param pNewLocation une Cell, la nouvelle position de l'entité
     */
    public void setLocation(Cell pNewLocation) {
        this.locationCell = pNewLocation;
        pNewLocation.addEntity(this);
    }

    /** Renvoie la position de l'entité */
    public Cell getLocation() {
        return this.locationCell;
    }

    /** Change le nom de l'entité
    @param pNewname nouveau nom
     */
    public void setName(String pNewName) {
        this.name = pNewName;
    }

    /** Renvoie le nom de l'entité */
    public String getName() {
        return this.name;
    }

    public String getLetter(){
      return this.letter;
    }

    /** Definie la description de l'item
     * @param String pDescription la nouvelle descrption de l'objet.
      */
    public void setDescription(String pDescription) {
        description = pDescription;
    }

    /** Renvoie la description de l'objet
     * @return String description.
      */
    public String getDescription() {
        return description;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setPriority(int newPriority){
        this.priority = newPriority;
    }

    public void update() {

    }

    /** Methode equals
     * @param Object o l'objet à tester.
     * @return boolean true si o est le même objet que cette entité.
    */
    public boolean equals(Object o) {
        if(o instanceof Entity) {
            Entity other = (Entity)o;
            if(this.getName()==other.getName() && this.getLocation()==other.getLocation()) {
                return true;
            }
        }
        return false;
    }

}
