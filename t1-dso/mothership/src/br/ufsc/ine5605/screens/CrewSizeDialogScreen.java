package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.exceptions.EmptyFieldsException;
import br.ufsc.ine5605.exceptions.FieldsWithIncorrectValuesException;

import javax.swing.*;
import java.awt.*;

public class CrewSizeDialogScreen extends Screen {

    private static final String CREW_SIZE_LIMIT = "Type in a number between 0 and 5!";
    private boolean hasCreatedAlready = false;
    
    private JTextField crewSizeNumberTextField;
    
    private JButton confirmButton;

    private RegisterSpaceShipScreen parent;

    public CrewSizeDialogScreen(RegisterSpaceShipScreen parent){
        this.parent = parent;
    }

    public void showCrewSizeDialog(){
        if( hasCreatedAlready ){
            setVisible(true);
            crewSizeNumberTextField.setText("");
        } else {
            getContentPane().setLayout(new GridBagLayout());
            constraints = new GridBagConstraints();

            renderScreen();
            setButtonListeners();

            setResizable(false);
            hasCreatedAlready = true;
            setSize(new Dimension(300, 150));
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    private void setButtonListeners() {
        confirmButton.addActionListener(e ->{
            if( hasEmptyFields() ){
                try{
                    throw new EmptyFieldsException();
                }catch(EmptyFieldsException exception){
                    showDialog(exception.getMessage());
                }
            } else if( !fieldsHaveCorrectValues() ){
                try{
                    throw new FieldsWithIncorrectValuesException("The crew size typed is impossible! Please, correct it.");
                }catch(FieldsWithIncorrectValuesException exception){
                    showDialog(exception.getMessage());
                }
            } else {
                setVisible(false);
                parent.setCrewSize(Integer.parseInt(crewSizeNumberTextField.getText()));
            }
        });
    }

    private void renderScreen() {
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel title = new JLabel("Type in the size of the crew to-be registered...");
        getContentPane().add(title, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        crewSizeNumberTextField = new JTextField();
        crewSizeNumberTextField.setToolTipText(CREW_SIZE_LIMIT);
        crewSizeNumberTextField.setPreferredSize(new Dimension(200 ,30));
        getContentPane().add(crewSizeNumberTextField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        confirmButton = new JButton("Enter crew size");
        confirmButton.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(confirmButton, constraints);
    }

    private boolean hasEmptyFields() {
        return crewSizeNumberTextField.getText().isEmpty();
    }

    private boolean fieldsHaveCorrectValues() {
        try{
            Integer.parseInt(crewSizeNumberTextField.getText());
        }catch(NumberFormatException e){
            showDialog("A crew size should have a numeric value, and not \""+crewSizeNumberTextField.getText()+"\". Please, change it.");
        }

        int size = Integer.parseInt(crewSizeNumberTextField.getText());

        if( size >= 0 && size <= 5 ){
            return true;
        }

        return false;
    }

}
