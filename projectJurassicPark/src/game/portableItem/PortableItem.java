package game.portableItem;

import edu.monash.fit2099.engine.Item;

import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.ActorLocations;
/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	private Location location;

	public void setPortableItemLocation(Location location){
		this.location=location;
	}

	public Location getPortableItemLocation(){
		return location;
	}

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
