package jeu.util;

import java.util.*;

import jeu.labyrinth.*;
import jeu.entity.*;
import jeu.entity.pnjs.*;
import jeu.entity.items.*;
import jeu.exceptions.*;

public class GameRenderer {

    String newline = "|NEWLINE|";

    public GameRenderer() {
    }

    public void Draw(Labyrinth pMaze, String pRightDraw, String pDrawHead, String pDrawFoot, int pTerminalWidth) {
        
        // Draw tout
        String top_line = "_".repeat(pTerminalWidth); // \u2581 une ligne
        String fdraw = processFinalDisplayText(pMaze.draw(), pRightDraw, top_line, pDrawHead, pDrawFoot);
        System.out.println(fdraw);

        
    }


    /** Permet d'afficher le labyrinth sur la gauche et les informations à droite
     * @param String le dessin du labyrinth.
     * @param String le dessin des informations de droite.
     * @param String pHeadSeparator La ligne separatrice en haut, sa taille doit être supérieure à celle du labyrinth car elle permet
     * cette fonction se base sur cette valeur pour écrire les informations à droite. Il y a retour à la ligne pour les lignes qui 
     * dépassent la largeur de ce séparateur.
     * @return String le dessin final.
     */
    private String processFinalDisplayText(String pLabyrinthDraw, String pRightDraw, String pHeadSeparator, String pHeadDraw, String pFootDraw) {
        // On a une largeur maximale pour l'affichage.
        int maxWidth = pHeadSeparator.length();
        // On récupère un tableau contenant les lignes du labyrinth.
        String[] labylines = pLabyrinthDraw.split("\n");
        int mazeWidth = labylines[0].length();

        // On vérifie que la largeur maximale est bien supérieur à celle du tableau.
        assert(maxWidth > mazeWidth);
        
        // On récupère les lignes des informations de droite, des informations d'en-tête et de pied.
        String[] rightLines = pRightDraw.split("\n");
        String[] headLines = pHeadDraw.split("\n");
        String[] footLines = pFootDraw.split("\n");
        
        // Le dessin finale contient d'abord le séparateur
        String finalDraw = (pHeadSeparator + '\n');
        
        // On ajoute l'en-tête qu'on transforme à la bonne taille.
        for (String line : headLines) {
            finalDraw += cutStringByWidth(line, maxWidth);
        }
        finalDraw += "\n";

        // On va transformer les infos à la bonne largeur.
        String rightInfos = "";
        for(String line : rightLines) {
            rightInfos += cutStringByWidth(line, maxWidth - mazeWidth - 3);
        }
        rightLines = rightInfos.split("\n");

        // On ajoute le labyrinthe avec les infos à droite.
        // Il faut regarder qui du labyrinthe ou des infos de droites a la plus grande hauteur.
        if(labylines.length >= rightLines.length) {

            // Le labyrinthe étant plus haut on va se baser sur lui.
            for(int i=0; i<labylines.length; i++) {
                String ttline = labylines[i] + "   ";
                if(i < rightLines.length) {
                    ttline += rightLines[i];
                }
                finalDraw += ttline + "\n";
            }
        }
        else {
            // Si les informations à droites prennent une plus grande hauteur alors on se base sur elles.
            for(int i=0; i<rightLines.length; i++) {
                String ttline = "";
                if(i < labylines.length) {
                    ttline += labylines[i];
                }
                else{
                    ttline += " ".repeat(labylines.length + 3);
                }
                ttline += rightLines[i];
                finalDraw += ttline + "\n";
            }
        }
        
        // On ajoute les informations de pied
        finalDraw += "\n";
        for(String line : footLines) {
            finalDraw += cutStringByWidth(line, maxWidth);
        }

        return finalDraw;
    }

