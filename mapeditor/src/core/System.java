package core;

import com.badlogic.gdx.Gdx;

/**
 * Created by Omer on 12/28/2015.
 */
public class System {

    public static final float FPS = 1/60f;

    public static int REAL_WIDTH;
    public static int REAL_HEIGHT;

    public static int VIRTUAL_WIDTH = 1024;
    public static int VIRTUAL_HEIGHT = 768;
    public static double VIRTUAL_ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static void init(){
        REAL_WIDTH = Gdx.graphics.getWidth();
        REAL_HEIGHT = Gdx.graphics.getHeight();

        SCREEN_WIDTH = VIRTUAL_WIDTH;
        SCREEN_HEIGHT = VIRTUAL_HEIGHT;

    }

    public static void setScreenSize(int _width,int _height){
        SCREEN_WIDTH = _width;
        SCREEN_HEIGHT = _height;
    }

}
