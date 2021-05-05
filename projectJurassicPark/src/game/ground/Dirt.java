package game.ground;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	public Dirt() {
		super('.');
	}

	private Boolean growBush(Location currentLocation) {
		int squareBushes = 0;
		int possibility = new Random().nextInt(100);

		// check if surroundings are trees or bushes
		for (Exit surroundingLoc : currentLocation.getExits()) {
			if (surroundingLoc.getDestination().getGround() instanceof Tree){
				return false;
			}
			if (surroundingLoc.getDestination().getGround() instanceof Bush){
				squareBushes += 1;
			}
		}

		if (squareBushes >= 2) {
			// 10% chance to grow a bush
			return possibility < 10;
		} else {
			// 1% chance to grow a bush
			return possibility < 1;
		}
	}

	@Override
	public void tick(Location location) {
		if(growBush(location)) {
			location.setGround(new Bush());
		}
	}
}
