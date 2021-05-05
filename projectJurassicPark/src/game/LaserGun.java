package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;
import game.action.FireAction;
import game.portableItem.Purchasable;

import java.util.List;

public class LaserGun extends WeaponItem implements Purchasable {

    int ecoCost = 500;

    public LaserGun() {
        super("Laser Gun", '>', 55, "shoot");
        // add the fireAction
        // allowableActions.add()

    }

    @Override
    public List<Action> getAllowableActions() {
        allowableActions.add(new FireAction(this));
        return allowableActions.getUnmodifiableActionList();
    }

    @Override
    public int getEcoPoint() {
        return ecoCost;
    }
}
