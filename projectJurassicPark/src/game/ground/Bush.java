package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.portableItem.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bush extends Ground {

    private List<Location> buFruits = new ArrayList();

    public Bush() {
         super('w');
    }
    public List<Location> getBuFruits() {
        return buFruits;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        int growFruit = new Random().nextInt(100);


        if (growFruit < 10) {
            location.addItem(new Fruit());

            buFruits.add(location);
//            System.out.println("灌木丛中水果数量："+buFruits.size());

        }


    }
}
