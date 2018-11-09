package br.ufsc.ine5605.exceptions;

public class DuplicateMissionException extends Exception {

    public DuplicateMissionException() {
        super();
    }

    public DuplicateMissionException(String message) {
        super(message);
    }
}
