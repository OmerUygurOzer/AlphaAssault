package objects;

import fileIO.ByteIO;
import fileIO.SerializableImage;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/19/2016.
 */
public class TileObject extends ObjectBase implements Serializable {
    public int tileType;
    public List<SerializableImage> frames = new ArrayList<SerializableImage>();
}
