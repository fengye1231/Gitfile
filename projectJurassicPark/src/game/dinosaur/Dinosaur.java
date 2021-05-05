package game.dinosaur;

import edu.monash.fit2099.engine.*;
import game.behaviour.Behaviour;
import game.behaviour.TrackFoodBehaviour;
import game.behaviour.TrackSpouseBehaviour;
import game.portableItem.Corpse;
import game.portableItem.FoodCapability;

public abstract class Dinosaur extends Actor {
    Behaviour behaviour;
    Gender gender;
    int maxFoodLevel;
    int startTrackFood;
    int startTrackSpouse;
    int age;
    boolean isBaby = true;
    int unconsciousTurn;
    int adultAge;
    int increaseFoodLevel;
    FoodCapability foodCapability;


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints, int age, int maxFoodLevel, Gender gender, int startTrackFood, int startTrackSpouse, int unconsciousTurn, int adultAge, int increaseFoodLevel, FoodCapability foodCapability) {
        super(name, displayChar, hitPoints);
        setHitPoints(hitPoints);
        this.age = age;
        this.maxFoodLevel = maxFoodLevel;
        this.gender = gender;
        this.startTrackFood = startTrackFood;
        this.startTrackSpouse = startTrackSpouse;
        this.unconsciousTurn = unconsciousTurn;
        this.adultAge = adultAge;
        this.increaseFoodLevel = increaseFoodLevel;
        this.foodCapability = foodCapability;
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        age++;

        if (age > adultAge) {
            isBaby = false;
        }

        if (isConscious()) {
            display.println(this + " at (" + map.locationOf(this).x() + "," + map.locationOf(this).y() + ") is unconscious now");
            unconsciousTurn -= 1;

            if (unconsciousTurn == 0) {
                map.locationOf(this).addItem(this.getCorpse());
                map.removeActor(this);
            }
            return new DoNothingAction();
        }

        if (hitPoints < startTrackFood) {
            behaviour = new TrackFoodBehaviour();
            hitPoints--;
        }
        if (hitPoints > startTrackSpouse && !isBaby) {
            behaviour = new TrackSpouseBehaviour();
            hitPoints--;
        }

        Action action = behaviour.getAction(this, map);
        Action wander = behaviour.getAction(this, map);
        if (action != null) {
            return action;
        } else if (wander != null) {
            return wander;
        } else {
            return new DoNothingAction();
        }
    }

    public void increaseFoodLevel(int addHitPoints) {
        this.hitPoints += addHitPoints;
    }

    public void setHitPoints(int hitPoints) {
        if (hitPoints < 0) {
            this.hitPoints = 0;
        } else if (hitPoints > maxFoodLevel) {
            this.hitPoints = maxFoodLevel;
        } else {
            this.hitPoints = hitPoints;
        }
    }

    private Corpse getCorpse() {
        return new Corpse(40, increaseFoodLevel);
    }

}
