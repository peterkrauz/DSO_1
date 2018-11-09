package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.constants.MissionState;
import br.ufsc.ine5605.exceptions.*;
import br.ufsc.ine5605.models.Mission;
import br.ufsc.ine5605.models.MissionContent;
import br.ufsc.ine5605.models.SpaceShip;
import br.ufsc.ine5605.screens.MainScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainController implements Controller{

    private ShipsController shipsController;
    private MainScreen mainScreen;
    private List<Mission> missions;
    private boolean isRunning;


    public MainController(){
        configure();
    }

    @Override
    public void run(){
        mainScreen.welcomeScreen();
        isRunning = true;

        while(isRunning){
            int choice = mainScreen.getUserChoice();
            executeUserChoice(choice);
        }
    }

    @Override
    public void configure() {
        mainScreen = new MainScreen(this);
        missions = new ArrayList<>();
        shipsController = new ShipsController();
    }

    @Override
    public void executeUserChoice(int choice) {
        switch (choice){
            case 1:
                displayMissions();
                break;
            case 2:
                handleMissionDevelopment();
                break;
            case 3:
                handleMissionLogReading();
                break;
            case 4:
                handleAddMission();
                break;
            case 5:
                handleRemoveMission();
                break;
            case 6:
                shipsController.run();
                break;
            case 7:
                mainScreen.showMessage("Finishing the MotherShip. Thank you for playing!");
                isRunning = false;
                break;
            default:
                mainScreen.showMessage("Unexistant option. Please retry.\n");
                break;
        }
    }

    private void handleMissionLogReading() {
        if(missions.isEmpty()){
            mainScreen.showMessage("Currently, you have no missions. Register one and start it to be able to read its log.");
        } else {
            mainScreen.showMessage("== Choose a mission by id to read its log ==");
            mainScreen.showMessage(mainScreen.displayMissionsIdsForLogReading());
            int id = mainScreen.askId();
            try{
                id = getMissionIdForLogReading(id);
                if( getMissionById(id).getLog().equals("") ){
                    mainScreen.showMessage("Whelp! This mission hasn't even been started! Develop it first so the crew can write it's log!");
                } else {
                    mainScreen.showMessage("Mission nº"+id+" Log: \n"+getMissionById(id).getLog());
                }
            }catch(Exception e){
                mainScreen.showMessage(e.getMessage());
            }
        }
    }

    private void handleMissionDevelopment() {
        if (missions.isEmpty()){
            mainScreen.showMessage("Currently, you have no missions. Register at least one to be able to develop it.");
        } else {
            mainScreen.showMessage("=== Choose by id a mission to develop. ===");
            mainScreen.showMessage(displayMissionsIds());
            int id = mainScreen.askId();

            try{
                id = getMissionIdForDevelopment(id);
                Mission mission = getMissionById(id);
                developMission(mission);
            }catch(Exception e){
                mainScreen.showMessage(e.getMessage());
            }
        }
    }

    private void developMission(Mission mission) {
        Random random = new Random();
        int event = random.nextInt(10);
        if(event < 2){
            mission.setState(MissionState.FAILURE);
            mainScreen.showMessage("A tragic event befell our loyal spaceship. Their scattered remains have been found far away from our closest star.\n Mission "+mission.getId()+" will no longer exist.");
            SpaceShip spaceShip = mission.getSpaceShip();
            getShipsController().getSpaceShips().remove(spaceShip);
            missions.remove(mission);
        } else if(event > 1 && event <= 7){
            mission.setState(MissionState.ONGOING);
            mainScreen.showMessage("Status quo. Nothing good or bad happened to our crew. The spaceship "+mission.getId()+" remains on duty!");
            mission.writeLog("Status quo. Spaceship "+mission.getId()+" remains on mission.\n");
        } else {
            mission.setState(MissionState.SUCCESSFULL);
            mainScreen.showMessage("Hooray! The Great Emperor will be utmost pleased! Spaceship "+mission.getId()+" was successful on their mission!");
            mission.writeLog("Success! The crew and captain of spaceship "+mission.getId()+" have returned in glory! Mission ended in success.");
            mission.setCompleted(true);
        }
    }

    private String displayMissionsIds() {
        return mainScreen.displayMissionsIds();
    }

    public Mission getMissionById(int id){
        for (Mission mission : missions){
            if( mission.getId() == id){
                return mission;
            }
        }
        return null;
    }

    private void handleRemoveMission() {
        if (missions.isEmpty()){
            mainScreen.showMessage("Currently, you have no missions. Register at least one to be able to remove it.");
        } else {
            try{
                mainScreen.displayRemovePanel();
                mainScreen.showMessage(mainScreen.displayMissionsIds());
                removeMission(mainScreen.askId());
            }catch (UnexistantMissionException e){
                mainScreen.showMessage(e.getMessage());
            }
        }
    }

    private void removeMission(int id) throws UnexistantMissionException {
        if( getMissionById(id) == null ){
            throw new UnexistantMissionException("There is no mission with the id "+id);
        } else {
            Mission mission = getMissionById(id);
            mission.getSpaceShip().setAvailable(true);
            missions.remove(mission);
        }
    }

    private void handleAddMission() {
        if (getShipsController().getSpaceShips().isEmpty()){
            mainScreen.showMessage("You need at least one ship in your fleet to be able to start a mission.\n");
        } else {
            if( getShipsController().hasAvailableShip() ){
                addMission(unWrap(mainScreen.registerMission()));
            } else {
                mainScreen.showMessage("All spaceships are already selected! Please register another to assign to a new mission.\n");
            }
        }
    }

    private Mission unWrap(MissionContent content) {
        return new Mission(content);
    }

    private void addMission(Mission mission) {
        if(!missions.contains(mission) && mission != null){
            missions.add(mission);
        }
    }

    private void displayMissions() {
        if(missions.isEmpty()){
            mainScreen.showMessage("You have no missions registered. Register one to be able to see it.");
        } else {
            mainScreen.showMessage(mainScreen.displayMissions());
        }
    }

    public ShipsController getShipsController() {
        return shipsController;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public int handleShipSelectionForMission(int shipId) throws UnexistantShipException, ShipUnavailableException{
        if( getShipsController().hasShipWithId(shipId) ){
            if ( getShipsController().getShipById(shipId).isAvailable() ){
                return shipId;
            } else {
                throw new ShipUnavailableException("Ship "+shipId+" is unavailable. Please retry.");
            }
        } else {
            throw new UnexistantShipException("There is no ship with id "+shipId+". Please retry.");
        }
    }

    public int handleMissionExistence(int missionId) throws DuplicateMissionException {
        if( getMissionById(missionId) == null ){
            return missionId;
        } else {
            throw new DuplicateMissionException("There is already a mission with id "+missionId+". Please retry.");
        }
    }
    
    public int getMissionIdForLogReading(int id) throws UnexistantMissionException{
        if( getMissionById(id) == null ){
            throw new UnexistantMissionException("There is no mission with id "+id+". Please retry.");
        } else {
            return id;
        }
    }

    public int getMissionIdForDevelopment(int id) throws MissionCompletedException, UnexistantMissionException {
        if( getMissionById(id) == null ){
            throw new UnexistantMissionException("There is no mission with id "+id+". Please retry.");
        } else {
            if( getMissionById(id).isCompleted()){
                throw new MissionCompletedException("Mission "+id+" is already completed! It cannot be developed any further.");
            } else {
                return id;
            }
        }
    }
    
}
