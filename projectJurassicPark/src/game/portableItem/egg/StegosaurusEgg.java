package game.portableItem.egg;

import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Gender;
import game.dinosaur.Stegosaur;

import java.util.Random;

public class StegosaurusEgg extends Egg{

    int ecoCost = 200;
    int ecoIncreased = 100;

    public StegosaurusEgg() {
        super("Stegosaurus Egg", 's', 15);
    }

    @Override
    public void hatch(Location location) {
        int gender = new Random().nextInt(2);
        Dinosaur newDinosaur;
        if (gender == 0) {
            // no need for name
            newDinosaur = new Stegosaur("Stegosaur", Gender.MALE);
        } else { newDinosaur = new Stegosaur("Stegosaur", Gender.FEMALE); }

        location.addActor(newDinosaur);
        EcoPoint.setEcoPoint(ecoIncreased);
    }


    @Override
    public int getEcoPoint() {
        return ecoCost;
    }

}
