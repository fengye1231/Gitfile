package game.portableItem;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.dinosaur.Dinosaur;

public class VegetarianMealKit extends PortableItem implements Purchasable {

    private int ecoCost = 100;
    // 200 is seems to be greater than the foodLevel of all dinosaurs (big enough)
    private int foodLevelPoint = 200;

    public VegetarianMealKit() {
        super("VegetarianMealKit", 'M');
        this.addCapability(FoodCapability.FEEDBRACHIOSAUR);
        this.addCapability(FoodCapability.FEEDSTEGOSAUR);
    }

    // may need to check the dinosaur type
    public void feed(Actor player, Dinosaur newDinosaur){
        player.removeItemFromInventory(this);
        newDinosaur.increaseFoodLevel(foodLevelPoint);
    }

    // This method actually is not needed since the item can only be purahcsed from Vending Machine
    // and only player can feed this item to dinosaurs
    // However, there is a case when player drops this item and dinosaurs eat it
    public void beEaten(Location location, Dinosaur newDinosaur) {
        location.removeItem(this);
        newDinosaur.increaseFoodLevel(foodLevelPoint);
    }

    @Override
    public int getEcoPoint() {
        return ecoCost;
    }
}
