package jeu.labyrinth;

import jeu.labyrinth.cells.*;
import jeu.entity.*;
import java.util.*;

public class LabyrinthChessType implements Labyrinth {

    /* tableau contenant les cases du labyrinthe */
    private CellChessType[][] theCells;
    /* la largeur du labyrinthe */
    private int width;
    /* la longueur du labyrinthe */
    private int length;
    /* la liste des cases du labyrinthe qui sont de l'air */
    private List<CellChessType> theAir;
    /* la liste des cases du labyrinthe qui sont des murs internes */
    private List<CellChessType> theWalls;
    private CellChessType startCell;
    private CellChessType endCell;

    /** Créer un labyrinthe de type "échecs" c'est a dire avec des cases
     * Les dimensions du labyrinthe ne doivent pas être négative
     * @param pWidth  un entier spécifiant la largeur (horizontale) du labyrinthe
     * @param pLength un entier spécifiant la hauteur (verticale) du labyrinthe
     */
    public LabyrinthChessType(int pWidth, int pLength) {

        // Largeur, hauteur (on vérifie que les dimensions sont positives)
        // On ajoute 2 ligne et 2 colonnes car ce sont les murs obligatoire qui entourent
        if (pWidth < 1) {
            this.width = 3;
        }
        else if (pWidth % 2 == 1) {
            this.width = pWidth + 2;
        }
        else {
            this.width = pWidth + 3;
        }

        if (pLength < 1) {
            this.length = 3;
        }
        else if (pLength % 2 == 1) {
            this.length = pLength + 2;
        }
        else {
            this.length = pLength + 3;
        }

        // Initialisation du tableau et des listes
        this.theCells = new CellChessType[width][length];
        this.theAir = new ArrayList<CellChessType>();
        this.theWalls = new ArrayList<CellChessType>();

        genLab();

    }

    /** Modifie la cellule a tel position
     * @param pXpos un entier, la position x
     * @param pYpos un entier, la position y
     * @param pNewCell la nouvelle cellule
     */
    public void setCell(int pXpos, int pYpos, CellChessType pNewCell) {
        theCells[pXpos][pYpos] = pNewCell;
    }

    /** Donne la cellule a la position demandé
     * @param pXpos un entier, la position x
     * @param pYpos un entier, la position y
     */
    public CellChessType getCell(int pXpos, int pYpos) {
        return this.theCells[pXpos][pYpos];
    }

    public Cell getStartCell(){
        return (Cell) this.startCell;
    }

    public Cell getEndCell() {
        return (Cell) this.endCell;
    }

    /** Renvoie le dessin du labyrinth
     * @return String, le dessin du labyrinth
     */
    public String draw() {
        String labyrinthDessin = "";

        for (int y=0; y < length; y++) {
            for (int x=0; x < width; x++) {
                // Si la cellule est de l'air on affiche un espace
                CellChessType currentCell = this.theCells[x][y];
                if (currentCell.getStatus() == Status.AIR) {
                    if (currentCell.isVisible() && !currentCell.getEntities().isEmpty()) {
                        Entity entity = currentCell.getEntities().get(0);
                        labyrinthDessin += " " + entity.getLetter() + " ";
                    }
                    else {
                        labyrinthDessin += "    ";
                    }
                }
                else if (currentCell.getStatus() == Status.WALL) { // Si c'est un mur on affiche un X
                    labyrinthDessin += "\u2588\u2588\u2588\u2588";
                }
                // Dans le cas ou la cellule est EMPTY, on affiche un € c'est a dire il y a une erreur !
                if (currentCell.getStatus() == Status.EMPTY) {
                    labyrinthDessin += "€€€";
                }
            }
            labyrinthDessin += "\n";
            for (int x=0; x < width ; x++) {
                CellChessType currentCell = this.theCells[x][y];
                // Si la cellule est de l'air on affiche un espace
                if (currentCell.getStatus() == Status.AIR) {
                    labyrinthDessin += "    ";
                }
                else if (currentCell.getStatus() == Status.WALL) { // Si c'est un mur on affiche un carré
                    labyrinthDessin += "\u2588\u2588\u2588\u2588";
                }
                // Dans le cas ou la cellule est EMPTY, on affiche un € c'est a dire il y a une erreur !
                if (currentCell.getStatus() == Status.EMPTY) {
                    labyrinthDessin += "€€€€";
                }
            }
            labyrinthDessin += "\n";
        }

        return labyrinthDessin;
    }

    public void addNeighbours(CellChessType cell) {
        int x = cell.getX();
        int y = cell.getY();
        if (this.theCells[x][y-1].getStatus() == Status.AIR) {
            cell.addNeighbour(this.theCells[x][y-1], Cardinals.NORTH);
        }
        if (this.theCells[x][y+1].getStatus() == Status.AIR) {
            cell.addNeighbour(this.theCells[x][y+1], Cardinals.SOUTH);
        }
        if (this.theCells[x+1][y].getStatus() == Status.AIR) {
            cell.addNeighbour(this.theCells[x+1][y], Cardinals.EAST);
        }
        if (this.theCells[x-1][y].getStatus() == Status.AIR) {
            cell.addNeighbour(this.theCells[x-1][y], Cardinals.WEST);
        }
    }

