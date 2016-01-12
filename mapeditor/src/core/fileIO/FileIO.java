package core.fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Omer on 1/11/2016.
 */
public class FileIO {
    public static byte[] readFromFile(String dir){


        File file = new File(dir);
        byte[] bytes = new byte[(int)file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bytes;



    }

    public static byte[] readFromFile(File _file){


        byte[] bytes = new byte[(int)_file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(_file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bytes;



    }


}
