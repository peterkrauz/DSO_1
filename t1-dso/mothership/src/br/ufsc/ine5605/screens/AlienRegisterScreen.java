package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.exceptions.EmptyFieldsException;
import br.ufsc.ine5605.models.Alien;

import javax.swing.*;
import java.awt.*;

public class AlienRegisterScreen extends Screen{

    private boolean hasCreatedAlready = false;

    private JTextField alienNameTextField;
    private JTextField alienClanTextField;

    private JButton confirmButton;

    private RegisterSpaceShipScreen parent;

    public AlienRegisterScreen(RegisterSpaceShipScreen parent){
        this.parent = parent;
    }

    public void registerAlien(boolean isCommander) {
        if( hasCreatedAlready ){
//            alienNameTextField.setText("");
//            alienClanTextField.setText("");
            setVisible(true);
        } else {
            getContentPane().setLayout(new GridBagLayout());
            constraints = new GridBagConstraints();

            renderScreen();
            setButtonListeners(isCommander);

            hasCreatedAlready = true;
            setResizable(false);
            setSize(new Dimension(300, 180));
            setVisible(true);
        }
    }

    private void renderScreen() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        JLabel title = new JLabel("Please, type in name and clan of the new alien.");
        getContentPane().add(title, constraints);

        constraints.gridy = 1;
        alienNameTextField = new JTextField();
        alienNameTextField.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(alienNameTextField, constraints);

        constraints.gridy = 2;
        alienClanTextField = new JTextField();
        alienClanTextField.setPreferredSize(new Dimension(200 ,30));
        getContentPane().add(alienClanTextField, constraints);

        constraints.gridy = 3;
        confirmButton = new JButton("Register Alien");
        confirmButton.setPreferredSize(new Dimension(200, 30));
        getContentPane().add(confirmButton, constraints);
    }

    private void setButtonListeners(boolean isCommander){
        confirmButton.addActionListener(e -> {
            if( hasEmptyFields() ){
                try{
                    throw new EmptyFieldsException();
                }catch (Exception exception){
                    showDialog(exception.getMessage());
                }
            } else {
                if( isCommander ){
                    String name = alienNameTextField.getText();
                    String clan = alienClanTextField.getText();
                    parent.addCommander(new Alien(name, clan));
                    setVisible(false);
                } else {
                    String name = alienNameTextField.getText();
                    String clan = alienClanTextField.getText();
                    parent.addAlienToCrew(new Alien(name, clan));
                    setVisible(false);
                }
            }
        });
    }

    private boolean hasEmptyFields() {
        return alienNameTextField.getText().isEmpty() || alienClanTextField.getText().isEmpty();
    }
}
