package com.boomer.alphaassault.handlers.controls;

import com.boomer.alphaassault.utilities.Location;

import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Omer on 11/28/2015.
 */
public class Inputs {

        //MOUSE/TOUCH hover
        private static Location hover;

        //THREAD LOCKS
        private static Object HOVER_LOCK;

        //SUPPORT MULTIPLE inputs//PRESSES BEING DONE SIMULTANEOUSLY
        private static int NUMBER_OF_INPUTS;
        private static ConcurrentHashMap<Long,Location> inputs;
        public static final int INPUT_SEPARATOR;


        static{

            hover = new Location(0,0);


            //DRAG STATICS
            NUMBER_OF_INPUTS = 0;
            inputs = new ConcurrentHashMap<Long, Location>();
            INPUT_SEPARATOR = 90;

            //LOCK STATICS
            HOVER_LOCK = new Object();



        }


        public static void updateHover(int _x, int _y){
            synchronized (HOVER_LOCK) {
                hover.x = _x;
                hover.y = _y;
            }
        }

        public static void inputAcquire(int _x, int _y){

            if(inputs.isEmpty()){
                NUMBER_OF_INPUTS++;
                inputs.put(System.currentTimeMillis(),new Location(_x,_y));
                return;
            }
            for(Long key: inputs.keySet()){
                if(Location.getDistance(inputs.get(key),new Location(_x,_y))<INPUT_SEPARATOR){
                    inputs.get(key).x = _x;
                    inputs.get(key).y = _y;
                    //System.out.println("INPUT UPDATED!");

                }

            }
                NUMBER_OF_INPUTS++;
                inputs.put(System.currentTimeMillis(),new Location(_x,_y));


        }

        public static void inputRelease(int _x, int _y){
            for(Long key: inputs.keySet()){
                        if(Location.getDistance(inputs.get(key),new Location(_x,_y))<INPUT_SEPARATOR){
                            inputs.remove(key);
                            NUMBER_OF_INPUTS--;
                            //System.out.println("INPUT RELEASED!");
                            continue;
                        }
            }


        }




        public static Location getHoverLocation(){
           return hover;
        }
        public static ConcurrentHashMap<Long,Location> getInputs(){
            return inputs;
        }
        public static boolean isEmtpy(){return inputs.isEmpty();}









}
