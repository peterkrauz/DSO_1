package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.models.MissionContent;
import br.ufsc.ine5605.screens.DevelopMissionScreen;
import br.ufsc.ine5605.screens.LogReadScreen;
import br.ufsc.ine5605.screens.RegisterMissionScreen;
import br.ufsc.ine5605.screens.RemoveMissionScreen;

public class SwingScreensController {

    private RegisterMissionScreen registerMissionScreen;
    private RemoveMissionScreen removeMissionScreen;
    private DevelopMissionScreen developMissionScreen;
    private LogReadScreen logReadScreen;

    private static SwingScreensController instance;

    private MissionContent missionContent;

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
    }

    public void setMissionContent(MissionContent missionContent){
        this.missionContent = missionContent;
        MainController.getInstance().addMission(missionContent);
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
}
