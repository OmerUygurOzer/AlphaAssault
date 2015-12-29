package com.boomer.alphaassault.gameworld.map;

import com.badlogic.gdx.Gdx;
import com.boomer.alphaassault.networkutils.SerializationWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Omer on 12/26/2015.
 */
public class MapIO {

    public static Map loadMap(Map _map,String _name){
        String path = Gdx.files.getLocalStoragePath();
        path +="maps\\" + _name + ".bm";
        System.out.println("Loading from:" + path);

        MapData newData = new MapData();
        Path mPath = Paths.get(path);
        try {
            byte[] bytes = Files.readAllBytes(mPath);
            newData.deSerialize(bytes);
            newData.parseMap(_map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveMap(Map _map, String _name){
        String path = Gdx.files.getLocalStoragePath();
        path +="maps\\" + _name + ".bm";
        System.out.println("Saving to:" + path);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream   = new FileOutputStream(path);
            fileOutputStream.write(_map.getData().serialize());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
