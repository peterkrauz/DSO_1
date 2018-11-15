package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.exceptions.ShipUnavailableException;
import br.ufsc.ine5605.exceptions.UnexistantShipException;
import br.ufsc.ine5605.models.ShipContent;
import br.ufsc.ine5605.models.SpaceShip;
import br.ufsc.ine5605.persistence.ShipsMapper;
import br.ufsc.ine5605.screens.ShipsScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShipsController implements Controller {

    private static ShipsController instance;

    private ShipsMapper mapper;
    private ShipsScreen shipsScreen;
    private boolean isRunning;

    private ShipsController() {
        configure();
    }

    public static ShipsController getInstance(){
        if( instance == null ){
            instance = new ShipsController();
        }
        return instance;
    }

    @Override
    public void configure(){
        shipsScreen = new ShipsScreen();
        mapper = new ShipsMapper();
    }

    @Override
    public void executeUserChoice(int choice) {
        switch (choice) {
            case 1:
                displayFleet();
                break;
            case 2:
                addShipToFleet(unWrap(shipsScreen.registerSpaceShip()));
                break;
            case 3:
                handleShipRemoval();
                break;
            case 4:
            //    handleShipUpdate();
                break;
            case 5:
                shipsScreen.showMessage("Exiting the Ships's Panel...\n");
                isRunning = false;
                break;
            default:
                shipsScreen.showMessage("This option does not exist. Please retry.\n");
                break;
        }

    }

    private void handleShipUpdate() {
        if(mapper.isEmpty()){
            shipsScreen.showMessage("Currently, your fleet of spaceships is empty. Please register atleast one to be able to update it.");
        } else {
            try{
                shipsScreen.displayUpdatePanel();
                shipsScreen.showMessage(shipsScreen.displayShipsIds());
                updateShipById(shipsScreen.askId());
            }catch (UnexistantShipException e){
                shipsScreen.showMessage(e.getMessage());
            }
        }
    }

    private void updateShipById(int id) throws UnexistantShipException{
        if( getShipById(id) == null ){
            throw new UnexistantShipException("There is no ship with id "+id);
        }else{
            SpaceShip oldShip = getShipById(id);
            SpaceShip updatedShip = unWrap(shipsScreen.updateShip());
            update(oldShip, updatedShip);
            shipsScreen.showMessage("Ship "+id+" updated");
        }
    }

    private void update(SpaceShip oldShip, SpaceShip updatedShip) {
      mapper.put(updatedShip);
      // mapper.set(mapper.indexOf(oldShip), updatedShip);
    }

    private void handleShipRemoval() {
        if(mapper.isEmpty()){
            shipsScreen.showMessage("Currently, your fleet of spaceships is empty. Please register at least one to be able to remove it.");
        } else {
            boolean removed = false;
            while (!removed){
                try{
                    shipsScreen.displayRemovePanel();
                    shipsScreen.showMessage(shipsScreen.displayShipsIds());
                    removeShip(shipsScreen.askId());
                    removed = true;
                }catch (Exception e){
                    shipsScreen.showMessage(e.getMessage());
                }
            }
        }
    }

    public void removeShip(int id) throws UnexistantShipException, ShipUnavailableException {
        if( getShipById(id) == null ){
            throw new UnexistantShipException("There is no ship with id "+id);
        } else {
            if( !getShipById(id).isAvailable() ){
                throw new ShipUnavailableException("This ship is assigned to a mission, thus it cannot be removed.");
            } else {
                mapper.delete(getShipById(id));
                shipsScreen.showMessage("Ship "+id+" removed!");
            }
        }
    }

    private void displayFleet() {
        if(mapper.isEmpty()){
            shipsScreen.showMessage("Currently, your fleet of spaceships is empty. Please register one to be able to see it.\n");
        } else {
            shipsScreen.displayFleet();
        }
    }

    private boolean harborsShip(SpaceShip spaceShip){
        return mapper.contains(spaceShip);
    }

    private void addShipToFleet(SpaceShip spaceShip) {
        if(!harborsShip(spaceShip) && spaceShip != null){
            mapper.put(spaceShip);
        }
    }

    private SpaceShip unWrap(ShipContent content) {
        return new SpaceShip(content);
    }

    public SpaceShip getShipById(int id){
        return mapper.get(id);
    }

    @Override
    public void run(){
        isRunning = true;

        while(isRunning){
            int choice = shipsScreen.getUserChoice();
            executeUserChoice(choice);
        }
    }

    public boolean hasShipWithId(int id){
      return mapper.contains(id);
    }

    public List<SpaceShip> getSpaceShips(){
        return mapper.getSpaceships();
    }

    public void setSpaceShips(List<SpaceShip> spaceShips) {
        this.mapper = (ShipsMapper) spaceShips;
    }

    public boolean hasAvailableShip() {
       for (SpaceShip spaceship : mapper.getSpaceships()) {
            if (spaceship.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    // test purposes
    public void addShipForTest(SpaceShip spaceShip) {
        mapper.put(spaceShip);
    }
}
