package game.action;

import edu.monash.fit2099.engine.*;
import game.portableItem.Fruit;
import game.dinosaur.*;

import java.util.ArrayList;
import java.util.List;


public class EatAction extends Action{


    protected Location foodLon;
    public EatAction(Location foodLon){this.foodLon=foodLon;};

    @Override
    public String execute(Actor actor, GameMap map) {

        String result = actor + " 准备吃水果";
//        Location currentLocation = map.locationOf(actor);
//        List<Item> itemList1=currentLocation.getItems();
//        for (Item itemIndex:itemList1) {
//            if (itemIndex instanceof Fruit) {
//                System.out.println("当前地址有水果");
//                result = actor + " " + "eat a friut";
//            }
//        }
        new Fruit().beEaten(foodLon,(Dinosaur)actor);
        result = actor + " 吃了一个水果";
        return result;
    }
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is eating a fruit";
    }

}
