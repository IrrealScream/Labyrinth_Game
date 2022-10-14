package jeu.quests.types;
import java.util.*;
import jeu.entity.*;
import jeu.labyrinth.cells.*;
import jeu.quests.*;

public class ExitQuest extends Quest{

    private int goldNeeded;
    private Cell endCell;

    public ExitQuest(String pname,int pGoldNeeded, Cell pEndCell){
        super(pname, "Il faut être sur la case de fin avec autant d'or.", "case de fin", "gold : " + pGoldNeeded);
        this.goldNeeded=pGoldNeeded;
        this.endCell = pEndCell;
    }
    
    public int getGoldNeeded(){
        return goldNeeded;
    }

    public boolean heroOnEndCell(Hero hero) {
        if(endCell.equals(hero.getLocation())){
            return true;
        }
        return false;
    }

    public void solvingQuest(Hero hero){
        if(this.getGoldNeeded()<=hero.getMoney() && endCell.equals(hero.getLocation())){
            this.resolved=true;
            System.out.println("Le labyrinthe vous ouvre ses portes tout l'or est vôtre!");
            this.exit();
        }
    }
}
