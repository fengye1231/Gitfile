package game.portableItem;

import edu.monash.fit2099.engine.*;
import game.EcoPoint;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;


public class Fruit extends PortableItem implements Purchasable {
    private int rotAwayTime = 15;
    // is a constant
    private int ecoCost = 30;
    private int beFedFoodLevel = 20;
    private int beEatenByStegosaur = 10;
    private int beEatenByBrachiosaur = 5;
    // remember -> capabilities of being food of different dinosaurs

    public Fruit() {
        super("Fruit", '^');
        this.addCapability(FoodCapability.FEEDSTEGOSAUR);
        this.addCapability(FoodCapability.FEEDBRACHIOSAUR);
        this.addCapability(FoodCapability.BRACHIOSAUREAT);
        this.addCapability(FoodCapability.STEGOSAUREAT);
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

    public void feed(Actor player, Dinosaur newDinosaur){
        player.removeItemFromInventory(this);
        newDinosaur.increaseFoodLevel(beFedFoodLevel);
        EcoPoint.setEcoPoint(10);
    }

    public void beEaten(Location location, Dinosaur newDinosaur) {
        location.removeItem(this);
        if (newDinosaur instanceof Stegosaur) {
            newDinosaur.increaseFoodLevel(beEatenByStegosaur);
        }
        else if (newDinosaur instanceof Brachiosaur) {
            // remove all the fruits from trees
            // this should be down for the actions part of dinosaur e.g. trackFoodBehavior
            newDinosaur.increaseFoodLevel(beEatenByBrachiosaur);
        }
    }

    @Override
    public int getEcoPoint() {
        return ecoCost;
    }
}
