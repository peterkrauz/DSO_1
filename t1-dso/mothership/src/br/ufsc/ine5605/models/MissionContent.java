package br.ufsc.ine5605.models;

public class MissionContent extends Content{

    public String description;
    public int id;
    public SpaceShip spaceShip;
    public int spaceShipId;

    public MissionContent(){}

    public MissionContent(String description, int id, SpaceShip spaceShip){
        this.description = description;
        this.id = id;
        this.spaceShip = spaceShip;
    }

}
