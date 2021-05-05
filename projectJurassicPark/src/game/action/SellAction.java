package game.action;

import edu.monash.fit2099.engine.*;
import game.portableItem.Purchasable;

import java.util.ArrayList;

public class SellAction extends Action {

    private ArrayList<Purchasable> itemList;

    public SellAction(ArrayList<Purchasable> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Actions actionList = new Actions();
        for (Purchasable itemToPurchase: itemList) {
            actionList.add(new PurchaseAction(itemToPurchase));
        }
        Action selectedItem = new Menu().showMenu(actor, actionList, new Display());
        return selectedItem.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Purchasing items from the vending machine";
    }
}
