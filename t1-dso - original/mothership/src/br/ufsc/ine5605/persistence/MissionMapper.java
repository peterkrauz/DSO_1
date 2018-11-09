package br.ufsc.ine5605.persistence;

import br.ufsc.ine5605.models.Mission;

import java.util.HashMap;

public class MissionMapper {

    public final String FILENAME = "missions.mship";
    private HashMap<Integer, Mission> missions;

    public MissionMapper(){
        this.missions = new HashMap<>();
    }

    public void put(Mission mission){
        missions.put(mission.getId(), mission);
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

    

}

