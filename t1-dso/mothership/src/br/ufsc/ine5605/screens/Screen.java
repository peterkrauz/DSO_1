package br.ufsc.ine5605.screens;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Screen extends JFrame {

    public Scanner scanner;

    public Screen(){}

    public Screen(String title){
        super(title);
        scanner = new Scanner(System.in);
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

    public void showDialog(String message, String title){
        JOptionPane.showMessageDialog(getContentPane(), message, title, 1);
    }

    public void showInputDialog(){
        JOptionPane.showMessageDialog(getContentPane(), "a");
    }

}