    /** Renvoie true si la génération du labyrinthe est terminée, false sinon
     * @return true si la génération du labyrinthe est terminée, false sinon
     */
    private boolean isFinished() {
        int nb = this.theAir.get(0).getNb();
        for (CellChessType cell : theAir) {
            if (cell.getNb() != nb) {
                return false;
            }
        }
        return true;
    }

    /** Génère le labyrinthe chess type aléatiorement */
    public void genLab() {
        for (int i=0; i < width; i++) {
            for (int j=0; j < length; j++) {
                this.theCells[i][j] = new CellChessType(i, j);
            }
        }
        for (int i=0; i < this.width; i++) {
            this.theCells[i][0] = new CellChessType(i,0,Status.WALL);

            if (i%2 == 1) {
                this.theCells[i][1] = new CellChessType(i,1,Status.AIR);
                this.theAir.add(this.theCells[i][1]);
            }
            else {
                this.theCells[i][1] = new CellChessType(i,1,Status.WALL);
                if (i != 0 && i != this.width-1) {
                    this.theWalls.add(this.theCells[i][1]);
                }
            }
        }

        for (int i=0; i < this.width; i++) {
            for (int j=2; j < this.length-1; j++) {
                this.theCells[i][j] = new CellChessType(i,j,this.theCells[i][j-2].getStatus());
                if (this.theWalls.contains(theCells[i][j-2]) || (i != 0 && i!= this.width-1 && j%2 == 0)) {
                    this.theWalls.add(this.theCells[i][j]);
                }
                else if (this.theAir.contains(this.theCells[i][j-2])) {
                    this.theAir.add(this.theCells[i][j]);
                }
            }
        }
        for (int i=0; i < this.width; i++) {
            int j = this.length-1;
            this.theCells[i][j] = new CellChessType(i,j,Status.WALL);
        }

        int nb=0;
        for (CellChessType cell : theAir) {
            nb++;
            cell.setNb(nb);
        }


        boolean finished = false;
        Random rand = new Random();

        while (finished == false) {
            if (this.theWalls.size() != 0) {
                int randInt = rand.nextInt(this.theWalls.size());
                CellChessType randCell = this.theWalls.get(randInt);
                int x = randCell.getX();
                int y = randCell.getY();

                int cell1 = 0;
                int cell2 = 0;

                if (this.theAir.contains(this.theCells[x-1][y]) && this.theAir.contains(this.theCells[x+1][y])) {
                    cell1 = this.theCells[x-1][y].getNb();
                    cell2 = this.theCells[x+1][y].getNb();
                }

                else if (this.theAir.contains(this.theCells[x][y-1]) && this.theAir.contains(this.theCells[x][y+1])) {
                    cell1 = this.theCells[x][y-1].getNb();
                    cell2 = this.theCells[x][y+1].getNb();
                }

                if (cell1 != cell2) {
                    randCell.setStatus(Status.AIR);
                    randCell.setNb(cell1);
                    theWalls.remove(randCell);
                    theAir.add(randCell);

                    for (CellChessType cell : theAir) {
                        if (cell.getNb() == cell2) {
                            cell.setNb(cell1);
                        }
                    }
                }
            finished = this.isFinished();
            }
            else {
                finished = true;
            }

        }
        if (this.theAir.size() == 1) {
            this.startCell = this.theAir.get(0);
            this.endCell = this.theAir.get(0);
        }
        else {
            int random1 = rand.nextInt(this.theAir.size());
            int random2 = rand.nextInt(this.theAir.size());
            while(random2 == random1) {
                random2 = rand.nextInt(this.theAir.size());
            }
            this.startCell = this.theAir.get(random1);
            this.endCell = this.theAir.get(random2);
            for (CellChessType cell : this.theAir) {
                this.addNeighbours(cell);
            }
        }
        this.startCell.setToVisible();
    }

    /** Cette fonction renvoie la liste des cellules vides qui peut ensuite être parcourue.
    * @return List<CellChessType> La liste des cellules vides.
    */
    public List<Cell> getAirCells() {
        List<Cell> lstCells = new ArrayList<Cell>();
        for (CellChessType cell : this.theAir) {
            Cell c = (Cell) cell;
            lstCells.add(c);
        }
        return lstCells;
    }

    /** Ici on peut compiler et utiliser la classe chesstype pour tester les fonction ou autres.
     * @param args un tableau de String contenant les paramètres de la commande (flags)
     */
    public static void main(String[] args) {
        LabyrinthChessType maze = new LabyrinthChessType(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Hero player = new Hero("Ragnar Lothbrok", 150, 300);
        player.setLocation(maze.getStartCell());
        maze.getStartCell().addEntity(player);
        System.out.println(maze.getStartCell());
        System.out.println(maze.getStartCell().getNeighbours());
        String dessin = maze.draw();
        System.out.println(dessin);
    }
}
