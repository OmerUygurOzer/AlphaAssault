package GUI;

import IOUtils.ObjectIO;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Omer on 2/1/2016.
 */
public class LocalSettings implements Serializable {
    public String objectDirectory = "";

    public int mainWidth;
    public int mainHeight;

    public int lhX;
    public int lhY;
    public int lhWidth;
    public int lhHeigth;

    public int obhX;
    public int obhY;
    public int obhWidth;
    public int obhHeigth;

    public int ohX;
    public int ohY;
    public int ohWidth;
    public int ohHeigth;

    public int lrX;
    public int lrY;
    public int lrWidth;
    public int lrHeigth;

    public int tX;
    public int tY;
    public int tWidth;
    public int tHeight;

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
