package ingame.level;



import ingame.objects.ModelManager;
import resources.Packer;
import resources.ResourceData;
import resources.ResourceManager;
import resources.ResourcePacker;

import java.io.Serializable;

/**
 * Created by Omer on 2/5/2016.
 */
public class Level implements Packer,Serializable{

    public String name;

    private ResourcePacker resourcePacker;
    private ResourceManager resourceManager;
    private ModelManager modelManager;

    public int width;
    public int height;

    public int tileSize;
    

    public Level(String name){
        this.name = name;
        this.resourcePacker = new ResourcePacker("raw_"+name,"_packed"+name,name);
        this.resourceManager = new ResourceManager();
        this.modelManager = new ModelManager();

    }

    public void setSize(int width,int height){
        this.width  = width;
        this.height = height;
    }

    public void setTileSize(int ts){
        this.tileSize = ts;
    }


    @Override
    public void pack() {
        resourcePacker.pack();
    }

    @Override
    public void unpack() {
        resourcePacker.unpack();
        resourceManager.setResourceData(resourcePacker.getPackedData());
    }

    @Override
    public ResourceData getPackedData() {
        return resourcePacker.getPackedData();
    }
}
