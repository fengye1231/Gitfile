package game.portableItem.egg;

import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.dinosaur.Allosaur;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Gender;

import java.util.Random;

public class BrachiosaurusEgg extends Egg{
    int ecoCost = 500;
    int ecoIncreased = 1000;

    public BrachiosaurusEgg() {
        super("Brachiosaurus Egg", 'b', 20);
    }

    @Override
    public void hatch(Location location) {
        int gender = new Random().nextInt(2);
        Dinosaur newDinosaur;
        if (gender == 0) {
            // no need for name
            newDinosaur = new Brachiosaur("Brachiosaur", Gender.MALE);
        } else { newDinosaur = new Brachiosaur("Brachiosaur", Gender.FEMALE); }

        location.addActor(newDinosaur);
        EcoPoint.setEcoPoint(ecoIncreased);
    }


    @Override
    public int getEcoPoint() {
        return ecoCost;
    }
}
