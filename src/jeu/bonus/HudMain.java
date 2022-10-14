package jeu.bonus;

import jeu.bonus.*;

public class HudMain{

    public static void main(String[] args){
        HudTalk hudTalk = new HudTalk();
        System.out.println(hudTalk.summonHudTalk());
        CommonHud hud = new CommonHud();
        System.out.println(hud.summonHud());
    }
}