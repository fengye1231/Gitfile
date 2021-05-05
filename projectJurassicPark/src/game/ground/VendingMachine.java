package game.ground;

import edu.monash.fit2099.engine.*;
import game.LaserGun;
import game.action.SellAction;
import game.portableItem.*;
import game.portableItem.egg.AllosaursEgg;
import game.portableItem.egg.BrachiosaurusEgg;
import game.portableItem.egg.StegosaurusEgg;

import java.util.ArrayList;

public class VendingMachine extends Ground {

    private ArrayList<Purchasable> itemList = new ArrayList();

    public VendingMachine() {
        super('V');

        // initialise the itemList to contain the item for selling
        // consider creating a setter with unmodifiable list to get itemList
        itemList.add(new Fruit());
        itemList.add(new VegetarianMealKit());
        itemList.add(new CarnivoreMealKit());
        itemList.add(new StegosaurusEgg());
        itemList.add(new BrachiosaurusEgg());
        itemList.add(new AllosaursEgg());
        itemList.add(new LaserGun());
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        // call the action for selling items
        return new Actions(new SellAction(itemList));
    }
}
