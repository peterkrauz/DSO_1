package br.ufsc.ine5605.models;

import java.util.ArrayList;
import java.util.List;

public class ShipContent extends Content{

    public Alien commander;
    public List<Alien> crew;
    public int id;

    public ShipContent(){
    }

    public ShipContent(Alien commander, int id){
        this.crew = new ArrayList<>();
        this.id = id;
    }

    public ShipContent(SpaceShip spaceShip) {
        this.commander = spaceShip.getCommander();
        this.crew = spaceShip.getCrew();
        this.id = spaceShip.getId();
    }

    public void addAlienToCrew(Alien alien){
        if(!crew.contains(alien) && alien != null){
            crew.add(alien);
        }
    }

}
