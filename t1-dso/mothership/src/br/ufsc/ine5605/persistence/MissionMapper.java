package br.ufsc.ine5605.persistence;

import br.ufsc.ine5605.exceptions.DuplicateMissionException;
import br.ufsc.ine5605.models.Mission;
import br.ufsc.ine5605.screens.MainScreen;
import sun.applet.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MissionMapper {

    private long serialVersionUID = 1L;

    public final String FILENAME = "missions.mship";
    private HashMap<Integer, Mission> missions;

    public MissionMapper(){
        missions = new HashMap<>();
        load();
    }

    public void put(Mission mission) throws DuplicateMissionException {
        if(missions.containsKey(mission.getId())){
            throw new DuplicateMissionException("There is already a mission with id "+mission.getId());
        }
        missions.put(mission.getId(), mission);
        save();
    }

    public Mission get(Integer key){
        return missions.get(key);
    }

    public boolean contains(Mission mission){
        return missions.containsValue(mission);
    }

    public boolean contains(Integer key){
        return missions.containsKey(key);
    }

    public List<Mission> getMissions(){
        return new ArrayList<>(missions.values());
    }

    public boolean isEmpty(){
        return missions.isEmpty();
    }

    private void save() {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(missions);

            objectOutputStream.flush();
            fileOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();

        } catch(Exception e){
            e.getMessage();
        }
    }

    private void load(){
        try{
            FileInputStream fileInputStream = new FileInputStream(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            this.missions = (HashMap<Integer, Mission>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Mission mission) {
        if( contains(mission) && mission != null){
            missions.remove(mission.getId(), mission);
        }
        save();
    }

}

