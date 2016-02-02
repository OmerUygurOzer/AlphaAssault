package level;

import com.badlogic.gdx.math.Vector2;
import level.objects.Tile;
import objects.ObjectBase;
import org.lwjgl.Sys;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 1/17/2016.
 */
public class Level implements Serializable{

    private String name;
    private int width;
    private int height;
    private int tileSize;

    private Map<String,File> objectFiles;
    private Map<String,List<Vector2>> locations;
    private Map<String,List<Integer>> layers;
    private Map<String,List<Vector2>> sizes;

    public Level() {

        this.width = 800;
        this.height = 800;
        this.tileSize = 20;

        objectFiles = new HashMap<String, File>();
        locations = new HashMap<String, List<Vector2>>();
        layers = new HashMap<String, List<Integer>>();
        sizes = new HashMap<String, List<Vector2>>();

    }

    public void setSize(int width,int heigth){
        this.width = width;
        this.height = heigth;
    }

    public void setTileSize(int tileSize){
        this.tileSize = tileSize;
    }
    public void setName(String name){
        this.name = name;
    }

    public void addObject(File file,Vector2 position,int layer,float width,float heigth){
        if(!objectFiles.containsKey(file.getName())){
            objectFiles.put(file.getName(),file);
            locations.put(file.getName(),new ArrayList<Vector2>());
            layers.put(file.getName(),new ArrayList<Integer>());
            sizes.put(file.getName(),new ArrayList<Vector2>());
        }
        locations.get(file.getName()).add(position);
        layers.get(file.getName()).add(layer);
        sizes.get(file.getName()).add(new Vector2(width,heigth));
        System.out.println(objectFiles.size());

    }

    public void clear(){
        objectFiles.clear();
        locations.clear();
        layers.clear();
        sizes.clear();
    }



}
