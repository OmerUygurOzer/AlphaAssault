package core.inputs;

import com.badlogic.gdx.math.Vector2;
import core.utils.GameMath;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Omer on 11/28/2015.
 */
public class Inputs {


        private static volatile Vector2 hover;
        private static int numberOfInputs;
        private static Map<Long,Vector2> inputs;
        public static final int INPUT_SEPARATOR;


        static{
            hover = new Vector2(0,0);
            numberOfInputs = 0;
            inputs = new ConcurrentHashMap<Long, Vector2>();
            INPUT_SEPARATOR = 90;
           }


        public static void updateHover(int _x, int _y){
                hover.x = _x;
                hover.y = _y;

        }

        public static void inputAcquire(int _x, int _y){
            boolean newInput = true;
            if(inputs.isEmpty()){
                numberOfInputs++;
                inputs.put(System.nanoTime(),new Vector2(_x,_y));
                return;
            }
            for(Long key: inputs.keySet()){
                if(GameMath.getDistance(inputs.get(key).x,inputs.get(key).y,_x,_y)<INPUT_SEPARATOR){
                    inputs.get(key).x = _x;
                    inputs.get(key).y = _y;
                    newInput = false;
                 }

            }
            if (newInput) {
                numberOfInputs++;
                inputs.put(System.nanoTime(),new Vector2(_x,_y));
            }
        }

        public static void inputRelease(int _x, int _y){
            for(Long key: inputs.keySet()){
                        if(GameMath.getDistance(inputs.get(key).x,inputs.get(key).y,_x,_y)<INPUT_SEPARATOR){
                            inputs.remove(key);
                            numberOfInputs--;
                            continue;
                        }
            }


        }

        public static Vector2 getHoverLocation(){
           return hover;
        }
        public static Map<Long,Vector2> getInputs(){return inputs;}
        public static boolean isEmtpy(){return inputs.isEmpty();}


}
