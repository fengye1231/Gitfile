package game.portableItem.egg;

import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.dinosaur.Allosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Gender;

import java.util.Random;

import static game.dinosaur.Gender.*;


public class AllosaursEgg extends Egg{

    int ecoCost = 1000;
    int ecoIncreased = 1000;

    public AllosaursEgg() {
        super("Allosaurs Egg", 'a', 20);
    }

    // the egg can be picked up, does it means that the player can enter
    // the place where the egg locates ?

    @Override
    public void hatch(Location location) {
        int gender = new Random().nextInt(2);
        Dinosaur newDinosaur;
        if (gender == 0) {
            // no need for name
            newDinosaur = new Allosaur("Allosaur", Gender.MALE);
        } else { newDinosaur = new Allosaur("Allosaur", Gender.FEMALE); }

        location.addActor(newDinosaur);
        EcoPoint.setEcoPoint(ecoIncreased);
    }


    @Override
    public int getEcoPoint() {
        return ecoCost;
    }
}
