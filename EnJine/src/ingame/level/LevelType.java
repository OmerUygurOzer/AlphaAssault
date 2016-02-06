package ingame.level;

/**
 * Created by Omer on 1/17/2016.
 */
public enum LevelType {
    MAP_FLAT, MAP_ISOMETRIC, PLATFORMER;
    public static String[] toStringArray(){
        return new String[]{ MAP_FLAT.toString(),MAP_ISOMETRIC.toString(),PLATFORMER.toString()};
    }
}
