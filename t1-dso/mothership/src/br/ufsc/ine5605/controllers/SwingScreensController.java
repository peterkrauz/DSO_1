package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.models.MissionContent;
import br.ufsc.ine5605.models.ShipContent;
import br.ufsc.ine5605.screens.*;

public class SwingScreensController {

    private RegisterMissionScreen registerMissionScreen;
    private RemoveMissionScreen removeMissionScreen;
    private DevelopMissionScreen developMissionScreen;
    private LogReadScreen logReadScreen;

    private RegisterSpaceShipScreen registerSpaceShipScreen;

    private static SwingScreensController instance;

    private MissionContent missionContent;
    private ShipContent shipContent;

    public static SwingScreensController getInstance(){
        if( instance == null ){
            instance = new SwingScreensController();
        }
        return instance;
    }

    private SwingScreensController(){
        registerMissionScreen = new RegisterMissionScreen();
        removeMissionScreen = new RemoveMissionScreen();
        developMissionScreen = new DevelopMissionScreen();
        logReadScreen = new LogReadScreen();

        registerSpaceShipScreen = new RegisterSpaceShipScreen();
    }

    public void setMissionContent(MissionContent missionContent){
        this.missionContent = missionContent;
        MainController.getInstance().addMission(missionContent);
    }

    public void setShipContent(ShipContent shipContent){
        this.shipContent = shipContent;
        //ShipsController.getInstance().addShip(shipContent);
    }

    public void showMissionRegisterScreen() {
        registerMissionScreen.showMissionRegisterScreen();
    }

    public void showScreenForMissionRemoval() {
        removeMissionScreen.showMissionRemovalScreen();
    }

    public void showScreenForMissionDevelopment() {
        developMissionScreen.showMissionDevelopmentScreen();
    }

    public void showScreenForMissionLogReading() {
        logReadScreen.showMissionLogReadingScreen();
    }

    public void showSpaceShipRegisterScreen() {
        registerSpaceShipScreen.showSpaceShipRegisterScreen();
    }
}
