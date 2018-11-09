package br.ufsc.ine5605.controllers;

import br.ufsc.ine5605.exceptions.ShipUnavailableException;
import br.ufsc.ine5605.exceptions.UnexistantShipException;
import br.ufsc.ine5605.models.ShipContent;
import br.ufsc.ine5605.models.SpaceShip;
import br.ufsc.ine5605.screens.ShipsScreen;

import java.util.ArrayList;
import java.util.List;

public class ShipsController implements Controller {

    private static ShipsController instance;

    private List<SpaceShip> spaceShips;
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
        spaceShips = new ArrayList<>();
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
                handleShipUpdate();
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
        if(spaceShips.isEmpty()){
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
        spaceShips.set(spaceShips.indexOf(oldShip), updatedShip);
    }

    private void handleShipRemoval() {
        if(spaceShips.isEmpty()){
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
                spaceShips.remove(getShipById(id));
                shipsScreen.showMessage("Ship "+id+" removed!");
            }
        }
    }

    private void displayFleet() {
        if(spaceShips.isEmpty()){
            shipsScreen.showMessage("Currently, your fleet of spaceships is empty. Please register one to be able to see it.\n");
        } else {
            shipsScreen.displayFleet();
        }
    }

    private boolean harborsShip(SpaceShip spaceShip){
        return spaceShips.contains(spaceShip);
    }

    private void addShipToFleet(SpaceShip spaceShip) {
        if(!harborsShip(spaceShip) && spaceShip != null){
            spaceShips.add(spaceShip);
        }
    }

    private SpaceShip unWrap(ShipContent content) {
        return new SpaceShip(content);
    }

    public SpaceShip getShipById(int id){
        for (SpaceShip spaceShip : spaceShips){
            if(spaceShip.getId() == id){
                return spaceShip;
            }
        }
        return null;
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
        for (SpaceShip spaceShip : spaceShips){
            if (spaceShip.getId() == id){
                return true;
            }
        }
        return false;
    }

    public List<SpaceShip> getSpaceShips(){
        return spaceShips;
    }

    public void setSpaceShips(List<SpaceShip> spaceShips) {
        this.spaceShips = spaceShips;
    }

    public boolean hasAvailableShip() {
        for (SpaceShip spaceShip : spaceShips){
            if(spaceShip.isAvailable()){
                return true;
            }
        }
        return false;
    }
}
