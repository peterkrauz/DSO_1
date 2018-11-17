package br.ufsc.ine5605.models;

import br.ufsc.ine5605.constants.MissionState;

import java.io.Serializable;

public class Mission implements Serializable {
    private String description;
    private String log;
    private boolean isCompleted;
    private int id;
    private SpaceShip spaceShip;
    private int spaceShipId;
    private MissionState state;

    public Mission(){

    }

    public Mission(MissionContent content){
        this.description = content.description;
        this.id = content.id;
        this.spaceShip = content.spaceShip;
        this.spaceShipId = content.spaceShipId;
        this.log = "";
        this.state = MissionState.STARTED;
        this.isCompleted = false;
    }

    public int getSpaceShipId() {
        return spaceShipId;
    }

    public void setSpaceShipId(int spaceShipId) {
        this.spaceShipId = spaceShipId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeLog(String log){
        this.log += log;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    public void setSpaceShip(SpaceShip spaceShip) {
        this.spaceShip = spaceShip;
    }

    public MissionState getState() {
        return state;
    }

    public void setState(MissionState state) {
        this.state = state;
    }
}
