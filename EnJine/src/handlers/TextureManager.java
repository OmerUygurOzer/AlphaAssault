package handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import graphics.NativeImageUtils;
import ingame.objects.RawObject;
import utilities.ObjectIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 2/5/2016.
 */
public class TextureManager {
    private TextureAtlas textureAtlas;

    private String localPath = System.getProperty("user.dir");
    private String objectsPath = localPath + "\\objects\\";
    private String imagesPath = localPath + "\\images\\";
    private String atlasPath = localPath + "\\atlas\\";

    private List<File> objectFiles = new ArrayList<File>();
    private List<String> createdImages = new ArrayList<String>();


    private boolean isInitialized = false;

    public TextureManager(){
    }


    public void initialize(){
        File[] objectsFolder = new File(objectsPath).listFiles();

        for(File file: objectsFolder){
            if(file.getName().endsWith(".enjo")){
                RawObject rawObject = ObjectIO.readObject(file.getAbsolutePath());
                int numberOfFrames = rawObject.frames.size();
                BufferedImage[] bufferedImages = new BufferedImage[numberOfFrames];
                for(int i = 0;i < numberOfFrames ;i++){
                    bufferedImages[i] = rawObject.frames.get(i).image;
                }
                BufferedImage packedImage = NativeImageUtils.packFrames(bufferedImages);
                String noExtensionName = file.getName().substring(0,file.getName().length()-5);
                File packedImageFile = new File(imagesPath+noExtensionName+".png");
                try {
                    FileOutputStream outputStream = new FileOutputStream(packedImageFile);
                    ImageIO.write(packedImage,"png",outputStream);
                    outputStream.close();
                } catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();
                }
                objectFiles.add(file);
            }
        }

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 1024;
        settings.minWidth = 1024;
        TexturePacker.process(settings,imagesPath,atlasPath,"EnjAtlas");
        textureAtlas = new TextureAtlas(atlasPath+"EnjAtlas.atlas");

        isInitialized = true;
    }
}

