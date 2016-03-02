package handlers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 2/5/2016.
 */
public class ResourceManager {
    private static ResourceManager resourceManager = new ResourceManager();
    public static ResourceManager getInstance(){return resourceManager;}

    private String localPath = System.getProperty("user.dir");
    private String levelsPath = localPath+"\\levels\\";
    private String objectsPath = localPath+"\\object\\";

    private Map<String,File> levelFiles = new HashMap<String, File>();

    private boolean resourcesRead = false;

    private ResourceManager(){}


    public void readResourceFiles(){
        File[] levelsFolder = new File(levelsPath).listFiles();
        File[] objectsFolder= new File(objectsPath).listFiles();

        for(File file: levelsFolder){
            if(file.getName().endsWith(".enjl")){
                levelFiles.put(file.getName(),file);
            }
        }

        for(File file: objectsFolder){
            if(file.getName().endsWith(".enjo")){
                levelFiles.put(file.getName(),file);
            }
        }

        resourcesRead = true;
    }

    public void generateReources(){}



}
