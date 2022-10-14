package jeu.exceptions;

public class UnknownItemException extends Exception {
    
    public UnknownItemException() {
        super();
    }

    public UnknownItemException(String message) {
        super(message);
    }
}
