package game.behaviour;

import edu.monash.fit2099.engine.*;


import game.action.EatAction;
import game.portableItem.Fruit;
import game.dinosaur.*;

import game.dinosaur.Allosaur;

import java.util.*;

//implements Behaviour
public class TrackSpouseBehaviour  implements Behaviour  {

    private Map<Location, Integer> locDis1= new HashMap<>();

//    private List<Actor> actors= new ArrayList<>();

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {

        String sex=((Dinosaur)actor).getGender();

        for (int i:map.getXRange()) {
            for (int j : map.getYRange()) {
                //.getActor()
                Actor actor1 = (map.at(i, j)).getActor();
                if (((Dinosaur) actor1).getGender() != ((Dinosaur) actor).getGender()) {
                    System.out.println("找到了一个异性同类");
                    int distance1 = Math.abs(map.locationOf(actor).x() - i) + Math.abs(map.locationOf(actor).y() - j);
                    locDis1.put(map.at(i, j), distance1);
                    System.out.println("将一个异性的位置和距离存储了起来");
                }
            }
        }

        if (!(locDis1.isEmpty())){
            List<Map.Entry<Location, Integer>> list1 = new ArrayList<>(locDis1.entrySet());
            Collections.sort(list1,new Comparator<Map.Entry<Location, Integer>>() {
                //升序排序
                public int compare(Map.Entry<Location, Integer> o1,
                                   Map.Entry<Location, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            Location here = map.locationOf(actor);
            Location there = list1.get(0).getKey();

            int currentDistance = distance(here, there);
            for (Exit exit : here.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, there);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }else {
                        //准备交配
                        return new EatAction(there);
                    }

                }
            }
        }
        return null;
    }

}


