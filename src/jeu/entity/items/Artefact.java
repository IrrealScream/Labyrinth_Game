package jeu.entity.items;
import java.util.*;

public class Artefact extends Item {
    /** Une Artefact est un item avec un poid, une valeur marchande, éventuellement une description */


    protected String identifiant; // l'identifiant de l'artefact permet de le rendre unique et de permettre a un pnj de le
                                  // reconnaître ou d'ouvrir une porte spécifique (la clef serait un artefact). 

    /** Construit un Artefact :
     * @param String pName le nom de l'arme.
     * @param int pWeight le poid de l'artefact
     * @param int pMarketValue la valeur marchande de l'artefact.
     * @param String pDescription la description de l'artefact.
     * @param String pID l'identifiant UNIQUE de l'artefact.
      */
    public Artefact(String pName, String pDescription, int pWeight, int pMarketValue, String pID) {
        super(pName, pDescription, pWeight, pMarketValue, "\uD83C\uDFFA");
        setId(pID);
    }

    /** Definie l'identifiant de l'artefact
     * @param String pId l'identifiant de l'artefact.
    */
    public void setId(String pId) {
        identifiant = pId;
    }

    /** Renvoie l'identifiant de l'artefact
     * @return String pId l'identifiant de l'artefact.
    */
    public String getId() {
        return identifiant;
    }

    /** Definie les dégats de l'arme
     * @param int pUseLife la durée de vie de l'arme en nombre d'utilisation.
    */
    public void setUseLife(int pUseLife) {
        //useLife = pUseLife;
    }

    /** Renvoie la durée de vie de l'arme en nombre d'utilisation
     * @return int useLife la durée de vie de l'arme en nombre d'utilisation.
     */
    public int getUseLife() {
        return 0;
    }

    /** Verifie que l'artefact a un identifiant unique par rapport à une liste d'item
     * @param List<Item> pLstItems la liste des items à parcourir.
     * @return Artefact or null renvoie l'artefact qui possède un identifiant similaire sinon renvoie null.
    */
    public Artefact checkIsUnique(List<Artefact> pLstItems) {
        for(Artefact art : pLstItems) {
            if(art.getId() == this.getId()) {return art;}
        }
        return null;
    }

    /** Methode equals
     * @param Object o l'objet à tester.
     * @return boolean true si o est le même objet que cette arme.
    */
    public boolean equals(Object o) {
        if(o instanceof Artefact) {
            Artefact other = (Artefact)o;
            if(this.getId()==other.getId() && super.equals(other)) {
                return true;
            }
        }
        return false;
    }

}