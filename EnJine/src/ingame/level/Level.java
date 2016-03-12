package ingame.level;



import ingame.logic.Attributes;
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

    private Attributes levelAttributes;
    

    public Level(String name){
        this.name = name;
        this.resourcePacker = new ResourcePacker();
        this.resourcePacker.setDirectoryNames("raw_"+name,"_packed"+name,name);
        this.resourceManager = new ResourceManager();
        this.modelManager = new ModelManager();
        this.levelAttributes = new Attributes();
    }

    public void setSize(int width,int height){
        levelAttributes.addAttribute("width",width);
        levelAttributes.addAttribute("height",height);
    }

    public void setTileSize(int ts){
        levelAttributes.addAttribute("tileSize",ts);
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

    @Override
    public void dispose() {
        resourcePacker.dispose();
    }
}
