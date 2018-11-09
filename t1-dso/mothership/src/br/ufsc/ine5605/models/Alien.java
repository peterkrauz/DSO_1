package br.ufsc.ine5605.models;

import java.io.Serializable;

public class Alien implements Serializable {
    private String name;
    private String clan;

    public Alien(){

    }

    public Alien(String name, String clan) {
        this.name = name;
        this.clan = clan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }
}
