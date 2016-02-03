package level;


import level.objects.LoadableObject;
import level.objects.MapObject;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Omer on 1/17/2016.
 */
public class LoadableLevel implements Serializable{

    private String name;
    private int width;
    private int height;
    private int tileSize;


    private transient ArrayList<MapObject>[] tiles;
    private ArrayList<LoadableObject> objects;


    public LoadableLevel() {

        this.width = 800;
        this.height = 800;
        this.tileSize = 20;
        objects = new ArrayList<LoadableObject>();
        generateLevel();
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
    public void generateLevel(){
        tiles = new ArrayList[(width/tileSize)*(height/tileSize)];
        for(int i = 0; i < tiles.length;i++){
            tiles[i] = new ArrayList<MapObject>();
        }
    }

    public boolean fits(int tileX,int tileY){
       if (tileX>=width/tileSize)return false;
       if (tileY>=height/tileSize)return false;
       if(tileX<0 || tileY < 0)return false;
        return true;
    }

    public void addObject(MapObject object,int tileX,int tileY){
            LoadableObject loadableObject = new LoadableObject();
            loadableObject.objectFileName = object.getObjectFile().getName();
            loadableObject.imageWidth = (int)object.getCurrentFrame().getWidth();
            loadableObject.imageWidth = (int)object.getCurrentFrame().getHeight();
            loadableObject.layer = object.layer;
            loadableObject.x = (int) object.position.x;
            loadableObject.y = (int) object.position.y;
            objects.add(loadableObject);


           int index = tileX + tileY*(width/tileSize);
            tiles[index].add(object);
    }

    public void removeObject(MapObject object){
        for(int i = 0; i < tiles.length;i++) {
            if (tiles[i].contains(object)) {
                tiles[i].remove(object);
                return;
            }
        }
    }

    public ArrayList<MapObject> getTile(int x, int y){return tiles[x + y*(width/tileSize)];}



    public void clear(){
        objects.clear();
        for(int i = 0; i < tiles.length;i++){
            tiles[i].clear();
        }
    }




}
