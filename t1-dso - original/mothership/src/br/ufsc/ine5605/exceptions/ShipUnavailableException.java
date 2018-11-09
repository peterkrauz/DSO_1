package br.ufsc.ine5605.exceptions;

public class ShipUnavailableException extends Exception {

    public ShipUnavailableException(String message){
        super(message);
    }

    public ShipUnavailableException() {
        super();
    }

}
