package jeu.entity.pnjs;

import jeu.entity.pnjs.*;
import jeu.entity.pnjs.questions.*;
import java.util.*;
import jeu.entity.*;
import jeu.entity.items.*;
import jeu.util.*;
import jeu.exceptions.*;


public class PnjsMain{

    public static void main(String args[]){
        try{
        PNJ finnAndJake = new PNJ(2,200,300,"Finn&Jake","F");
        Weapon arcEbene = new Weapon("arc d\'ébène", "Un arc qui vient de loin, les traces d'usure sur le manche laisse entendre qu'il a vécu.", 15, 40, 35, 10, "{|");
        List<Item> market = new ArrayList();
        Artefact krys = new Artefact("Krys", "Une arme dont l'origine est inconnue, sa lame mortel, dans les temps passés, était empoisonnée.\n Aujourd'hui l'arme fait l'objet d'un intérêt particuliler chez les chercheurs de trésor.", 7, 180, "krys_muad_dib");
        market.add(arcEbene);
        Merchant jackTheRipper = new Merchant(2,"JackTheRipper",market);
        Hero player = new Hero("Ragnar Lothbrok", 150, 300);
        player.addMoney(100);
        jackTheRipper.sellItem(player,arcEbene);
        System.out.println(arcEbene.getMarketValue());
        System.out.println(player.getMoney());
        jackTheRipper.buyBackItem(player,arcEbene);
        System.out.println(player.getMoney());
        System.out.println(arcEbene.getMarketValue());
        System.out.println(finnAndJake.talkTo(Action.SALUTATION));
        System.out.println(jackTheRipper.talkTo(Action.SALUTATION));
        jackTheRipper.buyBackItem(player,krys);
        }
        catch(UnknownItemException e){
            System.out.println(e);
        }
    }
}