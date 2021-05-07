package game.behaviour;

import edu.monash.fit2099.engine.*;
//import game.portableItem.Fruit;
import game.ground.Tree;
import game.ground.Bush;
import game.portableItem.Fruit;
import edu.monash.fit2099.engine.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;


public class TrackFoodBehaviour implements Behaviour{
//public class TrackFoodBehaviour {
    protected Location foodLocation;

    //树上掉的和灌木丛中的水果集合
    private List<Fruit> list = new ArrayList<>();
    private int tem;
    private Random random = new Random();
    private String hotKey;

//    private Tree tree1=new Tree();
//    private Bush bush1=new Bush();


    public void setHotKey(String hotKey){
        this.hotKey=hotKey;

    }
    public String getHotKey(){
        return hotKey;

    }

    public void merge(Tree tree1,Bush bush1){
        list.addAll(tree1.getFruitDrop());
        list.addAll(bush1.getFruitGrown());
    }
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private int min(int a,int b){
        if(a>b){
            return b;
        }else {
            return a;
        }
    }
    public Location minimum(Actor actor, GameMap map){
        Location self = map.locationOf(actor);
        for(int i=0;i<list.size();i++){
            if (distance(self,list.get(i).getPortableItemLocation())<distance(self,list.get(i+1).getPortableItemLocation())){
                tem=i;
            }
        }
        return (list.get(tem)).getPortableItemLocation();
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {
        // track closest food location 寻找最近的可以吃的食物。
        ArrayList<Action> actions = new ArrayList<Action>();
        actions.add(minimum(actor,map).getMoveAction(actor, "around", this.getHotKey()));

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }

//        int currentDistance = distance(here, there);
//        for (Exit exit : here.getExits()) {
//            Location destination = exit.getDestination();
//            if (destination.canActorEnter(actor)) {
//                int newDistance = distance(destination, there);
//                if (newDistance < currentDistance) {
//                    return new MoveActorAction(destination, exit.getName());
//                }
//            }
//        }












    }
}
