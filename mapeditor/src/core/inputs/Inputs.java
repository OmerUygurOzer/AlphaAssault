package core.inputs;

import com.badlogic.gdx.math.Vector2;
import core.utils.GameMath;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Omer on 11/28/2015.
 */
public class Inputs {


        private static volatile Vector2 hover;
        private static int numberOfInputs;
        private static Map<Long,Vector2> inputTouch;
        private static Map<Integer,Boolean> inputKeys;
        public static final int INPUT_SEPARATOR;


        static{
            hover = new Vector2(0,0);
            numberOfInputs = 0;
            inputTouch = new HashMap<Long, Vector2>();
            inputKeys = new HashMap<Integer, Boolean>();
            INPUT_SEPARATOR = 90;
           }


        public static void updateHover(int _x, int _y){
                hover.x = _x;
                hover.y = _y;
                //System.out.println(_x +":"+_y);
        }

        public static void inputAcquire(int _x, int _y){
            boolean newInput = true;
            if(inputTouch.isEmpty()){
                numberOfInputs++;
                inputTouch.put(System.nanoTime(),new Vector2(_x,_y));
                return;
            }
            for(Long key: inputTouch.keySet()){
                if(GameMath.getDistance(inputTouch.get(key).x, inputTouch.get(key).y,_x,_y)<INPUT_SEPARATOR){
                    inputTouch.get(key).x = _x;
                    inputTouch.get(key).y = _y;
                    newInput = false;
                 }

            }
            if (newInput) {
                numberOfInputs++;
                inputTouch.put(System.nanoTime(),new Vector2(_x,_y));
            }
        }

        public static void inputAcquire(int _key){
            inputKeys.put(_key,true);
        }



        public static void inputRelease(int _x, int _y){
            for(Long key: inputTouch.keySet()){
                        if(GameMath.getDistance(inputTouch.get(key).x, inputTouch.get(key).y,_x,_y)<INPUT_SEPARATOR){
                            inputTouch.remove(key);
                            numberOfInputs--;
                            continue;
                        }
            }


        }

        public static void inputRelease(int _key){
            inputKeys.put(_key,false);
         }

        public static Vector2 getHoverLocation(){
           return hover;
        }
        public static Map<Long,Vector2> getInputTouch(){return inputTouch;}
        public static Map<Integer,Boolean> getInputKeys(){return inputKeys;}
        public static boolean isEmtpy(){return inputTouch.isEmpty();}


}
