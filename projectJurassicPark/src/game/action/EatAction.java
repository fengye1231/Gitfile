package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.portableItem.Fruit;

import java.util.Random;

public class EatAction extends Action {
    Location currentLocation;
    public EatAction(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int possibility = new Random().nextInt(100);
        if (possibility < 60) {
            actor.addItemToInventory(new Fruit());
            EcoPoint.setEcoPoint(10);
            return "You search the tree or bush and found a ripe fruit!";
        } else {
            return "You search the tree or bush for fruit, but you can’t find any ripe ones.";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }


}
