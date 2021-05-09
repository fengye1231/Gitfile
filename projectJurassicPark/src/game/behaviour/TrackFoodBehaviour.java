package game.behaviour;

import edu.monash.fit2099.engine.*;

import game.action.AttackAction;

import game.portableItem.Fruit;
import edu.monash.fit2099.engine.Location;
import game.action.EatAction;
import java.util.*;




public class TrackFoodBehaviour implements Behaviour{
//public class TrackFoodBehaviour {
//    protected Location foodLocation;

    //树上掉的和灌木丛中的水果集合
//    private List<Fruit> list = new ArrayList<>();
    private Map<Location, Integer> locDis= new HashMap<>();

    private int distance1(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

//    private List<Item>list1;

//    public void merge(Tree tree1,Bush bush1){
//        list.addAll(tree1.getFruitDrop());
//        list.addAll(bush1.getFruitGrown());
//    }
//    private int distance(Location a, Location b) {
//        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
//    }
//
//    private int min(int a,int b){
//        if(a>b){
//            return b;
//        }else {
//            return a;
//        }
//    }
//    public Location minimum(Actor actor, GameMap map){
//        Location self = map.locationOf(actor);
//        for(int i=0;i<list.size();i++){
//            if (distance(self,list.get(i).getPortableItemLocation())<distance(self,list.get(i+1).getPortableItemLocation())){
//                tem=i;
//            }
//        }
//        return (list.get(tem)).getPortableItemLocation();
//    }

    //track closest food location 寻找最近的可以吃的食物。
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (int i:map.getXRange()){
            for (int j:map.getYRange()){
                for (Item item1:((map.at(i,j)).getItems())){
                    if (item1 instanceof Fruit){
                        System.out.println("此位置有水果");
                        int distance=Math.abs(map.locationOf(actor).x()-i)+Math.abs(map.locationOf(actor).y()-j);
                        locDis.put(map.at(i,j),distance);


                    }
                }
            }
        }

        if (!(locDis.isEmpty())){
            List<Map.Entry<Location, Integer>> list1 = new ArrayList<>(locDis.entrySet());
            Collections.sort(list1,new Comparator<Map.Entry<Location, Integer>>() {
                //升序排序
                public int compare(Map.Entry<Location, Integer> o1,
                                   Map.Entry<Location, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            Location here = map.locationOf(actor);
            Location there = list1.get(0).getKey();

            int currentDistance = distance1(here, there);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance1(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }else {
                        //在水果的位置上
                        return new EatAction(there);
                    }

                    }
                }
            }
        return null;
        }

    }

 //        track closest food location 寻找最近的可以吃的食物。
//        ArrayList<Action> actions = new ArrayList<Action>();
//        actions.add(minimum(actor,map).getMoveAction(actor, "around", this.getHotKey()));

//        if (!actions.isEmpty()) {
//            return actions.get(random.nextInt(actions.size()));
//        }
//        else {
//            return null;
//        }

//        int currentDistance = distance(here, there);
//        for (Exit exit : here.getExits()) {
//            Location destination = exit.getDestination();
//            if (destination.canActorEnter(actor)) {
//                int newDistance = distance(destination, there);
//                if (newDistance < currentDistance) {
//                    return new MoveActorAction(destination, exit.getName());
//                }
//            }



//}
