package br.ufsc.ine5605.persistence;

import br.ufsc.ine5605.models.SpaceShip;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShipsMapper {

    private long serialVersionUID = 1L;

    private final String FILENAME = "ships.mship";

    private HashMap<Integer, SpaceShip> spaceships;

    public ShipsMapper() {
        spaceships = new HashMap<>();
        load();
    }

    public void put(SpaceShip spaceship) {
        spaceships.put(spaceship.getId(), spaceship);
        save();

    }

    public SpaceShip get(Integer key){
        return spaceships.get(key);
    }

    public boolean contains(SpaceShip spaceship) {
        return spaceships.containsValue(spaceship);
    }

    public boolean contains(Integer key) {
        return spaceships.containsKey(key);
    }

    public List<SpaceShip> getSpaceships() {
        return new ArrayList<>(spaceships.values());
    }

    public boolean isEmpty() {
        return spaceships.isEmpty();
    }


    public void load() {
        try {
            FileInputStream fin = new FileInputStream(FILENAME);
            ObjectInputStream oi = new ObjectInputStream(fin);

            this.spaceships = (HashMap<Integer, SpaceShip>) oi.readObject();

            oi.close();
            fin.close();
            oi = null;
            fin = null;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch(IOException ex) {
            System.out.println(ex);
        }

    }

    public void save() {
        try {
            FileOutputStream fout = new FileOutputStream(FILENAME);

            ObjectOutputStream oo = new ObjectOutputStream(fout);

            oo.writeObject(spaceships);

            oo.flush();
            fout.flush();

            oo.close();
            fout.close();

            oo = null;
            fout = null;

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);

        }

    }

    public void delete(SpaceShip spaceship) {
        if(contains(spaceship) && spaceship != null) {
            spaceships.remove(spaceship.getId(), spaceship);
        }
        save();
    }



}
