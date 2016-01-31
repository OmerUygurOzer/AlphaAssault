package level.objects;

import java.io.*;


/**
 * Created by Omer on 1/30/2016.
 */
public class Tile2D extends Tile {

    public Tile2D() {

    }

    @Override
    public void update(float deltaTime) {

    }

    public static Tile2D readTile2D(String path){
        File file = new File(path);
        TileObject baseTile = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            baseTile = (TileObject)object;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Tile2D tile2D = new Tile2D();
        tile2D.frames = baseTile.frames;
        tile2D.attributes = baseTile.attributes;
        return tile2D;
    }

}
