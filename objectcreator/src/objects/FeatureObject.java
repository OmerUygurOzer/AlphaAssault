package objects;

import fileIO.ByteIO;
import fileIO.SerializableImage;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 1/28/2016.
 */
public class FeatureObject extends ObjectBase implements Serializable {
    public  Map<String, SerializableImage> frames = new HashMap<String, SerializableImage>();
}
