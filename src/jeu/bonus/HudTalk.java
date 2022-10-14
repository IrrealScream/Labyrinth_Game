package jeu.bonus;

public class HudTalk{

    protected String hud;
    private String face;

    public HudTalk(){
this.face= "   .------\\ /------."+"\n";
this.face+="   |       -       |"+"\n";
this.face+="   |               |"+"\n";
this.face+="   |               |"+"\n";
this.face+="   |               |"+"\n";
this.face+="______________________"+"\n";
this.face+="===========.==========="+"\n";
this.face+="  / ~~~~~     ~~~~~ \\ "+"\n";
this.face+="/|     |     |\\       "+"\n";
this.face+="W   ---  / \\  ---   W "+"\n";
this.face+="\\.      |o o|      ./"+"\n";
this.face+=" |                  |"+"\n";
this.face+=" \\   #########     /"+"\n";
this.face+="  \\  ## ----- ##  /"+"\n";
this.face+="   \\##         ##/"+"\n";
this.face+="    \\_____v_____/ "+"\n";

    }

    public String summonHudTalk(){
        String res= this.face+"\n"+"          |\\"+"\n"+"          | \\"+"\n" + "          |  \\"+"\n";
        res+="▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇"+"\n"; //100 blocs
        for(int i = 0 ; i < 10 ; i++){
            res+="▇                                                                                                  ▇"+"\n";
        }
        res+="▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇";
        return res; 
    }
}