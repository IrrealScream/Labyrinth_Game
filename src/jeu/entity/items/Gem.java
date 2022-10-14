package jeu.entity.items;

import java.util.*;

public class Gem extends Item {
    
    public Gem() {
        super("", "", 0, 0, "  ");
        this.setGem();
        this.priority = 4;   
    }

    public void setGem() {
        double proba = Math.random();
        if (proba >= 0.5) {
            this.marketValue = 50;
            this.name = "Améthyste";
            this.description = "Une gemme commune, échangeable contre un peu d'or";
        }
        else if (proba < 0.5 && proba >= 0.3) {
            this.marketValue = 100;
            this.name = "Rubis";
            this.description = "Une pierre peu commune, elle peut être vendue pour une modique somme";
        }
        else if (proba < 0.3 && proba >= 0.15) {
            this.marketValue = 250;
            this.name = "Saphir";
            this.description = "Une pierre assez rare d'un bleu profond. On peut en tirer un bon prix";
        }
        else if (proba < 0.15 && proba >= 0.05 ) {
            this.marketValue = 500;
            this.name = "Emeraude";
            this.description = "Une gemme rare d'un éclat vert remarquable. Elle est échangeable contre une belle somme";
        }
        else {
            this.marketValue = 800;
            this.name = "Diamant";
            this.description = "Une pierre précieuse très rare et scintillante. Elle peut se vendre une fortune";
        }
    }
}
