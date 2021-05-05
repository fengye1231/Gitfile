package game.action;

import edu.monash.fit2099.engine.*;
import game.dinosaur.Stegosaur;
import game.ground.Bush;
import game.ground.Tree;

import java.util.ArrayList;

public class FireAction extends Action {
    // to be modified
    private WeaponItem weaponItem;

    // Assume can only kill dinosaur that stands next to the player
    public FireAction(WeaponItem newWeaponItem) {
        weaponItem = newWeaponItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        ArrayList<Actor> stegosaursList = new ArrayList<>();

        for (Exit surroundingLoc : currentLocation.getExits()) {
            if (surroundingLoc.getDestination().getActor() instanceof Stegosaur){
                stegosaursList.add(surroundingLoc.getDestination().getActor());
            }
        }

        Actions actionsList = new Actions();
        for (Actor aimedStegosaur: stegosaursList) {
            if (!stegosaursList.isEmpty()) {
                actionsList.add(new AttackAction(aimedStegosaur));
            }
            else {
                return "There is no stegosaur nearby. ";
            }
        }
        Action selectedAction = new Menu().showMenu(actor, actionsList, new Display());
        return selectedAction.execute(actor, map);
    }

    @Override
    public Action getNextAction() {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "You fired using " + weaponItem;
    }
}
