package jeu.util;

import java.lang.Math;
import java.util.*;
import jeu.labyrinth.cells.*;

public class RandomUtil {


    public RandomUtil() {

    }

    public int getInt(int min, int max) {

        return (int)(Math.random() * (max - min + 1)) + min;

    }

    public Cell getRandomCell(List<Cell> pListCells) {
        return pListCells.get(getInt(0, pListCells.size()-1));
    }


    public static void main(String[] args) {
        RandomUtil rnd = new RandomUtil();

        // test getInt 
        System.out.println(rnd.getInt(0, 15));

    }

}