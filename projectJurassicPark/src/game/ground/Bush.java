package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.portableItem.Fruit;

import java.util.Random;

public class Bush extends Ground {

     public Bush() {
         super('w');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        int growFruit = new Random().nextInt(100);
        if (growFruit < 10) {
            location.addItem(new Fruit());
        }


    }
}
