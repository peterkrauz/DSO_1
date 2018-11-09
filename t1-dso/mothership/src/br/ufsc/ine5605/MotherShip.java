package br.ufsc.ine5605;

import br.ufsc.ine5605.controllers.MainController;

public class MotherShip {

    public static void main(String[] args) {
        MainController mainController = MainController.getInstance();
        mainController.run();
    }

}
