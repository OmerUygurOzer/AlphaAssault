package ingame.level;

import ingame.objects.GameObject;

import java.util.ArrayList;

/**
 * Created by Omer on 2/5/2016.
 */
public class Level {
    protected String name;

    protected int width;
    protected int height;

    protected int tileSize;

    private ArrayList<GameObject>[] tiles;

}
