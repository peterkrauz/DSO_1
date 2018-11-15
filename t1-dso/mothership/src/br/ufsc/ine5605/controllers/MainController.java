package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.constants.MissionState;
import br.ufsc.ine5605.exceptions.*;
import br.ufsc.ine5605.models.*;
import br.ufsc.ine5605.persistence.MissionMapper;
import br.ufsc.ine5605.screens.MainScreen;

import java.util.List;
import java.util.Random;

public class MainController implements Controller{

    public static MainController instance;

    private MainScreen mainScreen;
    private MissionMapper mapper;
    private boolean isRunning;


    private MainController(){
        configure();
        ShipsController.getInstance().addShipForTest(new SpaceShip(new ShipContent(new Alien("Test", "Testers"), 46)));
    }

    public static MainController getInstance() {
        if( instance == null ){
            instance = new MainController();
        }
        return instance;
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
        mainScreen = new MainScreen();
        mapper = new MissionMapper();
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
                handleRegisterMission();
                break;
            case 5:
                handleRemoveMission();
                break;
            case 6:
                ShipsController.getInstance().run();
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
        if(mapper.isEmpty()){
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
        if (mapper.isEmpty()){
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
        if(event <= 1){
            mission.setState(MissionState.FAILURE);
            mainScreen.showMessage("A tragic event befell our loyal spaceship. Their scattered remains have been found far away from our closest star.\n Mission "+mission.getId()+" will no longer exist.");
            SpaceShip spaceShip = mission.getSpaceShip();
            ShipsController.getInstance().getSpaceShips().remove(spaceShip);
            mapper.delete(mission);
        } else if(event >= 2 && event <= 7){
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

    // TODO
    public Mission getMissionById(int id){
        return mapper.get(id);
    }

    private void handleRemoveMission() {
        if (mapper.isEmpty()){
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
            mapper.delete(mission);
        }
    }

    public void handleRegisterMission() {
        if (ShipsController.getInstance().getSpaceShips().isEmpty()){
            mainScreen.showDialog("You need at least one ship in your fleet to be able to start a mission.\n");
        } else {
            if( ShipsController.getInstance().hasAvailableShip() ){
                RegisterController.getInstance().showMissionRegisterScreen();
            } else {
                mainScreen.showDialog("All spaceships are already selected! Please register another to assign to a new mission.\n");
            }
        }
    }

    private Mission unWrap(MissionContent content) {
        return new Mission(content);
    }

    public void addMission(MissionContent missionContent){
        Mission mission = unWrap(missionContent);

        mission.setSpaceShip(ShipsController.getInstance().getShipById(missionContent.spaceShipId));
        mission.getSpaceShip().setId(missionContent.spaceShipId);
        mission.getSpaceShip().setAvailable(false);

        try{
            if(!mapper.contains(mission) && mission != null){
                mapper.put(mission);
            }
        }catch(DuplicateMissionException e){
            mainScreen.showMessage(e.getMessage());
        }

        mainScreen.sync(mission);
    }

    public void addMission(Mission mission) {
        try{
            if(!mapper.contains(mission) && mission != null){
                mapper.put(mission);
            }
        }catch(DuplicateMissionException e){
            mainScreen.showMessage(e.getMessage());
        }
    }

    private void displayMissions() {
        if(mapper.isEmpty()){
            mainScreen.showMessage("You have no missions registered. Register one to be able to see it.");
        } else {
            mainScreen.showMessage(mainScreen.displayMissions());
        }
    }

    public List<Mission> getMissions() {
        return mapper.getMissions();
    }

    public int handleShipSelectionForMission(int shipId) throws UnexistantShipException, ShipUnavailableException{
        if( ShipsController.getInstance().hasShipWithId(shipId) ){
            if ( ShipsController.getInstance().getShipById(shipId).isAvailable() ){
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

    public boolean idIsNotTaken(int i) {
        return !ShipsController.getInstance().hasShipWithId(i);
    }

    public boolean idExistsInFleet(int i) {
        return ShipsController.getInstance().getShipById(i) != null;
    }

    public void handleGetIdToRemove() {
        RegisterController.getInstance().showScreenForMissionRemoval();
    }
}
