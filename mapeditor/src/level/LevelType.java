package level;

/**
 * Created by Omer on 1/17/2016.
 */
public enum LevelType {
    MAP_FLAT, MAP_ISOMORPHIC, PLATFORMER;
    public static String[] toStringArray(){
        return new String[]{ MAP_FLAT.toString(),MAP_ISOMORPHIC.toString(),PLATFORMER.toString()};
    }
}
