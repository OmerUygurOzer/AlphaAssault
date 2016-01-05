package core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/5/2016.
 */
public class LevelPacker {

    private static final String EXTENSION = ".aalvl";

    private class Image{
        public int x;
        public int y;
        public int width;
        public int height;
        public int id;
    }

    private class EntityReflection{
        public char type;
        public int x;
        public int y;
        public int width;
        public int typeID;
    }

    private class MapFeatureReflection{
        public int imageID;
        public int radius;
        public int typeID;
    }

    private class UnitReflection{
        public int imageID;
        public int HP;
        public int Range;
        public int typeID;
    }

    private class SpawnerReflection{
        public int frequency;
        public int limit;
        public int entitySpawnedType;
        public int typeID;
    }

    public static void pack(String _name,Level level){
        List<Byte> byteList = new ArrayList<Byte>();
        byte [] finalBytes;


        Integer[] pixmap = new Integer[1024*1024];


        int imageCount;
        int entityCount;
        int mapFeatureCount;
        int unitCount;
        int spawnCount;





        finalBytes = new byte[byteList.size()];
        for(int i = 0 ; i < finalBytes.length ; i++){
                finalBytes[i] = byteList.get(i);
        }

        String path = Gdx.files.getLocalStoragePath();
        path+= "levels\\" + _name+EXTENSION;
        System.out.println(path);
        File file = new File(path);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(finalBytes);
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
