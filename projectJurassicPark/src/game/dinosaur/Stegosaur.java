package game.dinosaur;


import edu.monash.fit2099.engine.*;
import game.action.AttackAction;
import game.behaviour.*;
import game.portableItem.FoodCapability;

/**
 * A herbivorous dinosaur.
 */
public class Stegosaur extends Dinosaur {
    private Behaviour behaviour;


    /**
     * Constructor.
     * All Stegosaurs are represented by a 'd' and have 100 hit points.
     *
     * @param name the name of this Stegosaur
     */
    public Stegosaur(String name, Gender gender) {
        super(name, 's', 50, 1, 100, gender, 90, 50, 20, 30, 50, FoodCapability.STEGOSAUREAT);
        behaviour = new WanderBehaviour();
    }


    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }

    /**
     * Figure out what to do next.
     * <p>
     * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */


}
