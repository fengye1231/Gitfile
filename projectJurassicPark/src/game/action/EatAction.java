package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import game.portableItem.Fruit;

import game.dinosaur.*;


public class EatAction  {
    protected Location currentLocation;
    protected Fruit fruit;
    protected Dinosaur dinosaur;

    public EatAction(Location currentLocation,Fruit fruit,Dinosaur dinosaur) {
        this.currentLocation = currentLocation;
        this.fruit=fruit;
        this.dinosaur=dinosaur;
    }

    public String execute(Location currentLocation,Fruit fruit,Dinosaur dinosaur) {

        String result = dinosaur + "eat one fruit";
//        newDinosaur.heal(10);
        fruit.beEaten(currentLocation, dinosaur);
        return result;
    }


    public String menuDescription(Dinosaur dinosaur) {
        return dinosaur + "eat one fruit";
    }


}
