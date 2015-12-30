package com.boomer.alphaassault.gameworld.level;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.map.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 12/29/2015.
 */
public class Level {
    private String resourcesPath;

    private Map map;


    private List<Entity> entityList;
    private List<Vector2> startingPointsList;

    private float duration;


    public Level(String _resourcesPath){
        resourcesPath = _resourcesPath;
        entityList = new ArrayList<Entity>();
        startingPointsList = new ArrayList<Vector2>();

        LevelLoader.load(this);
    }



}
