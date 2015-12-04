package com.boomer.alphaassault.handlers.controls;

import com.boomer.alphaassault.utilities.Location;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Omer on 11/28/2015.
 */
public class Inputs {


        private static volatile Location hover;
        private static int numberOfInputs;
        private static Map<Long,Location> inputs;
        public static final int INPUT_SEPARATOR;


        static{
            hover = new Location(0,0);
            numberOfInputs = 0;
            inputs = new ConcurrentHashMap<Long, Location>();
            INPUT_SEPARATOR = 90;
           }


        public static void updateHover(int _x, int _y){
                hover.x = _x;
                hover.y = _y;

        }

        public static void inputAcquire(int _x, int _y){

            if(inputs.isEmpty()){
                numberOfInputs++;
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
                numberOfInputs++;
                inputs.put(System.currentTimeMillis(),new Location(_x,_y));


        }

        public static void inputRelease(int _x, int _y){
            for(Long key: inputs.keySet()){
                        if(Location.getDistance(inputs.get(key),new Location(_x,_y))<INPUT_SEPARATOR){
                            inputs.remove(key);
                            numberOfInputs--;
                            //System.out.println("INPUT RELEASED!");
                            continue;
                        }
            }


        }

        public static Location getHoverLocation(){
           return hover;
        }
        public static Map<Long,Location> getInputs(){return inputs;}
        public static boolean isEmtpy(){return inputs.isEmpty();}









}
