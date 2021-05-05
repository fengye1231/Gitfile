package game.dinosaur;

import game.portableItem.FoodCapability;

public class Allosaur extends Dinosaur {

    public Allosaur(String name, Gender gender) {
        super(name, 'a', 20, 1, 100, gender, 90, 50, 20,50,50, FoodCapability.ALLOSAUREAT);
    }
}
