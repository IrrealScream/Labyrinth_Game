package jeu;

import jeu.util.*;

import jeu.labyrinth.*;
import jeu.labyrinth.cells.*;

import jeu.entity.*;
import jeu.entity.pnjs.*;
import jeu.entity.items.*;
import jeu.exceptions.*;
import jeu.quests.*;
import jeu.quests.types.*;
import jeu.entity.pnjs.questions.*;

import java.util.*;


public class Game {

    private boolean runnning = true;
    private int numTour = 1;
    private GameRenderer renderer;
    private int terminalWidth = 50;

    private Labyrinth maze;
    private int mazeWidth = 10;
    private int mazeHeight = 10;
    private List<Entity> lstEntities;
    private List<Gem> lstGems;
    private List<Cell> lstCells;
    private Hero player;
    private ExitQuest mainQuest;
    private Asker dragon;

    // Constructeur
    public Game() {
        renderer = new GameRenderer();
        lstEntities = new ArrayList<Entity>();
        lstCells = new ArrayList<Cell>();
    }

    // La génération du jeu : le labyrinth, le hero, les pnjns, les items, les quêtes, etc...
    private void generation() {
        // Génère le labyrinth puis les items et les personnages ainsi que la quete mais encore beaucoup d'autre chose sans oublier les autres trucs.
        // Dialogue utilisateur au départ pour le choix de la configuration
        
        generationLabyrinth();
        generationHero();
        generationGems();
        generationItemsPnjs();
    }

    private void generationLabyrinth() {
        System.out.println("#".repeat(terminalWidth));
        System.out.println("#".repeat(terminalWidth));
        mazeWidth = renderer.askMazeInt("largeur du labyrinthe --> ");
        terminalWidth += (mazeWidth * 5);
        mazeHeight = renderer.askMazeInt("hauteur du labyrinthe --> ");
        this.maze = renderer.askMazeType(mazeWidth, mazeHeight);

    }

    private void generationGems() {
        RandomUtil rnd = new RandomUtil();
        lstCells = this.maze.getAirCells();
        int nbAirCells = lstCells.size();
        this.lstGems = new ArrayList<Gem>();
        for (int i =0; i< nbAirCells/2; i++) {
            Gem gem = new Gem();
            lstGems.add(gem);
        }

        // On place d'abord les gemmes
        for (Gem gem : this.lstGems) {
            Cell c = rnd.getRandomCell(lstCells);
            while (c.getEntities().size() != 0) {
                c = rnd.getRandomCell(lstCells);
            }
            gem.setLocation(c);
        }
    }

    private void generationItemsPnjs() {
        RandomUtil rnd = new RandomUtil();
        Artefact krys = new Artefact("Krys", "Une arme dont l'origine est inconnue, sa lame mortel, dans les temps passés, était empoisonnée.\n Aujourd'hui l'arme fait l'objet d'un intérêt particuliler chez les chercheurs de trésor.", 7, 180, "krys_muad_dib");
        Artefact Rolex = new Artefact("Rolex", "Une belle rolex. Mais que fait elle là ?", 2, 2000, "la_rolex_du_banquier_suisse");
        Artefact Bouton = new Artefact("bouton", "Le bouton de la guerre des boutons", 1, 10, "bouton_de_la_guerre_des_boutons");
        Item Burger = new Item("burger", "Le meilleur hamburger du coin, un peu poussiéreux mais en même quand on a faim...", 200, 10, "\uD83E\uDEC0 ");
        lstEntities.add(krys);
        lstEntities.add(Rolex);
        lstEntities.add(Bouton);
        lstEntities.add(Burger);
        Weapon arcEbene = new Weapon("arc d\'ébène", "Un arc qui vient de loin, les traces d'usure sur le manche laisse entendre qu'il a vécu.", 15, 40, 35, 10, "{|");
        lstEntities.add(arcEbene);
        krys.setLocation(rnd.getRandomCell(this.lstCells));
        Rolex.setLocation(rnd.getRandomCell(this.lstCells));
        Bouton.setLocation(rnd.getRandomCell(this.lstCells));
        Burger.setLocation(rnd.getRandomCell(this.lstCells));

        List<Item> market = new ArrayList();
        market.add(arcEbene);
        Merchant jackTheRipper = new Merchant(2,"JackTheRipper",market);
        jackTheRipper.setLocation(rnd.getRandomCell(this.lstCells));
        dragon = new Asker(2,"El Dragone");
        dragon.setLocation(rnd.getRandomCell(this.lstCells));
        Question q1 = new Question("3! ?", "6");
        dragon.addQuestion(q1);
        dragon.setClue("La sortie est sur la " + maze.getEndCell().getY() + "eme ligne.");
    }


