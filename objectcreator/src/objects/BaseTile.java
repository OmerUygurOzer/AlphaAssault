package objects;

import fileIO.ByteIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/19/2016.
 */
public class BaseTile {
    public List<BufferedImage> frames = new ArrayList<BufferedImage>();
    public List<Integer> crossings = new ArrayList<Integer>();

    public void toFile(String path){
        File file = new File(path + ".enjTile");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(ByteIO.convertToByteArray(crossings.size()));
            for(int i = 0 ; i < crossings.size(); i++){
                fileOutputStream.write(ByteIO.convertToByteArray(crossings.get(i)));
            }
            fileOutputStream.write(ByteIO.convertToByteArray(frames.size()));
            for(int i  = 0 ; i < frames.size() ; i++ ){
            fileOutputStream.write(ByteIO.convertToByteArray(frames.get(i)));
            }
        fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
