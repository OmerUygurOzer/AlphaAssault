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
    public int tileType;
    public List<BufferedImage> frames = new ArrayList<BufferedImage>();

    public void toFile(String path){
        File file = new File(path + ".enjTile");
        /*
        File order
        1 - tile type
        2 - number of frames
        3 - loop{
            frameSize
            frame
            }
         */

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(ByteIO.convertToByteArray(tileType));

            fileOutputStream.write(ByteIO.convertToByteArray(frames.size()));
            for(int i  = 0 ; i < frames.size() ; i++ ){
                byte[] frame = ByteIO.convertToByteArray(frames.get(i));
                fileOutputStream.write(ByteIO.convertToByteArray(frame.length));
                fileOutputStream.write(frame);
            }




        fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
