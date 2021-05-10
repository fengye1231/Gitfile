package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaur.Brachiosaur;
import game.dinosaur.Dinosaur;
import game.dinosaur.Stegosaur;
import game.dinosaur.Gender;

import java.util.Random;


public class BreedAction extends Action {


    protected Dinosaur target;
    protected Dinosaur self;

    public BreedAction(Dinosaur target){this.target=target;}

    @Override
    public String execute(Actor actor, GameMap map)  {

            String result=actor+" 交配";
//            if ((target instanceof Stegosaur) && (self instanceof Stegosaur)) {
////                String sex=target.getClass().getDeclaredField("gender").toString();
//
//                if ((target.getGender()!=self.getGender()) && ((target.getHitPoints())>50)){
//                    result="与"+target+"交配";
//                }
//
//            }
//            else if ((target instanceof Brachiosaur) && (self instanceof Brachiosaur)) {
//                // remove all the fruits from trees
//                // this should be down for the actions part of dinosaur e.g. trackFoodBehavior
//                if ((target.getGender()!=self.getGender()) && ((target.getHitPoints())>70)){
//                    result="与"+target+"交配";
//                }
//            }
            return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "进行了交配";
    }



}
