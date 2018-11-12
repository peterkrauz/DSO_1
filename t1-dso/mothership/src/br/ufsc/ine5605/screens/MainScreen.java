package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.controllers.MainController;
import br.ufsc.ine5605.constants.MissionState;
import br.ufsc.ine5605.controllers.ShipsController;
import br.ufsc.ine5605.exceptions.MissionCompletedException;
import br.ufsc.ine5605.exceptions.UnexistantMissionException;
import br.ufsc.ine5605.models.Mission;
import br.ufsc.ine5605.models.MissionContent;
import br.ufsc.ine5605.models.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MainScreen extends Screen{

    public MainScreen(){
        super("Mothership");
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel title = new JLabel("Welcome. Choose an option.");

        String[] options = new String[]{"Enter Ships Panel","Add Mission", "View Missions",
                                        "Remove Mission", "Develop Mission",
                                        "Read Mission Log", "Quit the Mothership"};

        String[] toolTips = new String[]{"Will redirect to the Ships Screen", "A dialog to register a mission will appear",
                                         "A table will appear, displaying all the registered missions", "A dialog to remove a mission will appear",
                                         "A dialog will appear to get the mission id to be developed", "A dialog will appear to get the mission id to have it's log read",
                                         "The program will finish."};

        constraints.gridy = 0;
        getContentPane().add(title, constraints);

        renderMenu(options, toolTips, new MissionButtonHandler());

        setSize(550, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void renderMenu(String[] options, String[] toolTips, MissionButtonHandler mbh) {
        GridBagConstraints constraints = new GridBagConstraints();
        for( int i=0; i<options.length; i++ ){
            JButton jButton = new JButton(options[i]);
            jButton.setPreferredSize(new Dimension(200, 30));
            jButton.addActionListener(mbh);
            jButton.setToolTipText(toolTips[i]);
            constraints.gridy = i+1;
            getContentPane().add(jButton, constraints);
        }
    }

    public void welcomeScreen(){
        System.out.println("Welcome, to the");
        System.out.println("\t\t ___    ___ ");
        System.out.println("\t\t|   \\  /   | _____  _______  __    __  _______  _____     _____  __    __  __  _____  ");
        System.out.println("\t\t| |\\ \\/ /| ||  _  ||__   __||  |  |  ||  _____||  __  \\  /  ___\\|  |  |  ||  ||  __ \\ ");
        System.out.println("\t\t| | \\  / | || | | |   | |   |  |__|  || |___   |  |_|  | \\__  \\ |  |__|  ||  ||  |_| |");
        System.out.println("\t\t| |  \\/  | || | | |   | |   |   __   ||  ___|  |  __  /     \\  \\|   __   ||  ||   __/ ");
        System.out.println("\t\t| |      | || |_| |   | |   |  |  |  || |_____ | |  \\ \\  ___/  /|  |  |  ||  ||  |    ");
        System.out.println("\t\t|_|      |_||_____|   |_|   |__|  |__||_______||_|   \\_\\|____/  |__|  |__||__||__|  \n\n");
    }

    @Override
    public int getUserChoice(){
        System.out.println("========================================");
        System.out.println("============ Missions Panel ============");
        int choice = -1;
        while (choice == -1){
            System.out.println("\n{1} View Missions\n{2} Develop Mission\n{3} Read Mission Log\n{4} Add Mission\n{5} Remove Mission\n{6} Enter Ships Panel\n{7} Quit Mothership");
            int tempoChoice = -1;
            try{
                tempoChoice = scanner.nextInt();
            }catch(InputMismatchException e){
                System.out.println("A mismatch happened while trying to parse your option. Please retry.\n");
                scanner.next();
            }
            choice = tempoChoice;
        }
        return choice;
    }

    public String displayMissions(){
        StringBuilder sb = new StringBuilder("==== Viewing Missions ====\n");
        List<Mission> missions = MainController.getInstance().getMissions();
        missions.forEach(mission -> sb.append("\nMission nº"+mission.getId()
                +"\nDescription: "+mission.getDescription()
                +"\nCommander: "+mission.getSpaceShip().getCommander().getName()
                +"\nSpaceship: "+mission.getSpaceShip().getId()
                +"\nMission status: "+mission.getState().getMessage()+"\n")
        );
//        for (Mission mission : MainController.getInstance().getMissions()){
//            sb.append("\nMission nº"+mission.getId());
//            sb.append("\nDescription: "+mission.getDescription());
//            sb.append("\nCommander: "+mission.getSpaceShip().getCommander().getName());
//            sb.append("\nSpaceship: "+mission.getSpaceShip().getId());
//            sb.append("\nMission status: "+mission.getState().getMessage()+"\n");
//        }
        return sb.toString();
    }

    public MissionContent registerMission(){
        showMessage("======== Mission Register Panel ========");
        showMessage("Type in this mission's brief description");
        String description = scanner.next();
        int shipId = -1, missionId = -1;
        while ( shipId == -1 ) {
            showMessage("Select the id for this mission's ship...");
            showMessage(displayShipIds());
            try{
                shipId = MainController.getInstance().handleShipSelectionForMission(askId());
            }catch(Exception e){
                showMessage(e.getMessage());
            }
        }
        SpaceShip spaceShip = ShipsController.getInstance().getShipById(shipId);
        showMessage("Ship "+shipId+" selected!");
        while( missionId == -1 ){
            showMessage("Type in the new id for this mission...");
            try{
                missionId = MainController.getInstance().handleMissionExistence(askId());
            }catch(Exception e){
                showMessage(e.getMessage());
            }
        }
        showMessage("Spaceship "+spaceShip.getId()+" selected for mission "+missionId);
        spaceShip.setAvailable(false);
        return new MissionContent(description, missionId, spaceShip);
    }

    public void displayRemovePanel() {
        showMessage("======== Mission Removal Panel =========");
        showMessage("Type in the id of the mission to-be removed\n");
    }

    public int askId() {
        return super.askId();
    }

    private String displayShipIds() {
        if(!ShipsController.getInstance().getSpaceShips().isEmpty()){
            StringBuilder sb = new StringBuilder("Available Ids:\n");
            for (SpaceShip spaceShip : ShipsController.getInstance().getSpaceShips()){
                if (spaceShip.isAvailable()) {
                    sb.append(spaceShip.getId()+"\t");
                }
            }
            return sb.toString();
        } else {
            return "There are no ships, thus there are no id's. Register a ship first.";
        }
    }

    public String displayMissionsIds() {
        StringBuilder sb = new StringBuilder("Available Ids: \n");
        if(MainController.getInstance().getMissions().isEmpty()){
            return "There are no missions, thus there are no id's. Register a mission first.";
        } else {
            for (Mission mission : MainController.getInstance().getMissions()){
                if(mission.getState() != MissionState.SUCCESSFULL){
                    sb.append(mission.getId()+"\t");
                }
            }
            return sb.toString();
        }
    }

    public String displayMissionsIdsForLogReading() {
        if(MainController.getInstance().getMissions().isEmpty()){
            return "There are no missions, thus there are no id's. Register a mission first.";
        } else {
            StringBuilder sb = new StringBuilder("Available Ids: \n");
            for (Mission mission : MainController.getInstance().getMissions()){
                sb.append(mission.getId()+"\t");
            }
            return sb.toString();
        }
    }

    class MissionButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()){
                case "Enter Ships Panel":
                    showDialog("ENTRO NOS BARQUINNNNNN KKKK");
                    break;
                case "Add Mission":
                    showDialog("eita nois >.<", "vSFFF");
                    break;
            }

        }
    }
}
