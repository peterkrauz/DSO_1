package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.controllers.MainController;
import br.ufsc.ine5605.controllers.RegisterController;
import br.ufsc.ine5605.controllers.ShipsController;
import br.ufsc.ine5605.exceptions.EmptyFieldsException;
import br.ufsc.ine5605.exceptions.FieldsWithIncorrectValuesException;
import br.ufsc.ine5605.models.MissionContent;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class RegisterMissionScreen extends Screen{

    private boolean hasCreatedAlready = false;

    private GridBagConstraints constraints;

    private JTextField missionIdTextField;
    private JTextField missionDescriptionTextField;
    private JTextField spaceShipIdTextField;

    private JButton confirmButton;

    private MissionContent missionContent;

    private void renderScreen() {
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        JLabel missionIdLabel = new JLabel("ID");
        getContentPane().add(missionIdLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 8;
        missionIdTextField = new JTextField();
        missionIdTextField.setPreferredSize(new Dimension(180, 20));
        getContentPane().add(missionIdTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        JLabel missionDescriptionLabel = new JLabel("Description");
        getContentPane().add(missionDescriptionLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 8;
        missionDescriptionTextField = new JTextField();
        missionDescriptionTextField.setPreferredSize(new Dimension(180, 20));
        getContentPane().add(missionDescriptionTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        JLabel spaceShipIdLabel = new JLabel("Spaceship ID");
        getContentPane().add(spaceShipIdLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 8;
        spaceShipIdTextField = new JTextField();
        spaceShipIdTextField.setPreferredSize(new Dimension(180, 20));
        getContentPane().add(spaceShipIdTextField, constraints);

        constraints.gridx = 5;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.CENTER;
        confirmButton = new JButton("Register Mission!");
        getContentPane().add(confirmButton, constraints);
    }

    private void setScreenLabelTitle() {
        JLabel title = new JLabel("Type in the new Mission's ID, Description, and it's Spaceship's ID");
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 8;
        getContentPane().add(title, constraints);
    }

    public void showMissionRegisterScreen() {
        if( hasCreatedAlready == true ){
            missionIdTextField.setText("");
            missionDescriptionTextField.setText("");
            spaceShipIdTextField.setText("");
            setVisible(true);
        } else {
            getContentPane().setLayout(new GridBagLayout());
            constraints = new GridBagConstraints();
            missionContent = null;

            setScreenLabelTitle();
            renderScreen();
            setButtonListeners();

            hasCreatedAlready = true;
            setResizable(false);
            setSize(new Dimension(420, 250));
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
            } else if( !fieldsHaveCorrectValues() ){
                try{
                    throw new FieldsWithIncorrectValuesException();
                }catch(FieldsWithIncorrectValuesException exception){
                    showDialog(exception.getMessage());
                }
            }else if( !MainController.getInstance().idExistsInFleet(Integer.parseInt(spaceShipIdTextField.getText()))
                    || !ShipsController.getInstance().getShipById(Integer.parseInt(spaceShipIdTextField.getText())).isAvailable() ){
                showDialog("The spaceship id "+spaceShipIdTextField.getText()+" does not exist or isn't available. Please, type in an id that does exist.");
            }else if( MainController.getInstance().getMissionById(Integer.parseInt(missionIdTextField.getText())) != null){
                showDialog("The mission id "+Integer.parseInt(missionIdTextField.getText())+" already exists. Please, type another.");
            } else {
                // all fields are non-null, and have correct values. the id's exist as well
                missionContent = new MissionContent();
                missionContent.spaceShipId = Integer.parseInt(spaceShipIdTextField.getText());
                missionContent.id = Integer.parseInt(missionIdTextField.getText());
                missionContent.description = missionDescriptionTextField.getText();
                RegisterController.getInstance().setMissionContent(missionContent);
                setVisible(false);
            }
        });
    }

    private boolean fieldsHaveCorrectValues() {
        try{
            Integer.parseInt(missionIdTextField.getText()); // should parse to an integer
        }catch (NumberFormatException e){
            showDialog("A mission id should have a numeric value, and not \""+missionIdTextField.getText()+"\". Please, change it.");
        }

        try{
            Integer.parseInt(spaceShipIdTextField.getText()); // should parse to an integer
        }catch (NumberFormatException e){
            showDialog("A spaceship's id should have a numeric value, and not \""+spaceShipIdTextField.getText()+"\". Please, change it.");
        }

        return true;
    }

    private boolean hasEmptyFields() {
        return missionDescriptionTextField.getText().isEmpty() || missionIdTextField.getText().isEmpty() || spaceShipIdTextField.getText().isEmpty();
    }

    public void showScreenForMissionRemoval() {
        // TODO
    }
}
