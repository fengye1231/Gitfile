package game.portableItem;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

public class CarnivoreMealKit extends PortableItem implements Purchasable {

    private int ecoCost = 500;
    private int foodLevelPoint = 200;

    public CarnivoreMealKit() {
        super("CarnivoreMealKit", 'C');
        this.addCapability(FoodCapability.FEEDALLOSAUR);
    }


    public void feed(Actor player, Dinosaur newDinosaur){
        player.removeItemFromInventory(this);
        newDinosaur.increaseFoodLevel(foodLevelPoint);
    }

    @Override
    public int getEcoPoint() {
        return ecoCost;
    }
}