    private void generationHero() {
        // Le héros
        player = new Hero("Ragnar Lothbrok", 150, 300);
        player.setDescription("Un illustre roi viking");
        player.setLocation(maze.getStartCell());
        player.addMoney(12);
        // La quête
        mainQuest = new ExitQuest("Quête principale", 3000, maze.getEndCell());


    }

    /** Définie des cases aléatoire pour les entités dans les cases vides du labyrinthe
     * @param pListEntities La liste des entités à placer.
     * @param pListCells La liste des cellules disponible.
     */
    private void setRandomLocationOfEntities(List<Cell> pListCells) {
        RandomUtil rnd = new RandomUtil();
        // On place chaque entité
        for(Entity ent : lstEntities) {
            // Pour chaque entité on lui trouve une case aléatoire
            Cell c = rnd.getRandomCell(pListCells);
            c.addEntity(ent);
            ent.setLocation(c);
        }

    }


    // Lance le jeu !
    private void launch() {
        // Lance le jeu
        

        // La boucle du jeu
        while(runnning) {
            this.update();
            numTour++;
        }
    }

    // Update
    private void update() {

        // Update tout

        // Si le hero est

        // Update les entités pour ce tour
        for(Entity ent : lstEntities) { ent.update(); }

        // Dessine les informations, le labyrinthe, etc...
        // Version avec l'inventaire à droite
        // renderer.Draw(maze, renderer.drawInventory(player.getInventory()), 
        //                     renderer.drawHeadInformations(numTour), 
        //                     renderer.drawFootInformations(),
        //                     terminalWidth);

        // Version avec les entités présentes sur la case à droite
        renderer.Draw(maze, (renderer.drawEntities(player.getLocation().getEntities())), 
                            renderer.drawHeadInformations(numTour), 
                            renderer.drawFootInformations(),
                            terminalWidth);
        
        boolean tourFini = false;
        while(!tourFini) {
            // Attend une réponse du joueur (commande)
            Action myAction = renderer.askCommand();

            switch(myAction) {
                case HELP:
                    System.out.println(renderer.drawHelp());
                    break;
                case TALK:
                    try {
                        PNJ p = renderer.askWichPNJ(player.getLocation().getEntities());
                        if(p instanceof Merchant) {
                            Merchant merc = (Merchant) p;
                            Action act = null;
                            while (act != Action.GOODBYE) {
                                act = renderer.askWichAction();
                                if (act == Action.SALUTATION) {
                                    System.out.println(merc.talkTo(Action.SALUTATION));
                                }
                                
                                else if (act == Action.SELL) {
                                    try {
                                        Item item = renderer.askWichItemsToSellBuy(player.getInventory(), true);
                                        try {
                                            merc.buyBackItem(this.player, item);
                                        }
                                        catch (UnknownItemException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println(merc.talkTo(Action.SELL));
                                    }
                                    catch(NoEntitiesException e) {
                                        System.out.println("Objet introuvable dans l'inventaire du joueur.");
                                    }
                                }

                                else if (act == Action.BUY) {
                                    try {
                                        Item item = renderer.askWichItemsToSellBuy(merc.getMarket(), true);
                                        try {
                                            merc.sellItem(this.player, item);
                                        }
                                        catch (UnknownItemException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println(merc.talkTo(Action.BUY));
                                    }
                                    catch(NoEntitiesException e) {
                                        System.out.println("Objet introuvable dans le stock du marchand.");
                                    }
                                }

                                else if (act == Action.OKAY) {
                                    System.out.println(merc.talkTo(Action.OKAY));
                                }

                                else if (act == Action.GOODBYE) {
                                    System.out.println(merc.talkTo(Action.GOODBYE));
                                }
                            
                            }
                        }
                        else if(p instanceof Asker) {
                            dragon.getRandomQuestion();
                            Scanner scanner = new Scanner(System.in);
                            boolean goodAnswer = false;
                            System.out.println("Bonjour mortel, voici l'enigme : ");
                            while(!goodAnswer) {
                                System.out.println(dragon.getCurrentQuestion().getQuestion());
                                String input = scanner.next();
                                if(dragon.getCurrentQuestion().checkAnswer(input)) {
                                    goodAnswer = true;
                                    System.out.println("Bien joué à toi voici l'indice : " + dragon.getClue());
                                    player.addMoney(50);
                                }
                                else {
                                    System.out.println("Essaye encore.");
                                }
                            }
                        }

                    }
                    catch(NoEntitiesException e) {
                        System.out.println("\tIl n'y a pas de personnages sur cette case");
                    }
                    tourFini = true;
                    break;
                case USE:
                    System.out.println("\tJoueur utilise clef !");
                    tourFini = true;
                    break;
                case TAKE:
                    try {
                        Item item = renderer.askWichItems(player.getLocation().getEntities());
                        if(item!=null) {
                            player.collectAnItem(item);
                            tourFini = true;
                        }
                    }
                    catch(NoEntitiesException e) {
                        System.out.println("Il n'y a pas d'objets sur cette case");
                    }
                    break;
                case DROP:
                    try {
                        Item item = renderer.askWichItems(player.getInventory(), true);
                        player.dropAnItem(item);
                        tourFini = true;
                    }
                    catch(NoEntitiesException e) {
                        System.out.println("Il n'y a pas d'objets sur cette case.");
                    }
                    break;
                case WALKNORTH:
                    try {
                        player.move(Cardinals.NORTH);
                        System.out.println("\tJoueur marche devant.");
                        tourFini = true;
                    } catch (IllegalMoveDirection e) {
                        System.out.println("\tVous ne pouvez pas marcher dans cette direction.");
                    }
                    break;
                case WALKWEST:
                    try {
                        player.move(Cardinals.WEST);
                        System.out.println("\tJoueur marche à gauche.");
                        tourFini = true;
                    } catch (IllegalMoveDirection e) {
                        System.out.println("\tVous ne pouvez pas marcher dans cette direction.");
                    }
                    break;
                case WALKSOUTH:
                    try {
                        player.move(Cardinals.SOUTH);
                        System.out.println("\tJoueur recule.");
                        tourFini = true;
                    } catch (IllegalMoveDirection e) {
                        System.out.println("\tVous ne pouvez pas marcher dans cette direction.");
                    }
                    break;
                case WALKEAST:
                    try {
                        player.move(Cardinals.EAST);
                        System.out.println("\tJoueur marche à droite.");
                        tourFini = true;
                    } catch (IllegalMoveDirection e) {
                        System.out.println("\tVous ne pouvez pas marcher dans cette direction.");
                    }
                    break;
                case SEE:
                    System.out.println("\tJoueur regarde autour de lui.");
                    System.out.println(lstEntities.get(0).getDescription());
                    break;
                case INVENTORY:
                    System.out.println("\tJoueur consulte son inventaire.");
                    System.out.println("\t\tOr : " + player.getMoney());
                    for (Item item : player.getInventory()) {
                        System.out.println("\t\t" + item.getName().substring(0, 1).toUpperCase() + item.getName().substring(1) + " : " + item.getDescription());                        
                    }
                    break;
                case EXIT:
                    runnning = false;
                    tourFini = true;
                    break;
                default:
                    System.out.println("      <!Action inexistante!>\n");
                    break;
            }

        }


        // La quête est remplie le joueur gagne
        mainQuest.solvingQuest(player);
        if(mainQuest.isresolved()) {
            runnning = false;
            System.out.println("*".repeat(terminalWidth));
            System.out.println("#".repeat(terminalWidth));
            System.out.println("*".repeat(terminalWidth));
            System.out.println("#".repeat(terminalWidth));
            System.out.println("*".repeat(terminalWidth));
            System.out.println("Bravo !!!");
        }
        // Si le hero est sur la case mais qu'il n'a pas assez d'or on l'informe.
        if(mainQuest.heroOnEndCell(player) && !mainQuest.isresolved()) {
            System.out.println("Vous ne pouvez pas sortir, il vous manque quelque chose...");
        }
    }

    public static void main(String[] args) {

        Game session = new Game();

        session.generation();

        session.launch();

    }
}
