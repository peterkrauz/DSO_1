package br.ufsc.ine5605.exceptions;

public class UnexistantMissionException extends Exception {

    public UnexistantMissionException(String message){
        super(message);
    }

    public UnexistantMissionException() {
        super();
    }
}
