package ingame.level;

import ingame.objects.GameObject;
import utilities.Packable;

import java.util.ArrayList;

/**
 * Created by Omer on 2/5/2016.
 */
public class Level implements Packable {
    public String name;

    public byte[] packed;

    public int width;
    public int height;

    public int tileSize;


    public Level(){

    }




    @Override
    public void pack() {

    }

    @Override
    public void unpack(Packable packable) {

    }

    @Override
    public void write(String name) {

    }

    @Override
    public void read(String name) {

    }
}
