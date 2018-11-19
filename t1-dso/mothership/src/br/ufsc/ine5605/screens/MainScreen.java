package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.controllers.MainController;
import br.ufsc.ine5605.constants.MissionState;
import br.ufsc.ine5605.controllers.ShipsController;
import br.ufsc.ine5605.handler.ButtonHandler;
import br.ufsc.ine5605.models.Mission;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.InputMismatchException;
import java.util.List;

public class MainScreen extends Screen{

    DefaultTableModel defaultTableModel;
    JTable jTable;

    public MainScreen(){
        super("Mothership");
        getContentPane().setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        JLabel title = new JLabel("Welcome. Choose an option.");

        String[] options = new String[]{"Enter Ships Panel","Add Mission",
                                        "Remove Mission", "Develop Mission",
                                        "Read Mission Log"};

        String[] toolTips = new String[]{"Will redirect to the Ships Screen", "A dialog to register a mission will appear", "A dialog to remove a mission will appear",
                                         "A dialog will appear to get the mission id to be developed", "A dialog will appear to get the mission id to have it's log read"};

        constraints.gridy = 0;
        getContentPane().add(title, constraints);

        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Description");
        defaultTableModel.addColumn("SpaceShip ID");
        defaultTableModel.addColumn("State");
        defaultTableModel.addColumn("Completed");

        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(550, 350));
        jTable.setModel(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setPreferredSize(new Dimension(550, 200));

        constraints.gridy = 2;
        getContentPane().add(jScrollPane, constraints);

        renderMenu(options, toolTips, new MissionButtonHandler());

        setSize(600, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    public void displayRemovePanel() {
        showMessage("======== Mission Removal Panel =========");
        showMessage("Type in the id of the mission to-be removed\n");
    }

    public int askId() {
        return super.askId();
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

    public void sync(Mission mission) {
        defaultTableModel.addRow(new Object[]{mission.getId(),
                mission.getDescription(),
                mission.getSpaceShip().getId(),
                mission.getState(),
                mission.isCompleted()});
    }

    public void refresh(int id) {
        for( int i=0; i<defaultTableModel.getRowCount(); i++ ){
            if( defaultTableModel.getValueAt(i, 0).equals(id) ){
                defaultTableModel.removeRow(i);
            }
        }
    }

    public void refreshTable() {
        defaultTableModel.setRowCount(0);
        for( Mission m : MainController.getInstance().getMissions() ){
             sync(m);
        }
    }

    class MissionButtonHandler extends ButtonHandler {
        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()){
                case "Enter Ships Panel":
                    ShipsController.getInstance().openShipsPanel();
                    setVisible(false);
                    break;
                case "Add Mission":
                    if( ShipsController.getInstance().hasAvailableShip() ){
                        MainController.getInstance().handleRegisterMission();
                    } else {
                        showDialog("All spaceships are taken! \nPlease, register a new one in order to create a new mission.");
                    }
                    break;
                case "Remove Mission":
                    if( MainController.getInstance().hasMissionsToRemove() ){
                        MainController.getInstance().handleGetIdToRemove();
                    } else {
                        showDialog("Currently, you have no missions registered or you have just Successful missions. \nPlease add a new one first to be able to remove it!");
                    }
                    break;
                case "Develop Mission":
                    if( MainController.getInstance().hasMissionsToDevelop() ){
                        MainController.getInstance().handleGetIdToDevelop();
                    } else {
                        showDialog("Currently, you have no missions available for development. \nPlease register a new one.");
                    }
                    break;
                case "Read Mission Log":
                    if( MainController.getInstance().hasMissionsToReadLog() ){
                        MainController.getInstance().handleGetIdToReadLog();
                    } else {
                        showDialog("Currently, you either no missions registered or un-developed missions.\nRegister a new mission, or a develop one!");
                    }
                    break;
            }

        }
    }
}