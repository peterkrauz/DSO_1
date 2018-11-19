package br.ufsc.ine5605.screens;

import br.ufsc.ine5605.models.Alien;
import br.ufsc.ine5605.models.ShipContent;

import java.awt.*;
import java.util.ArrayList;

public class RegisterSpaceShipScreen extends Screen {

    private int crewSize;

    private ShipContent content;

    private CrewSizeDialogScreen crewSizeDialogScreen;
    private AlienRegisterScreen alienRegisterScreen;

    public RegisterSpaceShipScreen(){
        content = new ShipContent();
        content.crew = new ArrayList<>();
        crewSizeDialogScreen = new CrewSizeDialogScreen(this);
        alienRegisterScreen = new AlienRegisterScreen(this);
    }

    public void showSpaceShipRegisterScreen() {
        crewSizeDialogScreen.showCrewSizeDialog();
    }

    public void setCrewSize(int size){
        this.crewSize = size;
        registerCrew();
    }

    private void registerCrew() {
        for( int i=0; i<crewSize; i++ ){
            showDialog("porra");
            alienRegisterScreen.registerAlien(false);
        }
    }

    public void addCommander(Alien commander){
        content.commander = commander;
    }

    public void addAlienToCrew(Alien alien){
        content.crew.add(alien);
    }

    public void setContentId(int id){
        content.id = id;
    }

}
