package com.boomer.alphaassault.gameworld.level;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 12/29/2015.
 */
public class Level {
    private String resourcesPath;




    private List<Entity> entityList;
    private List<Vector2> startingPointsList;

    private float duration;

    private String levelName;

    public Level(String _resourcesPath,String _levelName){
        levelName = _levelName;
        resourcesPath = _resourcesPath;
        entityList = new ArrayList<Entity>();
        startingPointsList = new ArrayList<Vector2>();

        LevelLoader.load(this);
    }



}
