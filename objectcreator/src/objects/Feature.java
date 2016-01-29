package objects;

import fileIO.ByteIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/28/2016.
 */
public class Feature extends ObjectBase {
    public List<BufferedImage> frames = new ArrayList<BufferedImage>();

    @Override
    public void toFile(String path) {
        File file = new File(path + ".enjTile");
        /*
        File order
        1 - tile type
        2 - number of frames
        3 - loop{
            frameSize
            frame
            }
        4 - attributes
         */

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(ByteIO.convertToByteArray(frames.size()));

            for(int i  = 0 ; i < frames.size() ; i++ ){
                byte[] frame = ByteIO.convertToByteArray(frames.get(i));
                fileOutputStream.write(ByteIO.convertToByteArray(frame.length));
                fileOutputStream.write(frame);
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(attributes);


            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
