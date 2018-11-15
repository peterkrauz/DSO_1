package br.ufsc.ine5605.exceptions;

public class FieldsWithIncorrectValuesException extends Exception {

    public FieldsWithIncorrectValuesException() {
        super("You have fields with incorrect values. Please change the fields to their proper values.");
    }

    public FieldsWithIncorrectValuesException(String message) {
        super(message);
    }
}
