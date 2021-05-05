package game.portableItem;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

public class Corpse extends PortableItem {
    private int rotAwayTime;
    // if allosaur then 20, Brachiosaur then 40
    private int increasedFoodLevel;
    // brachiosaur : 100; others : 50

    // assumed corpse can not be picked up
    public Corpse(int newRotAwayTime, int newIncreasedFoodLevel) {
        super("corpse", '%');
        rotAwayTime = newRotAwayTime;
        increasedFoodLevel = newIncreasedFoodLevel;
        this.addCapability(FoodCapability.ALLOSAUREAT);
    }

    @Override
    public void tick(Location currentLocation) {
        if (rotAwayTime == 0) {
            currentLocation.removeItem(this);
        }
        rotAwayTime -= 1;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) { }

    public void beEaten(Location location, Dinosaur newDinosaur) {
        location.removeItem(this);
        newDinosaur.increaseFoodLevel(increasedFoodLevel);
    }
}
