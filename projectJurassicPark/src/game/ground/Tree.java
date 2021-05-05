package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.EcoPoint;
import game.portableItem.Fruit;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {
	private int age = 0;

	// consider adding an arrayList to stores a list of Fruits
	private ArrayList<Item> fruitGrown = new ArrayList();

	public Tree() {
		super('+');
	}

	public ArrayList<Item> getFruitGrown() {
		return fruitGrown;
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		int growFruit = new Random().nextInt(100);
		int dropFruit = new Random().nextInt(100);

		// tree has possibility of growing fruit
		if (growFruit < 50) {
			if (dropFruit < 5) {
				location.addItem(new Fruit());
			}
			else {
				fruitGrown.add(new Fruit());
			}
			EcoPoint.setEcoPoint(1);
		}
		else if (!fruitGrown.isEmpty()) {
			if (dropFruit < 5) {
				location.addItem(fruitGrown.get(0));
				// remove one fruit from fruitGrown
				fruitGrown.remove(0);
			}
		}
	}
}
