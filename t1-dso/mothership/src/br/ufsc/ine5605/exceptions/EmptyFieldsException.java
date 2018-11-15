package br.ufsc.ine5605.exceptions;

public class EmptyFieldsException extends Exception {
    public EmptyFieldsException() {
        super("There are empty fields. Please, fill them all in order to register properly.");
    }

    public EmptyFieldsException(String message) {
        super(message);
    }
}
