package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.controllers.MainController;
import br.ufsc.ine5605.handler.ButtonHandler;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Screen extends JFrame {

    public Scanner scanner;
    public GridBagConstraints constraints;

    public Screen(){}

    public Screen(String title){
        super(title);
        scanner = new Scanner(System.in);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainController.getInstance().openMissionsPanel();
                setVisible(false);
            }
        });
    }

    public int getUserChoice(){
        return 0;
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public int askId(){
        int id = -1;
        while (id == -1){
            try{
                id = scanner.nextInt();
            }catch(InputMismatchException e){
                showMessage("There was an input mismatch while trying to parse your input. Please, retry.");
                scanner.next();
            }
        }
        return id;
    }

    public void showDialog(String message){
        JOptionPane.showMessageDialog(getContentPane(), message);
    }

    public void renderMenu(String[] options, String[] toolTips, ButtonHandler handler) {
        for( int i=0; i<options.length; i++ ){
            JButton jButton = new JButton(options[i]);
            jButton.setPreferredSize(new Dimension(200, 30));
            jButton.addActionListener(handler);
            jButton.setToolTipText(toolTips[i]);
            constraints.gridy = i+3;
            getContentPane().add(jButton, constraints);
        }
    }


}

