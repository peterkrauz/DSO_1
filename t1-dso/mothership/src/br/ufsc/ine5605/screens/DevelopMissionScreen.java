package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.controllers.MainController;
import br.ufsc.ine5605.exceptions.EmptyFieldsException;
import br.ufsc.ine5605.exceptions.FieldsWithIncorrectValuesException;
import br.ufsc.ine5605.exceptions.UnexistantMissionException;

import javax.swing.*;
import java.awt.*;

public class DevelopMissionScreen extends Screen {

    private boolean hasCreatedAlready = false;

    private GridBagConstraints constraints;

    private JTextField missionIdTextField;

    private JButton confirmButton;

    public void showMissionDevelopmentScreen() {
        if( hasCreatedAlready ){
            missionIdTextField.setText("");
            setVisible(true);
        } else {
            getContentPane().setLayout(new GridBagLayout());
            constraints = new GridBagConstraints();
            hasCreatedAlready = true;

            renderScreen();
            setButtonListeners();

            setResizable(false);
            setSize(new Dimension(320, 150));
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    private void setButtonListeners() {
        confirmButton.addActionListener(e -> {
            if( hasEmptyFields() ){
                try{
                    throw new EmptyFieldsException();
                }catch(EmptyFieldsException exception){
                    showDialog(exception.getMessage());
                }
            } else if ( !fieldsHaveCorrectValues() ){
                try{
                    throw new FieldsWithIncorrectValuesException();
                }catch(FieldsWithIncorrectValuesException exception){
                    showDialog(exception.getMessage());
                }
            } else if(MainController.getInstance().getMissionById(Integer.parseInt(missionIdTextField.getText())) == null){
                try{
                    throw new UnexistantMissionException("There is no mission with id \""+ missionIdTextField.getText() +"\". Please, try another.");
                }catch (Exception exception){
                    showDialog(exception.getMessage());
                }
            } else {
                MainController.getInstance().handleDevelopMission(Integer.parseInt(missionIdTextField.getText()));
                setVisible(false);
            }
        });
    }

    private boolean hasEmptyFields() {
        return missionIdTextField.getText().isEmpty();
    }

    private boolean fieldsHaveCorrectValues() {
        try{
            Integer.parseInt(missionIdTextField.getText());
        }catch(NumberFormatException e){
            showDialog("A mission id should have a numeric value, and not \""+missionIdTextField.getText()+"\". Please, change it.");
        }

        return true;
    }

    private void renderScreen() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel missionDevelopLabel = new JLabel("Type in the id for the mission to be developed");
        getContentPane().add(missionDevelopLabel, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        missionIdTextField = new JTextField();
        missionIdTextField.setToolTipText(MainController.getInstance().getMissionIdsAvailableForDevelopment());
        missionIdTextField.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(missionIdTextField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        confirmButton = new JButton("Develop Mission");
        confirmButton.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(confirmButton, constraints);
    }

}
