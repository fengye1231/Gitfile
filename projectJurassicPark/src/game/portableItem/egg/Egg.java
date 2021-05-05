package game.portableItem.egg;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.portableItem.FoodCapability;
import game.portableItem.Purchasable;
import game.dinosaur.Dinosaur;
import game.portableItem.PortableItem;

public abstract class Egg extends PortableItem implements Purchasable {
    private int hatchTime;
    // assume allosaur eats their own eggs
    private int beEatenFoodLevel = 10;

    public Egg(String name, char displayChar, int hatchTime) {
        super(name, displayChar);
        this.hatchTime = hatchTime;
        this.addCapability(FoodCapability.FEEDALLOSAUR);
        this.addCapability(FoodCapability.ALLOSAUREAT);
    }

    @Override
    public void tick(Location currentLocation) {
        if (hatchTime == 0) {
            currentLocation.removeItem(this);
            hatch(currentLocation);
        }
        hatchTime -= 1;
    }

    // since the eggs can be hatch in player's inventory, does nothing
    @Override
    public void tick(Location currentLocation, Actor actor) { }

    public void feed(Actor player, Dinosaur newDinosaur){
        player.removeItemFromInventory(this);
        newDinosaur.increaseFoodLevel(beEatenFoodLevel);
    }

    public void beEaten(Location location, Dinosaur newDinosaur) {
        location.removeItem(this);
        newDinosaur.increaseFoodLevel(beEatenFoodLevel);
    }

    // hatch location : should it be the same as where the egg locates?
    // should player put down the egg?

    public abstract void hatch(Location location);


    public int getHatchTime() {
        return hatchTime;
    }
}
