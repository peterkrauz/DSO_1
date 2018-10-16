package br.ufsc.ine5605.exceptions;

public class UnexistantShipException extends Exception{

    public UnexistantShipException(String message){
        super(message);
    }

    public UnexistantShipException(){
        super();
    }

}
