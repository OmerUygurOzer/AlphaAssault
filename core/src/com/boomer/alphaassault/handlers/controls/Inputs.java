package com.boomer.alphaassault.handlers.controls;

import com.boomer.alphaassault.utilities.Location;

import java.util.HashMap;


/**
 * Created by Omer on 11/28/2015.
 */
public class Inputs {

        public static int HOVER_X;
        public static int HOVER_Y;

        //THREAD LOCKS
        private static Object HOVER_LOCK;
        private static Object INPUT_LOCK;

        //SUPPORT MULTIPLE INPUTS//PRESSES BEING DONE SIMULTANEOUSLY
        private static int NUMBER_OF_INPUTS;
        private static HashMap<Long,Location> INPUTS;
        private static int INPUT_SEPARATOR;


        static{
            HOVER_X = 0;
            HOVER_Y = 0;


            //DRAG STATICS
            NUMBER_OF_INPUTS = 0;
            INPUTS = new HashMap<Long, Location>();
            INPUT_SEPARATOR = 90;

            //LOCK STATICS
            HOVER_LOCK = new Object();
            INPUT_LOCK = new Object();


        }


        public static void updateHover(int _x, int _y){
            synchronized (HOVER_LOCK) {
                HOVER_X = _x;
                HOVER_Y = _y;
            }
        }

        public static void beingDragged(int _x, int _y){
            synchronized (INPUT_LOCK) {
                if(INPUTS.isEmpty()){
                    NUMBER_OF_INPUTS++;
                    INPUTS.put(System.currentTimeMillis(),new Location(_x,_y));
                    //System.out.println("DRAG ACQUIRED!");
                    return;
                }
                for(Long key: INPUTS.keySet()){
                    if(Location.getDistance(INPUTS.get(key),new Location(_x,_y))< INPUT_SEPARATOR){
                        INPUTS.get(key).x = _x;
                        INPUTS.get(key).y = _y;
                        //System.out.println("DRAG UPDATED!");
                        continue;
                    }
                }



            }
        }

        public static void touchRelease(int _x, int _y){
            synchronized (INPUT_LOCK){
                for(Long key: INPUTS.keySet()){
                    if(Location.getDistance(INPUTS.get(key),new Location(_x,_y))<INPUT_SEPARATOR){
                        INPUTS.remove(key);
                        INPUT_SEPARATOR--;
                        System.out.println("INPUT RELEASED!");
                        continue;
                    }
                }
            }



        }

        public static void touchAcquire(int _x,int _y){
            synchronized (INPUT_LOCK){
                if(INPUTS.isEmpty()){
                    INPUT_SEPARATOR++;
                    INPUTS.put(System.currentTimeMillis(),new Location(_x,_y));
                    return;
                }
                for(Long key: INPUTS.keySet()){
                    if(Location.getDistance(INPUTS.get(key),new Location(_x,_y))<INPUT_SEPARATOR){
                        INPUTS.get(key).x = _x;
                        INPUTS.get(key).y = _y;
                        //System.out.println("PRESS UPDATED!");
                        continue;
                    }
                }

            }
        }


        public static Location getHoverLocation(){
            Location location;
            synchronized (HOVER_LOCK){
                location = new Location(HOVER_X,HOVER_Y);
            }
            return location;
        }


        public static HashMap<Long,Location> getInputs(){
            HashMap<Long,Location> HashMap;
            synchronized (INPUT_LOCK){
                HashMap = new HashMap<Long, Location>();
                HashMap.putAll(INPUTS);
            }
            return HashMap;
        }










}
