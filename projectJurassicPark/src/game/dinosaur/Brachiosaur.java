package game.dinosaur;

import game.portableItem.FoodCapability;

public class Brachiosaur extends Dinosaur{

    public Brachiosaur(String name, Gender gender) {
        super(name, 'b', 100, 1,160, gender, 140, 70,15,50,50, FoodCapability.BRACHIOSAUREAT);
    }

}
