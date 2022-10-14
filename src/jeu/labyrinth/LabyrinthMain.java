package jeu.labyrinth;

import java.util.*;
import jeu.labyrinth.*;
import jeu.labyrinth.cells.*;
import jeu.entity.*;
import jeu.entity.pnjs.*;


public class LabyrinthMain {

	public static void main(String[] args) {
		// Demande a l'utilisateur la longueur et largeur du labyrinthe
		//Scanner clavier = new Scanner(System.in);
		//System.out.println("Veuillez saisir la longueur souhait�e du labyrinthe :");
		//int longueur=clavier.nextInt();
		//System.out.println("Veuillez saisir la largeur souhait�e du labyrinthe :");
		//int largeur=clavier.nextInt();



		ThinType Laby = new ThinType(11,11);

		System.out.println("C'est cela "+Laby.getAirCells().get(0));
		System.out.println(Laby.getStartCell());
		System.out.println(Laby.getEndCell());

		Hero Gon = new Hero("Gon",150,2);
		//Merchant Belethor = new Merchant(2,"Belethor",null,"Mdr");
		Boss Meruem = new Boss(1500,3,"Meruem",2000);
		Asker Sphynx = new Asker(0,"Sphynx");
		Laby.getCell(10,10).addEntity(Gon);
		//System.out.println(Laby.getCell(5, 5));
		//Laby.getCell(10,10).addEntity(Belethor);
		//Laby.getCell(12,12).addEntity(Meruem);
		//	Laby.getCell(20,15).addEntity(Sphynx);

		/*Laby.getCell(10,10).removeEntity(Gon);
		Laby.getCell(12,12).removeEntity(Meruem);
		Laby.getCell(10,10).removeEntity(Belethor);
		Laby.getCell(20,15).removeEntity(Sphynx);*/
		System.out.println(Laby.draw());
	}
}
