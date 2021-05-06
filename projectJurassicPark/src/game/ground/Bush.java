package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.portableItem.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bush extends Ground {

    private List<Fruit> fruitGrown = new ArrayList();

     public Bush() {
         super('w');
    }
    public List<Fruit> getFruitGrown() {
        return fruitGrown;
    }




    @Override
    public void tick(Location location) {
        super.tick(location);

        int growFruit = new Random().nextInt(100);


        for (int i=0;i<growFruit;i++){
            fruitGrown.add(new Fruit());

        }

        if (growFruit < 10) {
            location.addItem(new Fruit());
        }


    }
}
