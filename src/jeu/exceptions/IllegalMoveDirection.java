package jeu.exceptions;

public class IllegalMoveDirection extends Exception {
    
    public IllegalMoveDirection() {
        super();
    }

    public IllegalMoveDirection(String message) {
        super(message);
    }
}