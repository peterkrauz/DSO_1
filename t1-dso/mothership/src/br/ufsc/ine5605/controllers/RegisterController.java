package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.models.MissionContent;
import br.ufsc.ine5605.screens.RegisterMissionScreen;

public class RegisterController {

    private RegisterMissionScreen registerMissionScreen;
    private static RegisterController instance;

    private MissionContent missionContent;

    public static RegisterController getInstance(){
        if( instance == null ){
            instance = new RegisterController();
        }
        return instance;
    }

    private RegisterController(){
        registerMissionScreen = new RegisterMissionScreen();
    }

    public void showMissionRegisterScreen() {
        registerMissionScreen.showMissionRegisterScreen();
    }

    public void setMissionContent(MissionContent missionContent){
        this.missionContent = missionContent;
        MainController.getInstance().addMission(missionContent);
    }

    public void showScreenForMissionRemoval() {
        registerMissionScreen.showScreenForMissionRemoval();
    }
}
