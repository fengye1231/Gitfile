package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.EcoPoint;
import game.portableItem.Purchasable;

public class PurchaseAction extends Action {
    private Purchasable purchasedItem;

    public PurchaseAction(Purchasable itemToPurchase) {
        purchasedItem = itemToPurchase;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (EcoPoint.setEcoPoint(-purchasedItem.getEcoPoint())) {
            actor.addItemToInventory((Item) purchasedItem);
            return " You Successfully purchased the item: " + purchasedItem + " with " + purchasedItem.getEcoPoint() +
                    " eco points.";
        }
        return "Your eco points are not enough :(";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Item: " + purchasedItem + "is purchased with eco points of " + purchasedItem.getEcoPoint();
    }
}
