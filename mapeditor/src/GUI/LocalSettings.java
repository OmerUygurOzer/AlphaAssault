package GUI;



import utilities.ObjectIO;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Omer on 2/1/2016.
 */
public class LocalSettings implements Serializable {
    public String objectDirectory = "";

    public int mainWidth;
    public int mainHeight;

    public int levelHolderX;
    public int levelHolderY;
    public int levelHolderWidth;
    public int levelHolderHeight;

    public int paletteX;
    public int paletteY;
    public int paletteWidth;
    public int paletteHeight;

    public void store(){
        String localPath = System.getProperty("user.dir") + "\\" + "guiSettings.enj";
        ObjectIO.write(localPath,this);
    }



    public  static LocalSettings load(){
        String localPath = System.getProperty("user.dir") + "\\" + "guiSettings.enj";
        File file = new File(localPath);
        if(!file.exists())return null;
        return (LocalSettings)ObjectIO.read(localPath);
    }


}
