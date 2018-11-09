package br.ufsc.ine5605.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip implements Serializable {
    private Alien commander;
    private List<Alien> crew;
    private boolean isAvailable;
    private int id;

    public SpaceShip(){

    }

    public SpaceShip(ShipContent content){
        this.commander = content.commander;
        this.crew = content.crew;
        this.id = content.id;
        this.isAvailable = true;
    }

    public SpaceShip(Alien commander, int id){
        this.commander = commander;
        this.id = id;
        this.crew = new ArrayList<>();
        this.isAvailable = true;
    }

    public void addAlienToCrew(Alien alien){
        if(!crew.contains(alien) && alien != null){
            crew.add(alien);
        }
    }

    public Alien getCommander() {
        return commander;
    }

    public void setCommander(Alien commander) {
        this.commander = commander;
    }

    public List<Alien> getCrew() {
        return crew;
    }

    public void setCrew(List<Alien> crew) {
        this.crew = crew;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
