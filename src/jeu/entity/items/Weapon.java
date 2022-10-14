package jeu.entity.items;
import jeu.entity.*;
import jeu.entity.items.*;

public class Weapon extends Item {
    /** Une arme est un item qui possèdent des dégats, une vie (nombre d'utilisation) */

    protected int damages = 0;
    protected int useLife = 1;

    /** Construit une arme :
     * @param String pName le nom de l'arme 
     * @param int pWeight le poid de l'arme 
     * @param int pMarketValue la valeur marchande de l'arme.
     * @param int pDamages les dégats de l'arme
     * @param int pUseLife la durée de vie de l'arme.
      */
    public Weapon(String pName, String pDescription, int pWeight, int pMarketValue, int pDamages, int pUseLife, String pLetter) {
        super(pName, pDescription, pWeight, pMarketValue, pLetter);
        setDamages(pDamages);
        setUseLife(pUseLife);
    }

    /** Definie les dégats de l'arme
     * @param int pDamages les dégats de l'arme.
    */
    public void setDamages(int pDamages) {
        damages = pDamages;
    }

    /** Renvoie les dégats de l'arme 
     * @return int damages les dégats de l'arme.
     */
    public int getDamages() {
        return damages;
    }

    /** Definie les dégats de l'arme
     * @param int pUseLife la durée de vie de l'arme en nombre d'utilisation.
    */
    public void setUseLife(int pUseLife) {
        useLife = pUseLife;
    }

    /** Renvoie la durée de vie de l'arme en nombre d'utilisation
     * @return int useLife la durée de vie de l'arme en nombre d'utilisation.
     */
    public int getUseLife() {
        return useLife;
    }

    /** Utilise l'arme, permet de décrémenter sa durée de vie 
     * @return int Sa nouvelle durée de vie.
     */
    public int use() {
        setUseLife(useLife - 1);
        return getUseLife();
    }

    /** Methode equals
     * @param Object o l'objet à tester.
     * @return boolean true si o est le même objet que cette arme.
    */
    public boolean equals(Object o) {
        if(o instanceof Weapon) {
            Weapon other = (Weapon)o;
            if(this.getDamages()==other.getDamages() && this.getUseLife()==other.getUseLife() && super.equals(other)) {
                return true;
            }
        }
        return false;
    }

}