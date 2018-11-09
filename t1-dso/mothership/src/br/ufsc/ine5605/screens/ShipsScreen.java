package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.controllers.ShipsController;
import br.ufsc.ine5605.models.Alien;
import br.ufsc.ine5605.models.ShipContent;
import br.ufsc.ine5605.models.SpaceShip;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class ShipsScreen extends Screen {

    public ShipsScreen(){
        super();
    }

    @Override
    public int getUserChoice() {
        System.out.println("=======================================");
        System.out.println("============= Ships Panel =============");
        int choice = -1;
        while (choice == -1){
            System.out.println("\n{1} View Ships\n{2} Add Ship\n{3} Remove Ship\n{4} Update Ship\n{5} Quit Ships Panel");
            int tempoChoice = -1;
            try{
                tempoChoice = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("A mismatch happened while  trying to parse your option. Please retry.\n");
                scanner.next();
            }
            choice = tempoChoice;
        }
        return choice;
    }

    public ShipContent registerSpaceShip() {
        showMessage("======================================");
        showMessage("====== Ship Registration Screen ======");
        ShipContent shipContent = new ShipContent();
        showMessage("--> Registering commander first...");
        shipContent.commander = registerAlien();
        shipContent.crew = registerCrew();
        shipContent.id = registerId();
        showMessage("Ship created!\n");
        return shipContent;
    }

    private int registerId() {
        int id=-1;
        while( id == -1 ){
            try{
                showMessage("Type in the id of the ship.");
                id = scanner.nextInt();
                if( ShipsController.getInstance().hasShipWithId(id) ){
                    showMessage("There is already a ship with this id. Please choose another.");
                    id = -1;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid value for an id. Please retry.\n");
                scanner.next();
            }
        }
        if( id<0 ){
            id = -id;
        }
        return id;
    }

    private ArrayList<Alien> registerCrew() {
        ArrayList<Alien> crew = new ArrayList<>();
        boolean defined = false;
        int size;
        while (!defined){
            try{
                System.out.println("Type in the size of this ship's crew");
                size = scanner.nextInt();
                if(size < 1){
                    System.out.println("A lone voyage? Interesting.");
                    defined = true;
                    break;
                } else if(size>5){
                    System.out.println("That's too many aliens for a single ship. The max is 5.\n");
                } else {
                    int crewCount = 1;
                    for (int i=0; i<size; i++){
                        showMessage("Alien nº "+crewCount);
                        Alien alien = registerAlien();
                        crew.add(alien);
                        System.out.println("Alien "+alien.getName()+" recruited!");
                        crewCount++;
                    }
                    defined = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid value for a numeric size. Please retry.\n");
                scanner.next();
            }
        }
        return crew;
    }

    private Alien registerAlien() {
        showMessage("===== Alien Registration Screen ======");
        showMessage("Type in the name of the alien.");
        String name = scanner.next();
        showMessage("Type in the clan of the alien.");
        String clan = scanner.next();
        return new Alien(name, clan);
    }

    public void displayFleet() {
        StringBuilder sb = new StringBuilder("===== Viewing Ships =====");
        int counter = 1;
        for (SpaceShip spaceShip : ShipsController.getInstance().getSpaceShips()){
            sb.append("\nShip: "+counter);
            sb.append("\nCommander: "+displayAlien(spaceShip.getCommander()));
            if( spaceShip.getCrew().isEmpty() ){
                sb.append("\nEmpty crew");
            } else {
                sb.append("\nCrew:");
                for(Alien alien : spaceShip.getCrew()){
                    sb.append("\n\t"+displayAlien(alien));
                }
            }
            sb.append("\nStatus: "+(spaceShip.isAvailable() ? "Available" : "Unavailable"));
            sb.append("\nId: "+ spaceShip.getId()+"\n");
            counter++;
        }
        showMessage(sb.toString());
    }

    public String displayAlien(Alien alien){
        return alien.getName()+", of Clan "+alien.getClan();
    }

    public int askId() {
        return super.askId();
    }

    public void showMessage(String message){
        super.showMessage(message);
    }

    public void displayUpdatePanel() {
        showMessage("========= Ship Update Panel ===========");
        showMessage("Type in the id of the ship to-be updated\n");
    }

    public void displayRemovePanel() {
        showMessage("========= Ship Removal Panel ==========");
        showMessage("Type in the id of the ship to-be removed\n");
    }

    public ShipContent updateShip() {
        showMessage("==== SpaceShip Total Refactoring =====");
        return registerSpaceShip();
    }

    public String displayShipsIds() {
        StringBuilder sb = new StringBuilder("Ship's ids on your fleet:\n");
        for (SpaceShip spaceShip : ShipsController.getInstance().getSpaceShips()){
            sb.append(spaceShip.getId()+"\t");
        }
        return sb.toString();
    }
}
