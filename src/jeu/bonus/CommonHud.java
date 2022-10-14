package jeu.bonus;

public class CommonHud{

    private String hud;

    public CommonHud(){
        this.hud="▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇"+"\n";
        for(int i = 0 ; i < 5 ; i++){
            this.hud+="▇                                      ▇"+"\n";
        }
        this.hud+="▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇"+"\n";
    }

    public String summonHud(){
        return this.hud;
    }
} 