    public int askMazeInt(String askedMessage) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean ok = false;
        while(!ok) {
            System.out.print(askedMessage);
            String input = scanner.next();

            if(isDigits(input)) {
                i = Integer.parseInt(input);
                // On vérifie que l'index entrer est valide
                if(i >= 2) { ok = true; }
                else {
                    System.out.print("\t<!trop petit!>\n");
                }    
            }
            else {
                System.out.print("\t<!taille entrée invalide!>\n");
            }    
        }
        return i;
    }

    public Labyrinth askMazeType(int pMazeW, int pMazeH) {
        Scanner scanner = new Scanner(System.in);
        Labyrinth maze = new LabyrinthChessType(2, 2);
        boolean ok = false;
        while(!ok) {
            System.out.print("type du labyrinthe [THIN] ou [CHESS] --> ");
            String input = scanner.next().toLowerCase();

            switch (input) {
                case "thin":
                case "thintype":
                case "typethin":
                case "thin type":
                    maze = new ThinType(pMazeW, pMazeH);
                    ok = true;
                    break;
                case "chess":
                case "chess type":
                case "chesstype":
                    maze = new LabyrinthChessType(pMazeW, pMazeH);
                    ok = true;
                    break;
                default:
                    System.out.print("\t<!type invalide!>\n");
                    break;
            }
        }
        return maze;
    }

    /** Dessine l'inventaire passé en paramètre
     * @param pInventory
     * @return String
     */
    public String drawInventory(List<Item> pInventory) {

        String res = "Inventaire : ";

        for(Item item : pInventory) {
            res += item.getLetter() + " (" + item.getName() + ")\t";
        }

        return res;
    }

    public String drawEntities(List<Entity> entities) {
        String res = "Sur cette case se trouvent : ";
        for (Entity ent : entities) {
            if (!(ent instanceof Hero)) {
                res += ent.getName() + "\t";
            }
        }
        return res;
    }

    

    /** Prend une ligne est en fait un texte en plaçant des retours à la ligne de sorte que le texte final
     * ne soit pas plus largeur que la valeur spécifiée.
     * @param pMaxWidth.
     * @return String le texte transformé.
     */
    public String cutStringByWidth(String pLine, int pMaxWidth) {
        int n = pLine.length()/pMaxWidth;
        String res = "";

        for(int l = 0; l < n; l++) {
            res += pLine.substring(l*pMaxWidth, l*pMaxWidth+pMaxWidth) + '\n';
        }
        res += pLine.substring(n * pMaxWidth);

        return res;
    } 


    /** Renvoie une chaine de caractères contenant les informations en haut de l'écran */
    public String drawHeadInformations(int pNTour) {
        String res = "Tour : " + pNTour + "\n";

        return res;
    }

    /** Les informations dans le pied de page
     * @return String les informations.
     */
    public String drawFootInformations() {
        String res = "Labyrinth, etage 1.";

        return res;
    }

    // Affiche l'aide aux commandes
    public String drawHelp() {
        return "\t[HELP, AIDE] : afficher l'aide\n\t"
                +"[TALK, PARLER, HABLAR] : parle avec les personnages de la case\n\t"
                +"[USE, UTILISE] : utilise un objet de l'inventaire\n\t"
                +"[HAUT, Z, NORD, NORTH] : marche vers le haut\n\t"
                +"[GAUCHE, Q, OUEST, WEST] : marche vers la gauche\n\t"
                +"[BAS, S, SUD, SOUTH] : marche vers le bas\n\t"
                +"[DROITE, D, EST, EAST] : marche à droite\n\t"
                +"[REGARDER] : le joueur regarde autour de lui\n\t"
                +"[QUITTE, QUIT, EXIT] : quitte le jeu\n\t"
                +"[INVENTAIRE, INVENTORY] : affiche l'inventaire du joueur\n";
    }

    public Action askCommand() {
        boolean cmd_end = false;
        Action myAction = Action.EXIT;

        while (!cmd_end) {
            System.out.print(">> action ? ");
            Scanner scanner = new Scanner(System.in);
            String user_command = scanner.next().toLowerCase();

            switch (user_command) {
                case "aide":
                case "help":
                    cmd_end = true;
                    myAction = Action.HELP;
                    break;
                case "parler":
                case "talk":
                case "hablar":
                    cmd_end = true;
                    myAction = Action.TALK;
                    break;
                case "utilise":
                case "use":
                    cmd_end = true;
                    myAction = Action.USE;
                    break;
                case "take":
                case "ramasser":
                    cmd_end = true;
                    myAction = Action.TAKE;
                    break;
                case "drop":
                case "throw":
                case "jeter":
                    cmd_end = true;
                    myAction = Action.DROP;
                    break;
                // Déplacements
                case "z":
                case "nord":
                case "north":
                case "haut":
                    cmd_end = true;
                    myAction = Action.WALKNORTH;
                    break;
                case "q":
                case "ouest":
                case "west":
                case "gauche":
                    cmd_end = true;
                    myAction = Action.WALKWEST;
                    break;
                case "s":
                case "sud":
                case "south":
                case "bas":
                    cmd_end = true;
                    myAction = Action.WALKSOUTH;
                    break;
                case "d":
                case "est":
                case "east":
                case "droite":
                    cmd_end = true;
                    myAction = Action.WALKEAST;
                    break;
                case "regarder":
                    cmd_end = true;
                    myAction = Action.SEE;
                    break;
                case "inspect":
                case "inspecter":
                    cmd_end = true;
                    myAction = Action.SEE;
                    break;
                case "buy":
                case "acheter":
                    cmd_end = true;
                    myAction = Action.SEE;
                    break;
                case "sell":
                case "vendre":
                    cmd_end = true;
                    myAction = Action.SEE;
                    break;
                case "inventaire":
                case "inventory":
                    cmd_end = true;
                    myAction = Action.INVENTORY;
                    break;
                case "quit":
                case "exit":
                case "quitte":
                    cmd_end = true;
                    myAction = Action.EXIT;
                    break;
                default:
                    System.out.print("\t<!action incorrect, entrer 'help' afficher l'aide aux commandes!>\n"); 
                    break;
            }
        }
        return myAction;

    }


    /** Demande à l'utilisateur de choisir un pnj parmie la liste
      * @param pLstEntities
      * @return int (si le retour est -1 alors il y a eu une erreur)
     */
    public PNJ askWichPNJ(List<Entity> pLstEntities) throws NoEntitiesException {
        // Affiche la liste des pnjs.
        List<PNJ> lstPNJs = new ArrayList();
        int i = 0;
        for(Entity ent : pLstEntities) {
            if(ent instanceof PNJ) {
                System.out.println("\t" + i + ": " + ent.getName());
                lstPNJs.add((PNJ)ent);
                i += 1;
            }
        }

        // Si il n'y a pas de personnages
        if(lstPNJs.isEmpty()) {
            throw new NoEntitiesException("\tIl n'y a pas de pnjs dans la liste des entitées.");
        }

        // Demande quel pnj.
        boolean ok = false;
        int iPnj = -1;
        while(!ok) {
            System.out.print(">> qui ? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            // On affiche les commandes (help)
            if("help".equals(input.toLowerCase())) {
                System.out.println("\tHELP : afficher l'aide\n      STOP : arrêter le dialogue.");
            }

            // Stop pour arrêter de parler.
            if("stop".equals(input.toLowerCase())) {
                return null;
            }

            if(isDigits(input)) {
                iPnj = Integer.parseInt(input);
                // On vérifie que l'index entrer est valide
                if(iPnj < i && iPnj >= 0) { ok = true; }
                else {
                    System.out.print("\t<!numéro de personnage invalide!>\n");
                }    
            }
            else {
                System.out.print("\t<!numéro de personnage invalide!>\n");
            }    

            
        }
        return lstPNJs.get(iPnj);
    }


    /** Demande à l'utilisateur de choisir un item parmie la liste
      * @param pLstEntities
      * @return int (si le retour est -1 alors il y a eu une erreur)
     */
    public Item askWichItems(List<Entity> pLstEntities) throws NoEntitiesException{
        // Affiche la liste des items.
        List<Item> lstItems = new ArrayList();
        int i = 0;
        for(Entity ent : pLstEntities) {
            if(ent instanceof Item) {
                System.out.println("\t" + i + ": " + ent.getName());
                lstItems.add((Item)ent);
                i += 1;
            }
        }

        // Si il n'y a pas d'objet
        if(lstItems.isEmpty()) {
            throw new NoEntitiesException("Il n'y a pas d'objet dans la liste des entitées.");
        }

        // Demande quel objet.
        boolean ok = false;
        int iItem = -1;
        while(!ok) {
            System.out.print(">> quoi ? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            // On affiche les commandes (help)
            if("help".equals(input.toLowerCase())) {
                System.out.println("\tHELP : afficher l'aide\n\tSTOP : arrêter de ramasser.");
            }

            // Stop pour arrêter de parler.
            if("stop".equals(input.toLowerCase())) {
                return null;
            }

            if(isDigits(input)) {
                iItem = Integer.parseInt(input);
                // On vérifie que l'index entrer est valide
                if(iItem < i && iItem >= 0) { ok = true; }
                else {
                    System.out.print("\t<!numéro de personnage invalide!>\n");
                }    
            }
            else {
                System.out.print("\t<!numéro de personnage invalide!>\n");
            }    

            
        }
        return lstItems.get(iItem);
    }
    /** Demande à l'utilisateur de choisir un item parmie la liste
      * @param pLstItems
      * @return int (si le retour est -1 alors il y a eu une erreur)
     */
    public Item askWichItems(List<Item> pListItems, boolean pOnlyItems) throws NoEntitiesException{
        // Affiche la liste des items.
        int i = 0;
        for(Item ent : pListItems) {
            System.out.println("\t" + i + ": " + ent.getName());
            i += 1;
        }

        // Si il n'y a pas d'objet
        if(pListItems.isEmpty()) {
            throw new NoEntitiesException("Il n'y a pas d'objet dans la liste des entitées.");
        }

        // Demande quel objet.
        boolean ok = false;
        int iItem = -1;
        while(!ok) {
            System.out.print(">> quoi ? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            // On affiche les commandes (help)
            if("help".equals(input.toLowerCase())) {
                System.out.println("\tHELP : afficher l'aide\n\tSTOP : arrêter de ramasser.");
            }

            // Stop pour arrêter de parler.
            if("stop".equals(input.toLowerCase())) {
                return null;
            }

            if(isDigits(input)) {
                iItem = Integer.parseInt(input);
                // On vérifie que l'index entrer est valide
                if(iItem < i && iItem >= 0) { ok = true; }
                else {
                    System.out.print("\t<!numéro de personnage invalide!>\n");
                }    
            }
            else {
                System.out.print("\t<!numéro de personnage invalide!>\n");
            }    

            
        }
        return pListItems.get(iItem);
    }
        /** Demande à l'utilisateur de choisir un item parmie la liste
      * @param pLstItems
      * @return int (si le retour est -1 alors il y a eu une erreur)
     */
    public Item askWichItemsToSellBuy(List<Item> pListItems, boolean pOnlyItems) throws NoEntitiesException{
        // Affiche la liste des items.
        int i = 0;
        for(Item ent : pListItems) {
            System.out.println("\t" + i + ": " + ent.getName() + " => " + ent.getMarketValue());
            i += 1;
        }

        // Si il n'y a pas d'objet
        if(pListItems.isEmpty()) {
            throw new NoEntitiesException("Il n'y a pas d'objet dans la liste des entitées.");
        }

        // Demande quel objet.
        boolean ok = false;
        int iItem = -1;
        while(!ok) {
            System.out.print(">> quoi ? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            // On affiche les commandes (help)
            if("help".equals(input.toLowerCase())) {
                System.out.println("\tHELP : afficher l'aide\n\tSTOP : arrêter de ramasser.");
            }

            // Stop pour arrêter de parler.
            if("stop".equals(input.toLowerCase())) {
                return null;
            }

            if(isDigits(input)) {
                iItem = Integer.parseInt(input);
                // On vérifie que l'index entrer est valide
                if(iItem < i && iItem >= 0) { ok = true; }
                else {
                    System.out.print("\t<!numéro de personnage invalide!>\n");
                }    
            }
            else {
                System.out.print("\t<!numéro de personnage invalide!>\n");
            }    

            
        }
        return pListItems.get(iItem);
    }

    public Action askWichAction() {
        List<Action> actions = new ArrayList<Action>();
        actions.add(Action.SELL);
        actions.add(Action.BUY);
        actions.add(Action.SALUTATION);
        actions.add(Action.GOODBYE);

        int i = 0;
        for(Action act : actions) {
            System.out.println("\t" + i + ": " + act);
            i += 1;
        }

        boolean ok = false;
        int action = -1;
        while (!ok) {
            System.out.print(">> quoi ? ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();

            // On affiche les commandes (help)
            if("help".equals(input.toLowerCase())) {
                System.out.println("\tHELP : afficher l'aide\n\tSTOP : arrêter de ramasser.");
            }

            // Stop pour arrêter de parler.
            if("stop".equals(input.toLowerCase())) {
                return null;
            }

            if(isDigits(input)) {
                action = Integer.parseInt(input);
                // On vérifie que l'index entrer est valide
                if(action < i && action >= 0) { ok = true; }
                else {
                    System.out.print("\t<!numéro d'action invalide!>\n");
                }    
            }
            else {
                System.out.print("\t<!numéro d'action invalide!>\n");
            }    
        }
        return actions.get(action);
    }
    
    private boolean isDigits(String text) {
        if (text == null) { return false; }
        return text.matches("[0-9]+");
    }

    public static void main(String[] args) {
        GameRenderer render = new GameRenderer();

        String s = "Bonjour tout le monde ! Comment ça va ??";
        s = render.cutStringByWidth(s, 10);

        System.out.println(s);
        System.out.println("testouille");

    }



}